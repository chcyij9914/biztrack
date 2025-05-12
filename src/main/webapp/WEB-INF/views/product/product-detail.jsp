<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>상품 상세보기</title>

<!-- CSS -->
<link
	href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css"
	rel="stylesheet" />

<style>
.table input {
	border: none;
	background-color: transparent;
	text-align: center;
	width: 100%;
}

.table input:focus {
	outline: none;
}
</style>
</head>

<body class="p-4 bg-light">

	<div class="d-flex justify-content-between align-items-center mb-4">
	<h2 class="mb-0">상품 정보</h2>
	<div>
		<button type="submit" class="btn btn-primary me-2" form="updateForm">수정</button>
		<button type="button" class="btn btn-danger" onclick="deleteProduct('${product.productId}')">삭제</button>
	</div>
</div>
<hr>

	<!-- 상품 수정 form -->
	<form id="updateForm" method="post"
		action="${pageContext.request.contextPath}/product/update.do">
		<div class="mb-3">
			<table class="table table-bordered text-center"
				style="font-size: 0.8rem;">
				<thead style="background-color: #fdfdfe;">
					<tr>
						<th>물품코드</th>
						<th>물품명</th>
						<th>카테고리</th>
						<th>세부카테고리</th>
						<th>재고</th>
						<th>단가</th>
						<th>판매가</th>
					</tr>
				</thead>
				<tbody style="background-color: #ffffff;">
					<tr>
						<td><input type="text" name="productId"
							value="${product.productId}" readonly></td>

						<td><input type="text" name="productName"
							value="${product.productName}"></td>

						<td><input type="hidden" name="categoryId"
							value="${product.categoryId}"> <input type="text"
							name="newCategoryName" value="${product.categoryName}"></td>

						<td><input type="hidden" name="subCategoryId"
							value="${product.subCategoryId}"> <input type="text"
							name="newSubCategoryName" value="${product.subCategoryName}">
						</td>

						<td><input type="number" name="stock"
							value="${product.stock}"></td>
						<td><input type="number" name="unitPrice"
							value="${product.unitPrice}"></td>
						<td><input type="number" name="salePrice"
							value="${product.salePrice}"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>

	<br>
	<br>
	<h5>입출고 내역</h5>

	<div class="mb-3">
		<table class="table table-bordered text-center"
			style="font-size: 0.7rem;">
			<thead style="background-color: #fdfdfe;">
				<tr>
					<th>거래유형</th>
					<th>날짜</th>
					<th>물품명</th>
					<th>거래처</th>
					<th>수량</th>
					<th>재고</th>
				</tr>
			</thead>
			<tbody style="background-color: #ffffff;">
				<c:forEach var="row" items="${list}">
					<tr>
						<td>${row.DOCUMENT_NAME}</td>
						<td>${row.DOCUMENT_DATE}</td>
						<td>${row.PRODUCT_NAME}</td>
						<td>${row.CLIENT_NAME}</td>
						<td>${row.QUANTITY}</td>
						<td>${row.STOCK}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

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
		function deleteProduct(productId) {
			if (confirm("정말 삭제하시겠습니까?")) {
				$
						.ajax({
							type : 'POST',
							url : '${pageContext.request.contextPath}/product/delete.do',
							data : {
								productId : productId
							},
							success : function(response) {
								if (response === 'success') {
									alert("삭제되었습니다.");
									location.reload();
								} else {
									alert("삭제 실패: " + response);
								}
							},
							error : function(xhr) {
								alert("에러 발생: " + xhr.responseText);
							}
						});
			}
		}
	</script>
</body>
</html>