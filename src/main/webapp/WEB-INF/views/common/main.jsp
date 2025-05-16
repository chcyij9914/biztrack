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

					<!-- 위젯 2개 행 -->
					<div class="row">
						<!-- 캘린더 -->
						<div class="col-lg-6 col-md-12 mb-4">
							<div class="card shadow h-100 py-2">
								<div class="card-body">
									<h5 class="card-title">📅 일정</h5>
									<jsp:include page="/WEB-INF/views/common/calendar-fragment.jsp" />
								</div>
							</div>
						</div>

						<!-- 공지 사항 -->
						<div class="col-lg-6 col-md-12 mb-4">
							<div class="card shadow h-100 py-2">
								<div class="card-body">
									<h5 class="card-title">📌 최신 공지사항</h5>
									<jsp:include page="/nlist5.do" />
								</div>
							</div>
						</div>
					</div>
					
				

				</div>
				<!-- End of Content Wrapper -->

			</div>
			<!-- End of Wrapper -->
</body>
</html> 