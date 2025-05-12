package com.erp.biztrack.product.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.erp.biztrack.category.model.dto.Category;
import com.erp.biztrack.category.service.CategoryService;
import com.erp.biztrack.common.Paging;
import com.erp.biztrack.product.exception.ProductException;
import com.erp.biztrack.product.model.dto.Product;
import com.erp.biztrack.product.model.service.ProductService;
import com.erp.biztrack.subcategory.model.dto.SubCategory;
import com.erp.biztrack.subcategory.model.service.SubCategoryService;

@Controller
@RequestMapping("/product")
public class ProductController {
	// 이 클래스 안의 메소드들이 잘 동작하는지, 매개변수 또는 리턴값을 확인하는 용도로 로그 객체를 생성함
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private SubCategoryService subcategoryService;

	/*
	 * @Autowired 가 내부에서 자동 의존성 주입하고 서비스와 연결해 줌, 생성 코드 필요없음 public
	 * ProductController() { productService = new ProductService(); }
	 */

	// 뷰 페이지 내보내기용 메소드 -----------------------------------------------------
	// 공지사항 전체 목록보기 요청 처리용 (페이징 처리 : 한 페이지에 10개씩 출력 처리)
	@RequestMapping("product-list.do")
	public ModelAndView productListMethod(ModelAndView mv, @RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "limit", required = false) String slimit) {
		// 페이징 처리
		int currentPage = 1;
		if (page != null) {
			currentPage = Integer.parseInt(page);
		}

		// 한 페이지에 출력할 목록 갯수 기본 10개로 지정함
		int limit = 10;
		if (slimit != null) {
			limit = Integer.parseInt(slimit);
		}

		// 총 목록 갯수 조회해서, 총 페이지 수 계산함
		int listCount = productService.selectListCount();
		// 페이지 관련 항목들 계산 처리
		Paging paging = new Paging(listCount, limit, currentPage, "product-list.do");
		paging.calculate();

		// 서비스 모델로 페이징 적용된 목록 조회 요청하고 결과받기
		ArrayList<Product> list = productService.selectList(paging);

		// 카테고리 리스트 조회(검색필터)
		ArrayList<Category> categoryList = categoryService.selectAll();

		if (list != null && list.size() > 0) { // 조회 성공시
			// ModelAndView : Model + View
			mv.addObject("list", list); // request.setAttribute("list", list) 와 같음
			mv.addObject("paging", paging);
			mv.addObject("categoryList", categoryList);
			mv.setViewName("product/product-list");

		} else { // 조회 실패시
			mv.addObject("message", currentPage + "페이지에 출력할 공지글 목록 조회 실패!");
			mv.setViewName("common/error");
		}
		return mv;

	}

	// 상품 상세보기
	@RequestMapping("product-detail.do")
	public ModelAndView productDetail(@RequestParam("productId") String productId, ModelAndView mv) {
		Product detail = productService.selectProductDetail(productId);
		List<Map<String, Object>> historyList = productService.getProductHistory(productId);

		if (detail != null) {
			detail.setHistoryList(historyList);
			mv.addObject("product", detail);
			mv.addObject("list", historyList);
			mv.setViewName("product/product-detail");
		} else {
			mv.addObject("message", "해당 상품 정보를 불러올 수 없습니다.");
			mv.setViewName("common/errorPage");
		}
		System.out.println("입출고 내역 수: " + historyList.size());
		for (Map<String, Object> row : historyList) {
			System.out.println(row);
		}
		return mv;

	}

	// 상품 추가페이지
	@RequestMapping("new-product.do")
	public String moveNewProductPage() {
		return "product/new-product";
	}

	// 카테고리 리스트 가져오기
	@RequestMapping("category-list.do")
	@ResponseBody
	public ArrayList<Category> getCategoryList() {
		return categoryService.selectAll();
	}

	// 서브카테고리 리스트 가져오기
	@RequestMapping("subcategory-list.do")
	@ResponseBody
	public ArrayList<SubCategory> getSubCategoryList() {
		return subcategoryService.selectAll();
	}

	// 상품 등록
	@RequestMapping("insert.do")
	@ResponseBody
	public String insertProduct(@RequestBody Product product) {
		try {
			// 상품코드 자동 생성
			String productId = productService.getNextProductId();
			product.setProductId(productId);

			// 1. 새로운 카테고리 입력 시 DB에 먼저 insert
			if ("NEW".equals(product.getCategoryId()) && product.getNewCategoryName() != null) {
				String newCategoryId = categoryService.insertAndGetId(product.getNewCategoryName());
				product.setCategoryId(newCategoryId);
			}

			// 2. 새로운 서브카테고리 입력 시 DB에 먼저 insert
			if ("NEW".equals(product.getSubCategoryId()) && product.getNewSubCategoryName() != null) {
				String newSubCategoryId = subcategoryService.insertAndGetId(product.getNewSubCategoryName(),
						product.getCategoryId());
				product.setSubCategoryId(newSubCategoryId);
			}

			int result = productService.insertProduct(product);
			return (result > 0) ? "success" : "fail";

		} catch (ProductException e) {
			logger.error("상품 등록 중 오류 발생", e);
			return "error: " + e.getMessage();
		}
	}

	// 검색기능-이름
	@RequestMapping("csearchName.do")
	public String searchProductByName(@RequestParam("keyword") String keyword, Model model) {
		List<Product> result = productService.searchByName(keyword);
		model.addAttribute("list", result);
		return "product/product-list";
	}

	// 검색기능-카테고리
	@RequestMapping("csearchCategory.do")
	public String searchProductByCategory(@RequestParam("categoryId") String categoryId, Model model) {
		List<Product> result = productService.searchByCategory(categoryId);
		List<Category> categoryList = categoryService.selectAll();
		model.addAttribute("list", result);
		model.addAttribute("categoryList", categoryList);
		return "product/product-list";
	}

	// 수정기능
	@PostMapping("update.do")
	public String updateProduct(@ModelAttribute Product product) {
		productService.updateProduct(product);
		return "redirect:/product/product-detail.do?productId=" + product.getProductId();
	}

	// 상품 삭제
	@RequestMapping("delete.do")
	@ResponseBody
	public String deleteProduct(@RequestParam String productId) {
		try {
			int result = productService.deleteProduct(productId);
			return (result > 0) ? "success" : "fail";
		} catch (ProductException e) {
			logger.error("상품 삭제 실패", e);
			return "error: " + e.getMessage();
		}
	}

	// 상품 상세보기
	@GetMapping("/product/product-detail.do")
	public String getProductDetail(@RequestParam("productId") String productId, Model model) {
		try {
			Product product = productService.getProductById(productId);
			model.addAttribute("product", product);

			List<Map<String, Object>> historyList = productService.getProductHistory(productId);
			model.addAttribute("list", historyList);

			int calculatedStock = productService.getCalculatedStock(productId);
			model.addAttribute("calculatedStock", calculatedStock);

			return "product/product-detail";
		} catch (ProductException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}
	}
}