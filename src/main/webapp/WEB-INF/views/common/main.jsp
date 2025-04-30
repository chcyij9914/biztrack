<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    // 로그인 세션 체크
    if (session.getAttribute("loginInfo") == null) {
        response.sendRedirect(request.getContextPath() + "/login.do");
        return; // 여기서 반드시 return을 해줘야 밑에 코드 실행 안 됨!!
    }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
   content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
  <title>biztrack</title>
   
  <link
   href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css"
   rel="stylesheet">
<link
   href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,400,700,900"
   rel="stylesheet">
<link
   href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css"
   rel="stylesheet">

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
  
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>

</body>
</html>