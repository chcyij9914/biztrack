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
<title>training list</title>

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
			<div id="content">

				<!-- Topbar -->
				<c:import url="/WEB-INF/views/common/topbar.jsp" />

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<div class="d-flex justify-content-between align-items-center mb-2">
						<div>
							<h1 class="h3 text-gray-800 mb-1">교육 목록</h1>
						</div>

						<div class="d-flex align-items-center">

							<button type="button" class="btn btn-primary"
								onclick="goToDetail()">상세보기</button>
							&nbsp;&nbsp;


							<script>
								function goToDetail() {
									const checked = document
											.querySelector('input[name="selectedId"]:checked');
									if (!checked) {
										alert("상세보기를 할 교육을 하나 선택해주세요.");
										return;
									}

									const trainingId = checked.value;
									location.href = '${pageContext.request.contextPath}/training/detail.do?id='
											+ trainingId;
								}
							</script>


							<form class="form-inline"
								action="${pageContext.request.contextPath}/purchase/search.do"
								method="get">
								<form method="get"
									action="${pageContext.request.contextPath}/training/list.do">
									<input type="text" name="keyword" placeholder="교육명 검색"
										class="form-control mr-2" />
									<button type="submit" class="btn btn-outline-primary">검색</button>
								</form>

								<!-- <input type="date" class="form-control mr-2" name="date" /> <input
									type="text" class="form-control mr-2" name="content"
									placeholder="교육명 검색" /> <select class="form-control mr-2"
									name="status">
									<option value="">수강상태</option>
									<option value="수강전">수강전</option>
									<option value="수강중">수강중</option>
									<option value="수강완료">수강완료</option>
								</select>
								<button type="submit" class="btn btn-outline-primary">검색</button> -->
							</form>
						</div>
					</div>


					<!-- Table -->
					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-bordered" id="trainingTable"
								style="table-layout: fixed; width: 100%;" cellspacing="0">
								<colgroup>
									<col style="width: 10%;">
									<col style="width: 48%;">
									<col style="width: 58%;">
									<col style="width: 20%;">
									<col style="width: 20%;">
									<col style="width: 20%;">
									<col style="width: 10%;">
									<col style="width: 38%;">
								</colgroup>
								<thead class="text-center bg-light">
									<table class="table table-bordered text-center">
										<thead class="thead-light">
											<tr>
												<th><input type="checkbox"></th>
												<th>교육명</th>
												<th>교육 내용</th>
												<th>강사명</th>
												<th>시작일</th>
												<th>종료일</th>
												<th>정원</th>
												<th>교육장소</th>
											</tr>
										</thead>
												<tbody class="text-center bg-white">


									<c:forEach var="training" items="${trainingList}">
										<tr>
										    <th><input type="checkbox" name="selectedId" value="${training.trainingId}"></th>
											<td>${training.title}</td>
											<td>${training.courseContent}</td>
											<td>${training.instructorName}</td>
											<td>${training.startDate}</td>
											<td>${training.endDate}</td>
											<td>${training.capacity}</td>
											<td>${training.location}</td>
											<%-- <td><span class="badge badge-secondary">${training.trainingStatus}</span></td> --%>
										<!-- 	<td><a href="#" class="btn btn-sm btn-outline-secondary">상세</a></td> -->
										</tr>
									</c:forEach>

								</tbody>
							</table>
						</div>

						<!-- 페이징 영역 -->
						<c:import url="/WEB-INF/views/common/pagingView.jsp" />
					</div>

				</div>
				<!-- End Page Content -->

				<c:import url="/WEB-INF/views/common/footer.jsp" />

			</div>
		</div>
	</div>

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"><i
		class="fas fa-angle-up"></i></a>

	<!-- JS -->
	<script
		src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>
</body>
</html>
