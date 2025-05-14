<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>출고서 등록</title>
  <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <style>
    body { background-color: #f8f9fc; padding: 30px; }
    .form-section { margin-bottom: 20px; }
    .btn-add, .btn-submit { font-size: 15px; }
    .btn-submit { width: 150px; display: block; margin: 30px auto 0; }
    .table th, .table td { vertical-align: middle; text-align: center; }
    .table th { background-color: #f1f1f1; }
  </style>
</head>

<body>
  <h2 class="text-center text-primary mb-4">출고서 등록</h2>

  <form action="${pageContext.request.contextPath}/businessdocument/insertOutbound.do" method="post">
    
    <!-- 문서 정보 -->
    <div class="form-group row form-section">
      <label class="col-sm-2 col-form-label">문서유형</label>
      <div class="col-sm-4">
        <input type="text" class="form-control" value="출고서" readonly />
      </div>
      <label class="col-sm-2 col-form-label">문서번호</label>
      <div class="col-sm-4">
        <input type="text" class="form-control" name="documentId" value="자동 생성됨" readonly />
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
        <input type="text" class="form-control" value="${loginInfo.empId} / ${loginInfo.empName}" readonly />
      </div>
      <label class="col-sm-2 col-form-label">담당자</label>
      <div class="col-sm-4">
        <input type="text" class="form-control" value="${loginInfo.empId} / ${loginInfo.empName}" readonly />
      </div>
    </div>

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

    <!-- 품목 테이블 -->
    <table class="table table-bordered form-section">
      <thead>
        <tr>
          <th>#</th>
          <th>상품</th>
          <th>수량</th>
          <th>삭제</th>
        </tr>
      </thead>
      <tbody id="itemTableBody">
        <tr>
          <td>1</td>
          <td>
            <select class="form-control" name="items[0].productId">
              <option value="">-- 품목 선택 --</option>
              <c:forEach var="p" items="${productList}">
                <option value="${p.productId}">${p.productName}</option>
              </c:forEach>
            </select>
          </td>
          <td>
            <input type="number" name="items[0].quantity" class="form-control" required />
          </td>
          <td>
            <button type="button" class="btn btn-danger btn-sm" onclick="removeRow(this)">삭제</button>
          </td>
        </tr>
      </tbody>
    </table>

    <div class="text-right">
      <button type="button" class="btn btn-outline-primary btn-sm btn-add" onclick="addRow()">+ 품목 추가</button>
    </div>

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

    <div class="form-group form-section">
      <label>비고</label>
      <textarea name="remarks" class="form-control" rows="3"></textarea>
    </div>

    <button type="submit" class="btn btn-primary btn-submit">등록</button>
  </form>

  <!-- 품목 추가 스크립트 -->
  <script>
    let rowCount = 1;

    // 상품 옵션 목록 백업
    const productOptionHTML = `
      <option value="">-- 품목 선택 --</option>
      <c:forEach var="p" items="${productList}">
        <option value="${p.productId}">${p.productName}</option>
      </c:forEach>
    `;

    function addRow() {
      const table = document.getElementById('itemTableBody');
      const newRow = document.createElement('tr');
      newRow.innerHTML = `
        <td>${rowCount + 1}</td>
        <td>
          <select class="form-control" name="items[${rowCount}].productId">
            ${productOptionHTML}
          </select>
        </td>
        <td>
          <input type="number" name="items[${rowCount}].quantity" class="form-control" required />
        </td>
        <td>
          <button type="button" class="btn btn-danger btn-sm" onclick="removeRow(this)">삭제</button>
        </td>
      `;
      table.appendChild(newRow);
      rowCount++;
    }

    function removeRow(button) {
      const row = button.closest('tr');
      row.remove();
    }
  </script>
</body>
</html>
