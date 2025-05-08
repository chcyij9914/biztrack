<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>입고 작성</title>

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
		<h2 class="mb-0">입고 내역</h2>
		<button type="submit" class="btn btn-primary px-4 py-1.5 ml-3">상신</button>
	</div>
	<hr>

	<!-- 결재 테이블 -->
	<div class="d-flex justify-content-end mb-3">
		<table class="table table-bordered text-center"
			style="width: 50%; font-size: 0.8rem;">
			<tbody style="background-color: #ffffff;">
				<tr style="background-color: #fdfdfe;">
					<th rowspan="3" style="vertical-align: middle; padding: 4px 8px;">결재</th>
					<th style="padding: 4px 8px;">사원</th>
					<th style="padding: 4px 8px;">팀장</th>
				</tr>
				<tr>
					<td>기안</td>
					<td>${inbound.approve1Status}</td>
				</tr>
				<tr>
					<td>${today}</td>
					<td>${inbound.approve1Date}</td>
				</tr>
			</tbody>
		</table>
	</div>
	<br>

	<!-- 문서 작성 폼 -->
	<div class="mb-3">
		<table class="table table-bordered text-center"
			style="width: 100%; font-size: 0.8rem;">
			<colgroup>
				<col style="width: 15%;">
				<col style="width: 35%;">
				<col style="width: 15%;">
				<col style="width: 35%;">
			</colgroup>
			<tbody style="background-color: #ffffff;">
				<tr style="background-color: #fdfdfe;">
					<th>문서번호</th>
					<td><div>${inbound.documentId}</div></td>
					<th>작성자</th>
					<td><div>${inbound.empId}</div></td>
				</tr>
			</tbody>
		</table>
	</div>


	<!-- 물품 목록 -->
	<div class="mb-3">
		<table class="table table-bordered text-center"
			style="width: 100%; font-size: 0.7rem;">
			<colgroup>
				<col style="width: 20%;">
				<col style="width: 20%;">
				<col style="width: 20%;">
				<col style="width: 20%;">
				<col style="width: 20%;">

			</colgroup>
			<thead style="background-color: #fdfdfe;">
				<tr>
					<th>물품코드</th>
					<th>물품명</th>
					<th>구매처</th>
					<th>입고일자</th>
					<th>수량</th>

				</tr>
			</thead>
			<tbody id="itemTableBody" style="background-color: #ffffff;">
				<tr>
					<td><input type="text" class="form-control form-control-sm"
						name="productCodeList" readonly></td>
					<td><select class="form-control form-control-sm select2"
						name="productNameList" onchange="fillProductInfo(this)">
							<option value="">선택</option>
					</select></td>
					<td><input type="text" class="form-control form-control-sm"
						name="vendorNameList"></td>
					<td><input type="date" class="form-control form-control-sm"
						name="receivedDate"></td>
					<td><input type="number" class="form-control form-control-sm"
						name="quantityList" onchange="calculateAmount(this)"></td>

				</tr>
			</tbody>
		</table>
	</div>

	<!-- 품의서 번호 -->
	<div class="form-group">
		<label>품의서 문서번호</label> <input type="text" class="form-control"
			id="linkedPurchaseId" placeholder="예: A001">
	</div>

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

	<script>
let productList = []; // 전역 저장
let uploadedFiles = []; // 이건 document.ready 밖에 있어야 함

$(document).ready(function() {
    const contextPath = '${pageContext.request.contextPath}';

    // 문서번호 미리보기
    $.ajax({
        url: contextPath + '/purchase/peek-document-id.do',
        type: 'GET',
        data: { type: '구매품의서' },
        success: function (documentId) {
            $('#documentIdInput').val(documentId);
        },
        error: function () {
            alert('문서번호 조회 실패');
        }
    });

    // 서버에서 상품 목록 불러오기
    $.ajax({
        url: contextPath + '/inbound/product-list.do',
        method: 'GET',
        success: function(data) {
            productList = data;
            initializeSelect2(); // 여기도 정상 호출
        },
        error: function () {
            alert('상품 목록 조회 실패');
        }
    });
});

// 드롭다운에 select2 적용
function initializeSelect2() {
    $('select[name="productNameList"]').each(function() {
        const select = $(this);
        select.empty().append('<option value="">선택</option>');
        productList.forEach(product => {
            const option = $('<option>', {
                value: product.productCode,
                text: product.productName,
                'data-saleprice': product.salePrice
            });
            select.append(option);
        });
        select.select2({
            width: '100%',
            placeholder: "물품명 검색",
            allowClear: true
        });
    });
}

// 상품코드 자동 입력
function fillProductInfo(select) {
    const selectedOption = select.options[select.selectedIndex];
    const selectedProductCode = selectedOption.value;

    const row = select.closest('tr');
    row.querySelector('input[name="productCodeList"]').value = selectedProductCode;
}
</script>
</body>
</html>