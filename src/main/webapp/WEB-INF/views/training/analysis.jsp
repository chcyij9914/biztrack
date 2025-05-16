<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>교육 평가 분석</title>

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
							<h1 class="h3 text-gray-800 mb-1">교육 평가분석</h1>
							<p class="text-muted small mb-0">교육 효과 분석을 통한 커리큘럼 개선</p>
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
								<input type="hidden" name="training_id" value="${training.id}" />
								<head>
<meta charset="UTF-8">
<title>교육 평가 분석</title>
<link
	href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css"
	rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
								</head>
								<body class="bg-light">
									<div class="container mt-5">
										<h2 class="mb-4 text-primary">교육 평가 분석</h2>

										<div class="card mb-4">
											<div class="card-body">
												<h5 class="card-title">교육별 평균 평가 점수</h5>
												<canvas id="avgScoreChart"></canvas>
											</div>
										</div>

										<div class="card">
											<div class="card-body">
												<h5 class="card-title">교육 피드백 키워드 분석</h5>
												<canvas id="keywordChart"></canvas>
											</div>
										</div>
									</div>

									<script>
										// 예시 데이터 (서버에서 가져올 수 있음)
										const avgScoreData = {
											labels : [ '온보딩', '리더십', '보안',
													'엑셀', '고객응대', '프로젝트 관리',
													'보고서 작성법', '팀워크 향상',
													'세일즈 마케팅', 'AI기초' ],
											datasets : [ {
												label : '평균 점수',
												data : [ 3.0, 4.2, 2.5, 4.5,
														3.5, 4.2, 3.0, 3.5,
														3.2, 4.0, 2.9 ],
												backgroundColor : 'rgba(78, 115, 223, 0.5)'
											} ]
										};

										const keywordData = {
											labels : [ '설명이 친철함', '전문성있음',
													'자료가 부족함', '일정이 빠듯함 ',
													'실습이 유익함 ', '장소가 불편함',
													'참여도가 높음 ', '강의가 지루함' ],
											datasets : [ {
												label : '언급 수',
												data : [ 24, 18, 28, 12, 30,
														20, 30, 12 ],
												backgroundColor : 'rgba(54, 185, 204, 0.5)'
											} ]
										};

										new Chart(
												document
														.getElementById('avgScoreChart'),
												{
													type : 'bar',
													data : avgScoreData,
													options : {
														responsive : true,
														plugins : {
															legend : {
																display : false
															}
														}
													}
												});

										new Chart(
												document
														.getElementById('keywordChart'),
												{
													type : 'bar',
													data : keywordData,
													options : {
														responsive : true,
														plugins : {
															legend : {
																display : false
															}
														}
													}
												});
									</script>
								</body>
</html>
