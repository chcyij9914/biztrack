<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>training detail</title>

<!-- Fonts & Styles -->
<link
	href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,400,700,900"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css"
	rel="stylesheet">

<style>
.form-label {
	font-weight: bold;
}

.btn-primary {
	background-color: #2a63ec;
	border: none;
}

.btn-primary:hover {
	background-color: #1f4dcc;
}
</style>
</head>

<body id="page-top">
	<div id="wrapper">

		<!-- Sidebar -->
		<c:import url="/WEB-INF/views/common/menubar.jsp" />

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<c:import url="/WEB-INF/views/common/topbar.jsp" />

				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- Page Heading -->
					<div class="d-flex justify-content-between align-items-center mb-2">
						<div>
							<h1 class="h3 text-gray-800 mb-1">교육 상세보기</h1>
							<p class="text-muted small mb-0">교육 신청 전 상세 커리큘럼과 일정을 꼭
								확인하세요.</p>
						</div>
						<!-- <div>
						<a href="#" class="text-primary small mr-3">수강신청 연계</a>
						<a href="#" class="text-primary small">수료 여부 확인</a>
					</div> -->
					</div>

					<!-- 상세 정보 -->
					<div class="card shadow mb-4">
						<div class="card-body">
							<form action="/biztrack/training/enroll.do" method="post">
								<input type="hidden" name="training_id"
									value="${training.trainingId}" />

								<div class="form-group">
									<p>
										<strong>교육명:</strong>
									</p>
									<input type="text" class="form-control"
										value="${training.title}" readonly />
								</div>

								<div class="form-group">
									<p>
										<strong>강사:</strong>
									</p>
									<input type="text" class="form-control"
										value="${training.instructorName}" readonly />
								</div>

								<div class="form-group">
									<p>
										<strong>교육 일정:</strong>
									</p>
									<input type="text" class="form-control"
										value="${training.startDate} -${training.endDate}" readonly />
								</div>

								<div class="form-group">
									<p>
										<strong>교육 내용:</strong>
									</p>
									<input type="text" class="form-control"
										value="${training.courseContent}" readonly />
								</div>

								<div class="form-group">
									<p>
										<strong>정원:</strong>
									</p>
									<input type="text" class="form-control"
										value="${training.capacity} " readonly />
								</div>

								<div class="form-group">
									<p>
										<strong>장소:</strong>
									</p>
									<input type="text" class="form-control"
										value="${training.location}" readonly />
								</div>

								<div class="form-group">
									<p>
										<strong>교육 목표:</strong>
									</p>
									<textarea class="form-control" rows="4" readonly>${training.detailContent}</textarea>
								</div>

								<div class="d-flex justify-content-center mb-4">

									<a
										href="${pageContext.request.contextPath}"
										class="btn btn-warning btn-icon-split mr-2"> <span
										class="icon text-white-50"> <i class="fas fa-edit"></i>
									</span> <span class="text"> 수강신청</span>
									</a>


									<button type="button"
										class="btn btn-secondary btn-icon-split mr-2"
										onclick="history.back();">
										<span class="icon text-white-50"> <i
											class="fas fa-arrow-left"></i>
										</span> <span class="text">이전페이지</span>
									</button>

									<!-- 목록으로 버튼 -->
									<a href="${pageContext.request.contextPath}/list.do"
										class="btn btn-info btn-icon-split"> <span
										class="icon text-white-50"> <i class="fas fa-list"></i>
									</span> <span class="text">목록으로</span>
									</a>

								</div>

							</form>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Content -->
		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->
</body>
</html>
