<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>training register</title>

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
.pagination .page-link {
	border: none;
	background-color: transparent;
}

.pagination .page-item.active .page-link {
	background-color: transparent;
	color: #4e73df;
	font-weight: bold;
	border: none;
}

.pagination .page-link:hover {
	background-color: #f0f0f0;
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
							<h1 class="h3 text-gray-800 mb-1">교육등록 / 수정</h1>
							<p class="text-muted small mb-0">교육 과정을 신설하거나 기존 교육을 수정합니다.</p>
						</div>

						<div class="d-flex align-items-center">
							<form class="form-inline"
								action="${pageContext.request.contextPath}/purchase/search.do"
								method="get"></form>
						</div>
					</div>


					<div class="card-body">
						<form method="post"
							action="${pageContext.request.contextPath}/training/insert.do"
							enctype="multipart/form-data">

																			
									<%-- <!-- 교육 아이디 -->
							<div class="form-group row">
								<label class="col-sm-2 col-form-label">교육 아이디 :</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="title"
										value="${training.trainingid}" required>
								</div>
							</div> --%>

									<!-- 교육명 -->
									<div class="form-group row">
										<label class="col-sm-2 col-form-label">교육 명 :</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" name="title"
												value="${training.title}" required>
										</div>
									</div>

									<!-- 교육 내용 -->
									<div class="form-group row">
										<label class="col-sm-2 col-form-label">교육 내용 :</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" name="courseContent"
												value="${training.courseContent}" required>
										</div>
									</div>

									<!-- 강사명 -->
									<div class="form-group row">
										<label class="col-sm-2 col-form-label">강사명 :</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" name="instructorName"
												value="${training.instructorName}">
										</div>
									</div>

									<!-- 시작일 -->
									<div class="form-group row">
										<label class="col-sm-2 col-form-label">시작일 :</label>
										<div class="col-sm-5">
											<input type="date" class="form-control" name="startDate"
												value="${training.startDate}">
										</div>
									</div>

									<!-- 종료일 -->
									<div class="form-group row">
										<label class="col-sm-2 col-form-label">종료일 :</label>
										<div class="col-sm-5">
											<input type="date" class="form-control" name="endDate"
												value="${training.endDate}">
										</div>
									</div>

									<!-- 정원 -->
									<div class="form-group row">
										<label class="col-sm-2 col-form-label">정원 :</label>
										<div class="col-sm-10">
											<input type="number" class="form-control" name="capacity"
												value="${training.capacity}">
										</div>
									</div>

									<!-- 교육 장소 -->
									<div class="form-group row">
										<label class="col-sm-2 col-form-label">교육 장소 :</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" name="location"
												value="${training.location}">
										</div>
									</div>

									<!-- 상세 내용 -->
									<div class="form-group row">
										<label class="col-sm-2 col-form-label">교육 계획서 :</label>
										<div class="col-sm-10">
											<textarea class="form-control" name="detailContent" rows="4">${training.detailContent}</textarea>
										</div>
									</div>
									<!-- 
							파일 업로드 (선택사항)
							<div class="form-group row">
								<label class="col-sm-2 col-form-label">자료 업로드 :</label>
								<div class="col-sm-10">
									<input type="file" class="form-control-file" name="file">
								</div>
							</div> -->

									<!-- 제출 버튼 -->
									<div class="form-group row">
										<div class="col-sm-12 text-center">
											<button type="submit" class="btn btn-primary px-5">등록</button>
										</div>
									</div>


					</div>

					</form>
				</div>