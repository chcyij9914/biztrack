<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>계약서 등록</title>
<link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<style>
    #itemTable th, #itemTable td {
      vertical-align: middle;
      text-align: center;
      font-size: 15px;
    }
    td input.form-control {
      min-width: 140px;
    }
</style>
<script>
    function calculateAmount(idx) {
      const row = document.querySelectorAll('#itemTable tbody tr')[idx];
      const qty = parseInt(row.querySelector('[name="items[' + idx + '].quantity"]').value || 0);
      const price = parseInt(row.querySelector('[name="items[' + idx + '].salePrice"]').value || 0);
      row.querySelector('[name="items[' + idx + '].amount"]').value = qty * price;
      updateTotalAmount();
    }

    function updateTotalAmount() {
      let total = 0;
      document.querySelectorAll('[name$=".amount"]').forEach(el => {
        const val = parseInt(el.value || 0);
        total += isNaN(val) ? 0 : val;
      });
      document.getElementById('totalAmountDisplay').innerText = total.toLocaleString();
    }

    document.addEventListener('DOMContentLoaded', updateTotalAmount);
</script>
<c:if test="${not empty proposal}">
  <script>
    document.addEventListener("DOMContentLoaded", function () {
      document.getElementById("contractStartDate").removeAttribute("disabled");
      document.getElementById("contractEndDate").removeAttribute("disabled");
      document.getElementById("paymentMethod").removeAttribute("disabled");
      document.getElementById("title").removeAttribute("readonly");
    });
  </script>
</c:if>
<script>
  // 결재자 적용
  function applyApprovers(approver1Id, approver2Id) {
    const a1 = document.getElementById("approver1Info");
    const a2 = document.getElementById("approver2Info");
    a1.value = approver1Id;
    a2.value = approver2Id;
    document.getElementById("approver1Display").value = approver1Id;
    document.getElementById("approver2Display").value = approver2Id;
  }

  function displayApproverInfo() {
    const a1 = document.getElementById("approver1Info");
    const a2 = document.getElementById("approver2Info");
    document.getElementById("approver1Display").value = a1.value;
    document.getElementById("approver2Display").value = a2.value;
  }

  function openApproverPopup() {
	  window.open('${pageContext.request.contextPath}/department/approverPopup.do?target=both',
	              'approverPopup',
	              'width=900,height=750');
	}
