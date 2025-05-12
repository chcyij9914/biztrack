<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>상품 추가</title>

<!-- Font Awesome -->
<link
	href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet">
<!-- SB Admin 2 CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css"
	rel="stylesheet">

<!-- Select2 CSS 추가 -->
<link
	href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css"
	rel="stylesheet" />

<!-- 표 스타일 -->
<style>
.table input, .table select {
	border: none;
	box-shadow: none;
	background-color: transparent;
	padding: 0;
	text-align: center;
}

.table input:focus, .table select:focus {
	border: none;
	box-shadow: none;
	outline: none;
}

/* 숫자 input 화살표 제거 */
input[type=number]::-webkit-inner-spin-button, input[type=number]::-webkit-outer-spin-button
	{
	-webkit-appearance: none;
	margin: 0;
}

input[type=number] {
	-moz-appearance: textfield;
}
</style>

<%@ page import="java.text.SimpleDateFormat, java.util.Date"%>
<%
String today = new SimpleDateFormat("yy/MM/dd").format(new Date());
request.setAttribute("today", today);
%>
</head>

<body class="p-4 bg-light">
	<div class="d-flex justify-content-between align-items-center mb-4">
		<h2 class="mb-0">상품 추가</h2>
		<button id="addBtn" type="button" class="btn btn-primary">추가</button>
	</div>
	<hr>



	<!-- 물품 목록 -->
	<div class="mb-3">
		<table class="table table-bordered text-center"
			style="width: 100%; font-size: 0.7rem;">
			<colgroup>
				<col style="width: 20%;">
				<col style="width: 25%;">
				<col style="width: 25%;">
				<col style="width: 10%;">
				<col style="width: 10%;">
				<col style="width: 10%;">
			</colgroup>
			<thead style="background-color: #fdfdfe;">
				<tr>
					<th>물품명</th>
					<th>카테고리</th>
					<th>세부카테고리</th>
					<th>재고</th>
					<th>단가</th>
					<th>판매가</th>
				</tr>
			</thead>
			<tbody id="itemTableBody" style="background-color: #ffffff;">
				<tr>
					<td><input type="text" class="form-control form-control-sm"
						name="productName"></td>
					<td><select class="form-control category-select"
						name="categoryList"></select> <input type="hidden"
						name="categoryId"></td>
					<td><select class="form-control subcategory-select"
						name="subCategoryList"></select> <input type="hidden"
						name="subCategoryId"></td>
						<td><input type="number" class="form-control form-control-sm"
						name="stock"></td>
					<td><input type="number" class="form-control form-control-sm"
						name="unitPriceList"></td>
					<td><input type="number" class="form-control form-control-sm"
						name="salePriceList"></td>
				</tr>
			</tbody>
		</table>
	</div>

	<input type="text" id="newCategoryInput"
		class="form-control mt-2 d-none" placeholder="새 카테고리명 입력" />
	<input type="text" id="newSubCategoryInput"
		class="form-control mt-2 d-none" placeholder="새 세부카테고리명 입력" />

	<!-- JS -->
	<script
		src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>

	<script>
let categoryList = [];
let subcategoryList = [];

$(document).ready(function () {
	const contextPath = '${pageContext.request.contextPath}';

	$.get(contextPath + '/product/subcategory-list.do', function (subcategoryData) {
		subcategoryList = subcategoryData;
		$.get(contextPath + '/product/category-list.do', function (categoryData) {
			categoryList = categoryData;
			initializeCategorySelect(categoryData);
		});
	});

	$('#newCategoryInput').on('input', function () {
		const $row = $('#itemTableBody tr').first();
		$row.find('input[name="categoryId"]').val('NEW');
	});

	$('#newSubCategoryInput').on('input', function () {
		const $row = $('#itemTableBody tr').first();
		$row.find('input[name="subCategoryId"]').val('NEW');
	});
});

