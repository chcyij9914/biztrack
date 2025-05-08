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
		<button type="submit" class="btn btn-primary px-4 py-1.5 ml-3">추가</button>
	</div>
	<hr>



	<!-- 물품 목록 -->
	<div class="mb-3">
		<table class="table table-bordered text-center"
			style="width: 100%; font-size: 0.7rem;">
			<colgroup>
				<col style="width: 30%;">
				<col style="width: 18%;">
				<col style="width: 12%;">
				<col style="width: 12%;">
				<col style="width: 20%;">
			</colgroup>
			<thead style="background-color: #fdfdfe;">
				<tr>
					<th>물품명</th>
					<th>카테고리</th>
					<th>세부카테고리</th>
					<th>수량</th>
					<th>판매가</th>
				</tr>
			</thead>
			<tbody id="itemTableBody" style="background-color: #ffffff;">
				<tr>
					<td><input type="text" class="form-control form-control-sm"
						name="productNameList"></td>
					<td><select class="form-control form-control-sm select2"
						name="categoryList" onchange="fillProductInfo(this)">
							<option value="">선택</option>
					</select></td>
					<td><select class="form-control form-control-sm select2"
						name="subcategoryList" onchange="fillProductInfo(this)">
							<option value="">선택</option>
					</select></td>
					<td><input type="number" class="form-control form-control-sm"
						name="quantityList" onchange="calculateAmount(this)"></td>
					<td><input type="number" class="form-control form-control-sm"
						name="unitPriceList"></td>
				</tr>
			</tbody>
		</table>
	</div>



	<!-- 새 카테고리 입력창 (기본은 숨김) -->
	<input type="text" id="newCategoryInput"
		class="form-control mt-2 d-none" placeholder="새 카테고리명 입력" />

	<!-- 비고란 -->
	<div class="mb-4">
		<table class="table table-bordered text-center"
			style="width: 100%; font-size: 0.8rem;">
			<thead style="background-color: #fdfdfe;">
				<tr>
					<th>비고</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td style="background-color: #ffffff;"><div
							contenteditable="true"
							style="min-height: 80px; text-align: left;"></div></td>
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

</body>
</html>