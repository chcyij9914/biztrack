<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>리마인더 목록</title>

  <!-- SB Admin 2 CSS -->
  <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
</head>

<body id="page-top">
  <div id="wrapper">
    <%@ include file="/WEB-INF/views/common/menubar.jsp" %>

    <div id="content-wrapper" class="d-flex flex-column">
      <div id="content">
        <c:import url="/WEB-INF/views/common/topbar.jsp" />

        <div class="container-fluid">
          <!-- 제목 -->
          <h1 class="h3 mb-4 text-gray-800">리마인더 목록</h1>

          <!-- 카드 -->
          <div class="card shadow mb-4">
            <div class="card-header py-3 d-flex justify-content-between align-items-center">
              <h6 class="m-0 font-weight-bold text-primary">구매/리마인더 정보</h6>
              <a href="#" class="btn btn-sm btn-primary">문자 작성</a>
            </div>
            <div class="card-body">
              <!-- 테이블 -->
              <div class="table-responsive">
                <table class="table table-bordered text-center">
                  <thead class="thead-light">
                    <tr>
                      <th>물품명</th>
                      <th>거래처명</th>
                      <th>거래처번호</th>
                      <th>거래처주소</th>
                      <th>구매 수량</th>
                      <th>거래건수</th>
                      <th>수신확인</th>
                      <th>리마인더 생성일</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach var="row" items="${reminderList}">
                      <tr>
                        <td>${row.productName}</td>
                        <td>${row.clientName}</td>
                        <td>${row.clientPhone}</td>
                        <td>${row.clientAddress}</td>
                        <td>${row.purchaseQuantity}</td>
                        <td>${row.transactionCount}</td>
                        <td>
                          <c:choose>
                            <c:when test="${row.receiptConfirmation eq 'Y'}">확인</c:when>
                            <c:otherwise>미확인</c:otherwise>
                          </c:choose>
                        </td>
                        <td><fmt:formatDate value="${row.reminderDate}" pattern="yyyy-MM-dd" /></td>
                      </tr>
                    </c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div> <!-- container-fluid -->
      </div> <!-- content -->

      <c:import url="/WEB-INF/views/common/footer.jsp" />
    </div> <!-- content-wrapper -->
  </div> <!-- wrapper -->

  <!-- JS -->
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>
</body>
</html>
