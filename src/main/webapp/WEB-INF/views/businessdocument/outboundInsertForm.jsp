<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>출고서 등록</title>
<link
	href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<style>
body {
	background-color: #f8f9fc;
	padding: 30px;
}

.form-section {
	margin-bottom: 20px;
}

.btn-add, .btn-submit {
	font-size: 15px;
}

.btn-submit {
	width: 150px;
	display: block;
	margin: 30px auto 0;
}

.table th, .table td {
	vertical-align: middle;
	text-align: center;
}

.table th {
	background-color: #f1f1f1;
}
</style>
</head>
<body>
	<h2 class="text-center text-primary mb-4">출고서 등록</h2>
	<form
		action="${pageContext.request.contextPath}/businessdocument/insertOutbound.do"
		method="post">

		<!-- 문서 정보 -->
		<div class="form-group row form-section">
			<label class="col-sm-2 col-form-label">문서유형</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" value="출고서" readonly />
			</div>
			<label class="col-sm-2 col-form-label">문서번호</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" name="documentId"
					value="자동 생성됨" readonly />
			</div>
		</div>
		<div class="form-group row form-section">
			<label class="col-sm-2 col-form-label">제목</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="title" required />
			</div>
		</div>
		<div class="form-group row form-section">
			<label class="col-sm-2 col-form-label">작성자</label>
			<div class="col-sm-4">
				<input type="text" class="form-control"
					value="${loginInfo.empId} / ${loginInfo.empName}" readonly />
			</div>
			<label class="col-sm-2 col-form-label">담당자</label>
			<div class="col-sm-4">
				<input type="text" class="form-control"
					value="${loginInfo.empId} / ${loginInfo.empName}" readonly />
			</div>
		</div>
		
		<!-- 작성자 정보 전송용 (JSP에서 값만 보여주는 것이 아니라 서버로 넘기기 위함) -->
		<input type="hidden" name="documentWriterId" value="${loginInfo.empId}" />
		<input type="hidden" name="documentManagerId" value="${loginInfo.empId}" />
		
		<div class="form-group row form-section">
			<label class="col-sm-2 col-form-label">거래처</label>
			<div class="col-sm-4">
				<select class="form-control" name="clientId" required>
					<option value="">-- 거래처 선택 --</option>
					<c:forEach var="client" items="${clientList}">
						<option value="${client.clientId}">${client.clientName}</option>
					</c:forEach>
				</select>
			</div>
			<label class="col-sm-2 col-form-label">출고일자</label>
			<div class="col-sm-4">
				<input type="date" class="form-control" name="documentDate" required />
			</div>
		</div>

		<!-- ✅ 결재자 선택 영역: 1차 / 2차 -->
		<div class="form-group row form-section">
			<label class="col-sm-2 col-form-label">결재자</label>
			<div class="col-sm-10">
				<div class="input-group">
					<input type="text" id="approver1Display" class="form-control"
						placeholder="1차 결재자 이름/직급" readonly /> <input type="text"
						id="approver2Display" class="form-control"
						placeholder="2차 결재자 이름/직급" readonly />
					<div class="input-group-append">
						<button type="button" class="btn btn-outline-secondary"
							onclick="openApproverPopup()">조직도에서 선택</button>
					</div>
				</div>
				<input type="hidden" name="approver1Id" id="approver1Info" /> <input
					type="hidden" name="approver2Id" id="approver2Info" />
			</div>
		</div>

		<!-- 품목 테이블 -->
		<table class="table table-bordered form-section">
			<thead>
				<tr>
					<th>#</th>
					<th>상품</th>
					<th>수량</th>
					<th></th>
				</tr>
			</thead>
			<tbody id="itemTableBody">
				<tr>
					<td>1</td>
					<td><select class="form-control" name="items[0].productId">
							<option value="">-- 품목 선택 --</option>
							<c:forEach var="p" items="${productList}">
								<option value="${p.productId}">${p.productName}</option>
							</c:forEach>
					</select></td>
					<td><input type="number" name="items[0].quantity"
						class="form-control" required /></td>
					<td><button type="button" class="btn btn-danger btn-sm"
							onclick="removeRow(this)">삭제</button></td>
				</tr>
			</tbody>
		</table>
		<div class="text-right">
			<button type="button" class="btn btn-outline-primary btn-sm btn-add"
				onclick="addRow()">+ 품목 추가</button>
		</div>

		<!-- 결제수단 -->
		<div class="form-group row form-section">
			<label class="col-sm-2 col-form-label">결제수단</label>
			<div class="col-sm-4">
				<select class="form-control" name="paymentMethod" required>
					<option value="">-- 선택 --</option>
					<option value="신용카드">신용카드</option>
					<option value="계좌이체">계좌이체</option>
					<option value="현금">현금</option>
				</select>
			</div>
		</div>

		<!-- 비고 -->
		<div class="form-group form-section">
			<label>비고</label>
			<textarea name="remarks" class="form-control" rows="3"></textarea>
		</div>

		<!-- 등록 버튼 -->
		<button type="submit" class="btn btn-primary btn-submit">등록</button>
	</form>

	<script>
    // 조직도 팝업 열기
    function openApproverPopup() {
      window.open(
        '${pageContext.request.contextPath}/department/approverPopup.do?target=both',
        'approverPopup',
        'width=900,height=750,scrollbars=yes'
      );
    }

    // 조직도에서 결재자 선택 시 부모창에서 호출됨
    window.setApprovers = function(approver1Id, approver1Info, approver2Id, approver2Info) {
      document.getElementById("approver1Display").value = approver1Info;
      document.getElementById("approver2Display").value = approver2Info;
      document.getElementById("approver1Info").value = approver1Id;
      document.getElementById("approver2Info").value = approver2Id;
    };
    
    // 결재자 받아오기(확정X)
    function applyApprovers(approver1Id, approver2Id) {
        const a1 = document.getElementById("approver1Info");
        const a2 = document.getElementById("approver2Info");
        a1.value = approver1Id;
        a2.value = approver2Id;
        document.getElementById("approver1Display").value = approver1Id;
        document.getElementById("approver2Display").value = approver2Id;
      }

    // 품목 행 추가
    let rowCount = 1;
    function addRow() {
      const table = document.getElementById("itemTableBody");
      const newRow = document.createElement("tr");
      newRow.innerHTML = `
        <td>${rowCount + 1}</td>
        <td><select class="form-control" name="items[${rowCount}].productId">
          <option value="">-- 품목 선택 --</option>
          <c:forEach var="p" items="${productList}">
            <option value="${p.productId}">${p.productName}</option>
          </c:forEach>
        </select></td>
        <td><input type="number" name="items[${rowCount}].quantity" class="form-control" required /></td>
        <td><button type="button" class="btn btn-danger btn-sm" onclick="removeRow(this)">삭제</button></td>
      `;
      table.appendChild(newRow);
      rowCount++;
    }

    function removeRow(button) {
      const row = button.closest("tr");
      row.remove();
    }
  </script>
</body>
</html>
