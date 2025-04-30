<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>오류 발생</title>
    <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body id="page-top">
<div class="container-fluid mt-5 text-center">
    <div class="error mx-auto" data-text="Oops!" style="font-size: 100px; color: #e74a3b;">Oops!</div>
    <p class="lead text-gray-800 mb-4">요청 처리 중 오류가 발생했습니다</p>
    <p class="text-gray-500 mb-4">
        <c:out value="${msg}" default="예기치 않은 문제가 발생했습니다. 관리자에게 문의해주세요."/>
    </p>

    <a href="javascript:history.back()" class="btn btn-secondary mt-3">
        <i class="fas fa-arrow-left mr-1"></i> 이전 페이지로
    </a>
    <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-primary mt-3 ml-2">
        <i class="fas fa-home mr-1"></i> 메인으로 이동
    </a>
</div>

<!-- 스크립트 -->
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>
</body>
</html>
