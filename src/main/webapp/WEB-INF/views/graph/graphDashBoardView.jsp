<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>영업 그래프 대시보드</title>

  <!-- Google Charts -->
  <script src="https://www.gstatic.com/charts/loader.js"></script>

  <!-- jQuery -->
  <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

  <!-- SB Admin 2 CSS -->
  <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
</head>

<body id="page-top">
  <div id="wrapper">
    <%@ include file="/WEB-INF/views/common/menubar.jsp" %>

    <div id="content-wrapper" class="d-flex flex-column">
      <div id="content">
        <div class="container-fluid">
          <!-- 타이틀 -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h4 mb-0 text-gray-800">영업 그래프 대시보드</h1>
          </div>

          <!-- 3개의 개별 그래프 import (Controller URL로 로드됨) -->
          <div class="row">
            <div class="col-md-12">
              <c:import url="/graph/profitGraph.do?fragment=true" />
              <%-- <c:import url="${pageContext.request.contextPath}/graph/departmentGraph.do" />
              <c:import url="${pageContext.request.contextPath}/graph/transactionCount.do" /> --%>
            </div>
          </div>

        </div>
      </div>
    </div>
  </div>

  <!-- JS -->
  <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>
</body>
</html>
