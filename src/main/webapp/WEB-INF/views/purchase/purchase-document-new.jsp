<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>새 문서 작성</title>

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
	<div class="container mt-2">
		<h2>새 문서 작성</h2>
		<br>

		<!-- 탭 + 상신버튼 -->
		<div class="d-flex justify-content-between align-items-center mb-4"
			style="border-bottom: 1px solid #dee2e6; padding-bottom: 8px;">
			<ul class="nav nav-tabs" id="docTab" role="tablist"
				style="border-bottom: none;">
				<li class="nav-item"><a class="nav-link active"
					id="purchase-tab" data-toggle="tab" href="#purchase" role="tab">구매품의서</a></li>
				<li class="nav-item"><a class="nav-link" id="payment-tab"
					data-toggle="tab" href="#payment" role="tab">지출결의서</a></li>
			</ul>
			<button type="submit" class="btn btn-primary px-4 py-1.5 ml-3">상신</button>
		</div>

		<!-- 결재 테이블 -->
		<div class="d-flex justify-content-end mb-3">
			<table class="table table-bordered text-center"
				style="width: 70%; font-size: 0.8rem;">
				<tbody style="background-color: #ffffff;">
					<tr style="background-color: #fdfdfe;">
						<th rowspan="3" style="vertical-align: middle; padding: 4px 8px;">결재</th>
						<th style="padding: 4px 8px;">사원</th>
						<th style="padding: 4px 8px;">팀장</th>
						<th style="padding: 4px 8px;">부서장</th>
					</tr>
					<tr>
						<td>기안</td>
						<td>기안</td>
						<td>기안</td>
					</tr>
					<tr>
						<td>${today}</td>
						<td>25/04/03</td>
						<td>25/04/04</td>
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
						<td><input type="text" id="documentIdInput"
							class="form-control form-control-sm" readonly
							style="background: #fdfdfe; border: none; text-align: center; min-height: 30px;" />
						</td>
						<th>작성자</th>
						<td><div contenteditable="true" style="min-height: 30px;"></div></td>
					</tr>
					<th>제목</th>
					<td colspan="3"><div contenteditable="true"
							style="min-height: 30px;"></div></td>
					</tr>
				</tbody>
			</table>
		</div>

		<!-- 물품 목록 -->
		<div class="mb-3">
			<table class="table table-bordered text-center"
				style="width: 100%; font-size: 0.7rem;">
				<colgroup>
					<col style="width: 9%;">
					<col style="width: 12%;">
					<col style="width: 19%;">
					<col style="width: 14%;">
					<col style="width: 12%;">
					<col style="width: 7.5%;">
					<col style="width: 10%;">
					<col style="width: 16.5%;">
				</colgroup>
				<thead style="background-color: #fdfdfe;">
					<tr>
						<th>번호</th>
						<th>물품코드</th>
						<th>물품명</th>
						<th>구매처</th>
						<th>견적일자</th>
						<th>수량</th>
						<th>단가</th>
						<th>금액</th>
					</tr>
				</thead>
				<tbody id="itemTableBody" style="background-color: #ffffff;">
					<tr>
						<td>1</td>
						<td><input type="text" class="form-control form-control-sm"
							name="productCodeList" readonly></td>
						<td><select class="form-control form-control-sm select2"
							name="productNameList" onchange="fillProductInfo(this)">
								<option value="">선택</option>
						</select></td>
						<td><input type="text" class="form-control form-control-sm"
							name="vendorNameList"></td>
						<td><input type="date" class="form-control form-control-sm"
							name="vendorNameList"></td>
						<td><input type="number" class="form-control form-control-sm"
							name="quantityList" onchange="calculateAmount(this)"></td>
						<td><input type="number" class="form-control form-control-sm"
							name="unitPriceList" onchange="calculateAmount(this)"></td>
						<td><input type="number" class="form-control form-control-sm"
							name="totalPriceList" readonly></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<th colspan="7" style="background-color: #fdfdfe;">합계</th>
						<th style="background-color: #ffffff;"><input type="number"
							id="grandTotal" class="form-control form-control-sm" readonly
							style="background-color: #e9ecef; border: none; text-align: center; min-height: 30px;" />
						</th>
					</tr>
				</tfoot>
			</table>
		</div>

		<!-- 품목 추가 버튼 -->
		<div class="text-right mb-4">
			<button type="button" class="btn btn-outline-primary btn-sm"
				onclick="addItem()">+ 품목 추가</button>
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


		<!-- 첨부파일 -->
		<div class="form-group">
			<label>첨부파일</label>
			<div class="d-flex align-items-center">
				<input type="text" class="form-control" readonly value="파일명"
					style="flex: 1;" /> <label for="fileUpload"
					class="btn btn-outline-primary ml-2 mb-0"> <i
					class="fas fa-upload"></i>
				</label> <input type="file" name="file" id="fileUpload" class="d-none" />
			</div>
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

		<!-- 물품 목록 Ajax + Select2 적용 -->
		<script>
