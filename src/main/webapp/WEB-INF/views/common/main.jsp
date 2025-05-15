<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty sessionScope.loginInfo}">
    <c:redirect url="/login.do" />
</c:if>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>biztrack</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">

</head>

<body id="page-top">

  <!-- Wrapper 전체 시작 -->
  <div id="wrapper">

    <!-- Sidebar (왼쪽) -->
    <c:import url="/WEB-INF/views/common/menubar.jsp" />

    <!-- Content Wrapper (오른쪽 전체 영역) -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar (오른쪽 상단) -->
        <c:import url="/WEB-INF/views/common/topbar.jsp" />

     <!-- 페이지 본문 -->
        <div class="container-fluid">
  <!-- 제목 -->
  <div class="row mb-4">
    <div class="col">
      <h1 class="h3 text-gray-800">📊 BizTrack 대시보드</h1>
    </div>
  </div>

  <!-- 위젯 3개 행 -->
  <div class="row">

    <!-- 캘린더 -->
    <div class="col-xl-4 col-md-6 mb-4">
      <div class="card shadow h-100 py-2">
        <div class="card-body">
          <h5 class="card-title">📅 캘린더</h5>
          <p class="text-muted">여기에 캘린더 위젯 삽입 예정</p>
        </div>
      </div>
    </div>

    <!-- 일정 -->
    <div class="col-xl-4 col-md-6 mb-4">
      <div class="card shadow h-100 py-2">
        <div class="card-body">
          <h5 class="card-title">📌 오늘 일정</h5>
          <ul class="text-gray-700 mb-0" style="padding-left: 1em;">
            <li>10:00 - 회의실 A 예약</li>
            <li>13:30 - 교육관리 회의</li>
            <li>16:00 - 외부 미팅</li>
          </ul>
        </div>
      </div>
    </div>

    <!-- 월별 실적 -->
    <div class="col-xl-4 col-md-12 mb-4">
      <div class="card shadow h-100 py-2">
        <div class="card-body">
          <h5 class="card-title">📈 월별 실적</h5>
          <img src="${pageContext.request.contextPath}/resources/img/monthly-chart-placeholder.png" width="100%" alt="그래프 대체 이미지">
        </div>
      </div>
    </div>

  </div>
</div>

        
      </div>
      <!-- End of Main Content -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Wrapper -->


</body>
</html>