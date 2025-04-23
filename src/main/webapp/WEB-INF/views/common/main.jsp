<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
          <h1 class="h3 mb-4 text-gray-800">여기에 본문을 작성하세요</h1>
        </div>
        
      </div>
      <!-- End of Main Content -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Wrapper -->


</body>
</html>