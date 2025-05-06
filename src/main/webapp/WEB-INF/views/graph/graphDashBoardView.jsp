<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>영업 그래프 대시보드</title>

  <!-- Google Charts & jQuery -->
  <script src="https://www.gstatic.com/charts/loader.js"></script>
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
        <c:import url="/WEB-INF/views/common/topbar.jsp" />

        <div class="container-fluid">
          <!-- 타이틀 -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h4 mb-0 text-gray-800">영업 그래프 대시보드</h1>
          </div>

          <!-- 좌우 2열 구조 -->
          <div class="row">
            <!-- 제품별 영업이익 -->
            <div class="col-md-6 mb-4">
              <c:import url="/graph/profitGraph.do?fragment=true" />
            </div>

            <!-- 거래처별 거래건수 -->
            <div class="col-md-6 mb-4">
              <c:import url="/graph/transactionCountGraph.do?fragment=true" />
            </div>
          </div>

          <!-- 하단 추가 영역 -->
          <div class="row">
            <div class="col-md-12">
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">추가 영역</h6>
                </div>
                <div class="card-body">
                  <p>추가 그래프나 표 자리를 위한 공간입니다.</p>
                </div>
              </div>
            </div>
          </div>

        </div> <!-- container-fluid -->
      </div> <!-- content -->

      <c:import url="/WEB-INF/views/common/footer.jsp" />
    </div> <!-- content-wrapper -->
  </div> <!-- wrapper -->

  <!-- JS -->
  <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>
</body>
</html>
