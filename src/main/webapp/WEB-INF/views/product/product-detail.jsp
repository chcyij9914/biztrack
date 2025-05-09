<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>상품 상세보기</title>

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
		<h2 class="mb-0">상품 정보</h2>
		<button type="submit" class="btn btn-primary px-4 py-1.5 ml-3">수정</button>
	</div>
	<hr>


	<!--물품 정보 -->
	<div class="mb-3">
		<table class="table table-bordered text-center"
			style="width: 100%; font-size: 0.7rem;">
			<colgroup>
				<col style="width: 15%;">
				<col style="width: 15%;">
				<col style="width: 15%;">
				<col style="width: 15%;">
				<col style="width: 15%;">
				<col style="width: 15%;">
				<col style="width: 10%;">

			</colgroup>
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
			<tbody id="itemTableBody" style="background-color: #ffffff;">
				<tr>
					<td><div>${product.productId}</div></td>
					<td><div>${product.productName}</div></td>
					<td><div>${product.categoryName}</div></td>
					<td><div>${product.subCategoryName}</div></td>
					<td><div>${product.stock}</div></td>
					<td><div>${product.unitPrice}</div></td>
					<td><div>${product.salePrice}</div></td>
				</tr>
			</tbody>
		</table>
	</div>
	<br>
	<br>
	<h5>
		입출고 내역
		</h3>

		<!-- 물품 목록 -->
		<div class="mb-3">
			<table class="table table-bordered text-center"
				style="width: 100%; font-size: 0.7rem;">
				<colgroup>
					<col style="width: 15%;">
					<col style="width: 15%;">
					<col style="width: 15%;">
					<col style="width: 15%;">
					<col style="width: 15%;">
					<col style="width: 25%;">

				</colgroup>
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
				<tbody id="itemTableBody" style="background-color: #ffffff;">
					<tr>
						<td><div>${inbound.productId}</div></td>
						<td><div>${inbound.productId}</div></td>
						<td><div>${inbound.vendorName}</div></td>
						<td><div>${inbound.receivedDate}</div></td>
						<td><div>${inbound.inboundQuantity}</div></td>
						<td><div>${inbound.inboundQuantity}</div></td>

					</tr>
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

		<!-- Select2 JS 추가 -->
		<script
			src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>


		</script>
</body>
</html>