let productList = []; // 전역 저장

$(document).ready(function() {
    // 서버에서 상품 목록 불러오기
    $.ajax({
        url: '${pageContext.request.contextPath}/purchase/product-list.do',
        method: 'GET',
        success: function(data) {
            productList = data; // 불러온 데이터를 전역변수에 저장
            initializeSelect2();
        }
    });
});

// 셀렉트박스 초기화 함수
function initializeSelect2() {
    $('select[name="productNameList"]').each(function() {
        const select = $(this);
        select.empty().append('<option value="">선택</option>');

        productList.forEach(product => {
            const option = $('<option>', {
                value: product.productCode,
                text: product.productName,
                'data-saleprice': product.salePrice // 단가 저장
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

// 물품명 선택시 물품코드 + 단가 입력
function fillProductInfo(select) {
    const selectedOption = select.options[select.selectedIndex];
    const selectedProductCode = selectedOption.value;
    const selectedSalePrice = selectedOption.getAttribute('data-saleprice');

    const row = select.closest('tr');
    row.querySelector('input[name="productCodeList"]').value = selectedProductCode;
    row.querySelector('input[name="unitPriceList"]').value = selectedSalePrice;

    calculateAmount(select);
}

// 수량 또는 단가 입력시 금액 자동 계산
function calculateAmount(input) {
    const row = input.closest('tr');
    const quantity = parseInt(row.querySelector('input[name="quantityList"]').value) || 0;
    const unitPrice = parseInt(row.querySelector('input[name="unitPriceList"]').value) || 0;
    const total = Math.round(quantity * unitPrice * 1.1);

    row.querySelector('input[name="totalPriceList"]').value = total;

    calculateGrandTotal();
}

// 전체 합계 계산
function calculateGrandTotal() {
    let grandTotal = 0;
    document.querySelectorAll('input[name="totalPriceList"]').forEach(input => {
        grandTotal += parseInt(input.value) || 0;
    });
    document.querySelector('tfoot input').value = grandTotal;
}

// 품목 추가 버튼 클릭 시
function addItem() {
    const tbody = document.getElementById('itemTableBody');
    const newRow = document.createElement('tr');

    newRow.innerHTML = `
        <td></td> <!-- 번호는 나중에 다시 채움 -->
        <td><input type="text" class="form-control form-control-sm" name="productCodeList" readonly></td>
        <td>
            <select class="form-control form-control-sm select2" name="productNameList" onchange="fillProductInfo(this)">
                <option value="">선택</option>
            </select>
        </td>
        <td><input type="text" class="form-control form-control-sm" name="vendorNameList"></td>
        <td><input type="date" class="form-control form-control-sm" name="quoteDateList"></td> 
        <td><input type="number" class="form-control form-control-sm" name="quantityList" onchange="calculateAmount(this)"></td>
        <td><input type="number" class="form-control form-control-sm" name="unitPriceList" onchange="calculateAmount(this)"></td>
        <td><input type="number" class="form-control form-control-sm" name="totalPriceList" readonly></td>
    `;

    tbody.appendChild(newRow);

    // 번호 다시 붙이기
    Array.from(tbody.querySelectorAll('tr')).forEach((tr, idx) => {
        tr.querySelector('td').textContent = idx + 1;
    });

    // 새로 추가된 셀렉트박스에 select2 + 상품옵션 다시 설정
    $(newRow).find('select[name="productNameList"]').each(function() {
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

//새 문서 작성시 미리보기용 문서번호 가져오기
$(document).ready(function() {

    const contextPath = '${pageContext.request.contextPath}';

    // 기본값 구매품의서
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

    // 탭 클릭 시 문서번호 미리보기 갱신
    $('.nav-link').on('click', function (e) {
        e.preventDefault();

        const docType = $(this).text().trim();

        $.ajax({
            url: contextPath + '/purchase/peek-document-id.do',
            type: 'GET',
            data: { type: docType },
            success: function (documentId) {
                $('#documentIdInput').val(documentId);
            },
            error: function () {
                alert('문서번호 조회 실패');
            }
        });

        $('.nav-link').removeClass('active');
        $(this).addClass('active');
        $(this).tab('show');
    });

});

</script>
</body>
</html>