</script>
</head>
<body class="bg-light">
<div class="container mt-5">
  <div class="card shadow">
    <div class="card-header bg-primary text-white">
      <h4 class="m-0">계약서 등록</h4>
    </div>
    <div class="card-body">
      <form action="${pageContext.request.contextPath}/client/cdocumentInsert.do" method="post" enctype="multipart/form-data">
      
      <!-- 안내메시지 -->
      <c:if test="${empty proposal}">
		  <div class="alert alert-warning d-flex align-items-center" role="alert">
		    <i class="fas fa-exclamation-triangle mr-2"></i>
		    먼저 <strong>제안서를 선택</strong>한 후 <strong>[불러오기]</strong> 버튼을 클릭해 주세요.
		  </div>
		</c:if>

        <div class="form-row mb-3">
          <div class="col-md-6">
            <label>연결 제안서</label>
            <div class="input-group">
              <select id="proposalSelect" class="form-control">
                <option value="">-- 제안서 선택 --</option>
                <c:forEach var="doc" items="${proposalList}">
                  <option value="${doc.documentId}">${doc.documentId} - ${doc.title}</option>
                </c:forEach>
              </select>
              <div class="input-group-append">
                <button type="button" class="btn btn-outline-secondary" onclick="location.href='cdocumentInsertForm.do?proposalId=' + proposalSelect.value">불러오기</button>
              </div>
            </div>
            <input type="hidden" name="connectDocumentId" id="connectDocumentId" value="${proposal.documentId}" />
          </div>
        </div>

        <div class="form-row mb-3">
          <div class="col-md-2">
            <label>문서유형</label>
            <input type="hidden" name="documentTypeId" value="C" />
            <input type="text" class="form-control" value="계약서" readonly />
          </div>
          <div class="col-md-2">
            <label>문서번호</label>
            <input type="text" class="form-control" value="자동 생성됨" readonly />
          </div>
          <div class="col-md-4">
            <label>제목</label>
            <input type="text" name="title" id="title" class="form-control" value="[${proposal.documentId}] ${items[0].productName} 계약" readonly required />
          </div>
        </div>

        <div class="form-row mb-3">
          <div class="col-md-6">
            <label>작성자</label>
            <input type="text" class="form-control" value="${sessionScope.loginInfo.empId} / ${sessionScope.loginInfo.empName} / ${sessionScope.loginInfo.jobId}" readonly />
            <input type="hidden" name="writerEmpId" value="${sessionScope.loginInfo.empId}" />
          </div>
          <div class="col-md-6">
            <label>담당자</label>
            <input type="text" class="form-control" value="${sessionScope.loginInfo.empId} / ${sessionScope.loginInfo.empName} / ${sessionScope.loginInfo.jobId}" readonly />
            <input type="hidden" name="managerEmpId" value="${sessionScope.loginInfo.empId}" />
          </div>
        </div>

        <div class="form-row mb-3">
          <div class="col-md-6">
            <label>결재자 선택</label>
            <div class="input-group">
              <input type="text" id="approver1Display" class="form-control" placeholder="1차 결재자 이름/직급" readonly />
              <input type="text" id="approver2Display" class="form-control" placeholder="2차 결재자 이름/직급" readonly />
              <div class="input-group-append">
                <button type="button" class="btn btn-outline-secondary" onclick="openApproverPopup()">조직도에서 선택</button>
              </div>
            </div>
            <input type="hidden" name="approver1Info" id="approver1Info" />
            <input type="hidden" name="approver2Info" id="approver2Info" />
          </div>
        </div>

        <div class="form-group">
          <label><strong>결제수단</strong></label>
          <select name="paymentMethod" id="paymentMethod" class="form-control" disabled required>
			  <option value="">-- 선택 --</option>
			  <option value="카드" ${proposal.paymentMethod == '카드' ? 'selected' : ''}>신용카드</option>
			  <option value="계좌이체" ${proposal.paymentMethod == '계좌이체' ? 'selected' : ''}>계좌이체</option>
			  <option value="현금" ${proposal.paymentMethod == '현금' ? 'selected' : ''}>현금</option>
			</select>
        </div>

        <table class="table table-bordered mb-3" id="itemTable">
          <thead>
            <tr class="text-center">
              <th>#</th>
              <th>물품코드</th>
              <th>물품명</th>
              <th>수량</th>
              <th>단가</th>
              <th>금액</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="item" items="${items}" varStatus="loop">
              <tr>
                <td>${loop.index + 1}</td>
                <td><input type="text" name="items[${loop.index}].productId" class="form-control" value="${item.productId}" readonly /></td>
                <td><input type="text" name="items[${loop.index}].productName" class="form-control" value="${item.productName}" readonly /></td>
                <td><input type="number" name="items[${loop.index}].quantity" class="form-control" value="${item.quantity}" oninput="calculateAmount(${loop.index})" required /></td>
                <td><input type="number" name="items[${loop.index}].salePrice" class="form-control" value="${item.salePrice}" readonly /></td>
                <td><input type="number" name="items[${loop.index}].amount" class="form-control" value="${item.quantity * item.salePrice}" readonly /></td>
              </tr>
            </c:forEach>
          </tbody>
        </table>

        <div class="text-right mb-3">
          <strong>총합계:</strong> <span id="totalAmountDisplay">0</span>원
        </div>

        <div class="form-row mb-3">
          <div class="col-md-4">
            <label>거래처</label>
            <select name="clientId" id="clientId" class="form-control" required readonly>
              <option value="${proposal.clientId}" selected>${proposal.clientId} - ${proposal.clientName}</option>
            </select>
          </div>
          <div class="col-md-4">
            <label>거래일자</label>
            <input type="date" name="documentDate" class="form-control" value="${proposal.documentDate}" required />
          </div>
        </div>
        <div class="form-row mb-3">
          <div class="col-md-4">
            <label>계약 시작일</label>
            <input type="date" name="contractStartDate" id="contractStartDate" class="form-control" disabled required />
          </div>
          <div class="col-md-4">
            <label>계약 종료일</label>
            <input type="date" name="contractEndDate" id="contractEndDate" class="form-control" disabled required />
          </div>
        </div>

        <div class="form-group">
          <label>비고</label>
          <textarea name="remarks" class="form-control" rows="3"></textarea>
        </div>

        <div class="form-group">
          <label>첨부파일</label>
          <input type="file" name="uploadFile" class="form-control-file" />
        </div>

        <div class="text-right">
          <button type="submit" class="btn btn-primary">상신</button>
        </div>
        <c:if test="${not empty msg}">
          <div class="alert alert-success">${msg}</div>
        </c:if>
      </form>
    </div>
  </div>
</div>
</body>
</html>
