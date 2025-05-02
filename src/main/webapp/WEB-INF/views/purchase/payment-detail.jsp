<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>지출결의서 상세보기</title>

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
		<h2>지출결의서 상세보기</h2>
		<br>

		<!-- 탭 + 상신버튼 -->
		<div class="d-flex justify-content-between align-items-center mb-4"
			style="border-bottom: 1px solid #dee2e6; padding-bottom: 8px;">
			<ul class="nav nav-tabs">
				<li class="nav-item"><a class="nav-link active" href="#"
					onclick="return false;">지출결의서</a></li>
			</ul>
			<button type="submit" class="btn btn-primary px-4 py-1.5 ml-3">상신취소</button>
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
						<td>${purchase.approve1Status}</td>
						<td>${purchase.approve2Status}</td>
					</tr>
					<tr>
						<td>${purchase.createdDate}</td>
						<td>${purchase.approve1Date}</td>
						<td>${purchase.approve2Date}</td>
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
						<td><div>${purchase.documentId}</div></td>
						<th>작성자</th>
						<td><div>${purchase.empId}</div></td>
					</tr>
					<tr>
						<th>제목</th>
						<td colspan="3"><div>${purchase.title}</div></td>
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
						<th>계산서일자</th>
						<th>수량</th>
						<th>단가</th>
						<th>금액</th>
					</tr>
				</thead>
				<tbody id="itemTableBody" style="background-color: #ffffff;">
					<tr>
						<td>${status.index + 1}</td>
						<td><div>${purchase.productCode}</div></td>
						<td><div>${purchase.productCode}</div></td>
						<td><div>${purchase.vendorName}</div></td>
						<td><div>${purchase.invoiceDate}</div></td>
						<td><div>${purchase.purchaseQuantity}</div></td>
						<td><div></div></td>
						<td><div></div></td>
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

		<!-- 품의서 번호 -->
		<div class="form-group">
			<label>품의서 문서번호</label> <input type="text" class="form-control"
				id="linkedPurchaseId" 
				<%-- value="${purchase.documentId}"  --%>readonly>
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
				<textarea class="form-control" id="fileNamePreview" readonly
					rows="2" style="flex: 1; resize: none;"></textarea>
				<label for="fileUpload" class="btn btn-outline-primary ml-2 mb-0">
					<i class="fas fa-upload"></i>
				</label> <input type="file" name="files" id="fileUpload" class="d-none"
					multiple />
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


		</script>
</body>
</html>