function initializeCategorySelect(list) {
	$('.category-select').each(function () {
		const $select = $(this).empty().append('<option></option>');
		list.forEach(item => {
			$select.append(new Option(item.categoryName, item.categoryId));
		});
		$select.append(new Option('새 카테고리 직접 입력...', 'NEW_INPUT'));

		$select.select2({
			width: '100%',
			placeholder: '카테고리 선택',
			allowClear: true,
			closeOnSelect: true
		});

		$select.on('change', function () {
			const selectedCategoryId = $(this).val();
			const $row = $(this).closest('tr');
			const $subSelect = $row.find('.subcategory-select');

			if (selectedCategoryId === 'NEW_INPUT') {
				$('#newCategoryInput').removeClass('d-none');
				$row.find('input[name="categoryId"]').val('NEW');
				$row.find('input[name="subCategoryId"]').val('');
				$('#newSubCategoryInput').removeClass('d-none');
				$subSelect.empty().trigger('change');
			} else {
				$('#newCategoryInput').addClass('d-none');
				$row.find('input[name="categoryId"]').val(selectedCategoryId);
				updateSubcategoryOptions($subSelect, selectedCategoryId);
			}
		});
	});
}

function updateSubcategoryOptions($select, categoryId) {
	$select.empty().append('<option></option>');
	const filteredList = subcategoryList.filter(item => item.categoryId === categoryId);
	filteredList.forEach(item => {
		$select.append(new Option(item.subCategoryName, item.subCategoryId));
	});
	$select.append(new Option('새 세부카테고리 직접 입력...', 'NEW_INPUT'));

	$select.off('change').on('change', function () {
		const selectedId = $(this).val();
		const $row = $(this).closest('tr');
		if (selectedId === 'NEW_INPUT') {
			$('#newSubCategoryInput').removeClass('d-none');
			$row.find('input[name="subCategoryId"]').val('NEW');
		} else {
			$('#newSubCategoryInput').addClass('d-none');
			$row.find('input[name="subCategoryId"]').val(selectedId);
		}
	});

	$select.select2({
		width: '100%',
		placeholder: '세부카테고리 선택',
		allowClear: true,
		closeOnSelect: true
	});
}

$(document).ready(function () {
	$('#addBtn').on('click', function () {
		const productData = getProductData();
		console.log("상품 데이터:", productData);

		if (!productData.productName || !productData.salePrice ||
			(productData.categoryId === 'NEW' && !productData.newCategoryName) ||
			(productData.subCategoryId === 'NEW' && !productData.newSubCategoryName)) {
			alert('모든 항목을 입력해 주세요.');
			return;
		}

		$.ajax({
			type: 'POST',
			url: '${pageContext.request.contextPath}/product/insert.do',
			data: JSON.stringify(productData),
			contentType: 'application/json',
			success: function () {
				alert('상품이 등록되었습니다!');
				location.reload();
			},
			error: function (xhr, status, err) {
				console.error('등록 실패:', err);
				alert('등록 실패! 콘솔을 확인하세요.');
			}
		});
	});
});

function getProductData() {
	const row = $('#itemTableBody tr').first();

	const categoryId = row.find('[name="categoryId"]').val();
	const subCategoryId = row.find('[name="subCategoryId"]').val();

	const newCategoryName = $('#newCategoryInput').is(':visible') ? $('#newCategoryInput').val() : null;
	const newSubCategoryName = $('#newSubCategoryInput').is(':visible') ? $('#newSubCategoryInput').val() : null;

	return {
		productName: row.find('[name="productName"]').val(),
		categoryId: categoryId,
		subCategoryId: subCategoryId,
		stock: row.find('[name="stock"]').val(), 
		unitPrice: row.find('[name="unitPriceList"]').val(), 
		salePrice: row.find('[name="salePriceList"]').val(),
		newCategoryName: newCategoryName,
		newSubCategoryName: newSubCategoryName
	};
}
</script>
</body>
</html>