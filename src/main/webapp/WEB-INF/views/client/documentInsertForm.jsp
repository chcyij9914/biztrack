<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>문서 등록</title>
  <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
  <style>
    #itemTable th,
    #itemTable td {
      vertical-align: middle;
      text-align: center;
      font-size: 15px;
    }
    td select.form-control.product-select {
      min-width: 180px;
    }
    td input.form-control.product-name {
      min-width: 160px;
    }
    td select.form-control.payment-select {
      min-width: 120px;
    }
    td button.btn-sm {
      padding: 0.25rem 0.75rem;
      white-space: nowrap;
    }
    .btn-delete {
      min-width: 50px;
      height: 32px;
      line-height: 1.2;
    }
  </style>
  <script>
  // 품목 선택 시 단가 및 금액 계산
  function updatePrice(select, idx) {
    const option = select.options[select.selectedIndex];
    const row = select.closest('tr');
    row.querySelector('[name="items[' + idx + '].productName"]').value = option.getAttribute('data-name');
    row.querySelector('[name="items[' + idx + '].unitPrice"]').value = option.getAttribute('data-price');
    calculateAmount(idx);
  }

  function calculateAmount(idx) {
    const row = document.querySelectorAll('#itemTable tbody tr')[idx];
    const qty = parseInt(row.querySelector('[name="items[' + idx + '].quantity"]').value || 0);
    const price = parseInt(row.querySelector('[name="items[' + idx + '].unitPrice"]').value || 0);
    row.querySelector('[name="items[' + idx + '].amount"]').value = qty * price;
  }

  // 행 추가/삭제
  function addItemRow() {
    const table = document.querySelector('#itemTable tbody');
    const rowCount = table.rows.length;
    const clone = table.rows[0].cloneNode(true);
    clone.querySelectorAll('input, select').forEach(el => {
      const name = el.name.replace(/\[\d+\]/, '[' + rowCount + ']');
      el.name = name;
      if (el.tagName === 'INPUT') el.value = (el.type === 'number' || el.type === 'text') ? '' : el.value;
    });
    table.appendChild(clone);
  }

  function removeRow(btn) {
    const table = document.querySelector('#itemTable tbody');
    if (table.rows.length > 1) btn.closest('tr').remove();
  }

  // 거래처별 상품 필터링
  function filterProductsByClient(clientId) {
    $('#itemTable tbody tr').each(function () {
      const $select = $(this).find('.product-select');
      const backup = $select.data('original-options');
      const options = $('<div>' + backup + '</div>').find('option');
      const filtered = options.filter(function () {
        return $(this).data('client') === clientId;
      });
      $select.empty().append(filtered).trigger('change');
    });
  }

  $(document).on('change', '#clientId', function () {
    const clientId = $(this).val();
    filterProductsByClient(clientId);
  });

  $(function () {
    $('.product-select').each(function () {
      $(this).data('original-options', $(this).html());
    });
  });

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
      <h4 class="m-0">문서 등록</h4>
    </div>
    <div class="card-body">
      <form action="${pageContext.request.contextPath}/client/documentInsert.do" method="post" enctype="multipart/form-data">

        <!-- 상단 필드 -->
        <div class="form-row mb-3">
          <div class="col-md-2">
            <label>문서유형</label>
            <label>문서유형</label>
		    <input type="hidden" name="documentType" value="D" />
		    <input type="text" class="form-control" value="제안서" readonly />
          </div>
          <div class="col-md-2">
            <label>문서번호</label>
            <input type="text" name="documentId" class="form-control" value="${documentId}" readonly />
          </div>
          <div class="col-md-4">
            <label>제목</label>
            <input type="text" name="title" class="form-control" required />
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

        <!-- 결재자 지정 (조직도 팝업 1개로 통합) -->
		<div class="form-row mb-3">
		  <div class="col-md-6">
		    <label>결재자 선택</label>
		    <div class="input-group">
		      <!-- 사용자 표시용 -->
		      <input type="text" id="approver1Display" class="form-control" placeholder="1차 결재자 이름/직급" readonly />
		      <input type="text" id="approver2Display" class="form-control" placeholder="2차 결재자 이름/직급" readonly />
		      <div class="input-group-append">
		        <button type="button" class="btn btn-outline-secondary" onclick="openApproverPopup()">조직도에서 선택</button>
		      </div>
		    </div>
		
		    <!-- 실제 전송용 히든 필드 -->
		    <input type="hidden" name="approver1Info" id="approver1Info" />
		    <input type="hidden" name="approver2Info" id="approver2Info" />
		  </div>
		</div>

        <div class="form-row mb-3">
          <div class="col-md-3">
            <label>1차 결재일자</label>
            <input type="date" name="approve1Date" class="form-control" />
          </div>
          <div class="col-md-3">
            <label>2차 결재일자</label>
            <input type="date" name="approve2Date" class="form-control" />
          </div>
        </div>

        <!-- 품목 리스트 테이블 -->
        <table class="table table-bordered mb-3" id="itemTable">
          <thead>
            <tr class="text-center">
              <th style="width: 30px;">#</th>
              <th style="min-width: 180px;">물품코드</th>
              <th>물품명</th>
              <th>수량</th>
              <th>단가</th>
              <th>금액</th>
              <th>결제수단</th>
              <th style="width: 80px;">삭제</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>1</td>
              <td>
                <select name="items[0].productCode" class="form-control product-select" onchange="updatePrice(this, 0)">
                  <c:forEach var="p" items="${productList}">
                    <option value="${p.productCode}" data-name="${p.productName}" data-price="${p.unitPrice}">${p.productCode} - ${p.productName}</option>
                  </c:forEach>
                </select>
              </td>
              <td><input type="text" name="items[0].productName" class="form-control" readonly></td>
              <td><input type="number" name="items[0].quantity" class="form-control" oninput="calculateAmount(0)"></td>
              <td><input type="number" name="items[0].unitPrice" class="form-control" readonly></td>
              <td><input type="number" name="items[0].amount" class="form-control" readonly></td>
              <td>
                <select name="items[0].paymentMethod" class="form-control payment-select">
                  <option value="카드">신용카드</option>
                  <option value="계좌">계좌이체</option>
                  <option value="현금">현금</option>
                </select>
              </td>
              <td><button type="button" class="btn btn-danger btn-sm btn-delete" onclick="removeRow(this)">삭제</button></td>
            </tr>
          </tbody>
        </table>
        <button type="button" class="btn btn-outline-primary mb-3" onclick="addItemRow()">+ 품목 추가</button>

        <!-- 거래처 및 거래일자 -->
        <div class="form-row mb-3">
          <div class="col-md-4">
            <label>거래처</label>
            <select name="clientId" id="clientId" class="form-control">
              <c:forEach var="c" items="${clientList}">
                <option value="${c.clientId}">${c.clientId} - ${c.clientName}</option>
              </c:forEach>
            </select>
          </div>
          <div class="col-md-4">
            <label>거래일자</label>
            <input type="date" name="transactionDate" class="form-control" required />
          </div>
        </div>

        <!-- 비고 -->
        <div class="form-group">
          <label>비고</label>
          <textarea name="remarks" class="form-control" rows="3"></textarea>
        </div>

        <!-- 첨부파일 -->
        <div class="form-group">
          <label>첨부파일</label>
          <input type="file" name="uploadFile" class="form-control-file" />
        </div>

        <!-- 버튼 -->
        <div class="text-right">
          <button type="submit" class="btn btn-primary">상신</button>
        </div>
      </form>
    </div>
  </div>
</div>

<script>
  function updatePrice(select, idx) {
    const option = select.options[select.selectedIndex];
    const row = select.closest('tr');
    row.querySelector('[name="items[' + idx + '].productName"]').value = option.getAttribute('data-name');
    row.querySelector('[name="items[' + idx + '].unitPrice"]').value = option.getAttribute('data-price');
    calculateAmount(idx);
  }

  function calculateAmount(idx) {
    const row = document.querySelectorAll('#itemTable tbody tr')[idx];
    const qty = parseInt(row.querySelector('[name="items[' + idx + '].quantity"]').value || 0);
    const price = parseInt(row.querySelector('[name="items[' + idx + '].unitPrice"]').value || 0);
    row.querySelector('[name="items[' + idx + '].amount"]').value = qty * price;
  }

  function addItemRow() {
    const table = document.querySelector('#itemTable tbody');
    const rowCount = table.rows.length;
    const clone = table.rows[0].cloneNode(true);
    clone.querySelectorAll('input, select').forEach(el => {
      const name = el.name.replace(/\[\d+\]/, '[' + rowCount + ']');
      el.name = name;
      if (el.tagName === 'INPUT') el.value = (el.type === 'number' || el.type === 'text') ? '' : el.value;
    });
    table.appendChild(clone);
  }

  function removeRow(btn) {
    const table = document.querySelector('#itemTable tbody');
    if (table.rows.length > 1) btn.closest('tr').remove();
  }
</script>
</body>
</html>
