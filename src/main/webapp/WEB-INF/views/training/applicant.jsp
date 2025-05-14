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

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
	$(function() {
		$('#detailBtn')
				.on(
						'click',
						function() {
							const selected = $('input[name="selectedId"]:checked');
							if (selected.length === 0) {
								alert('교육을 하나 선택해주세요.');
								return;
							}

							const trainingId = selected.val();
							const url = '${pageContext.request.contextPath}/training/detail.do?id='
									+ trainingId;
							window.location.href = url;
						});
	});
</script>

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
							<h1 class="h3 text-gray-800 mb-1">수강신청 내역 확인</h1>
						</div>

						<div class="d-flex align-items-center">

							<!-- <button type="button" class="btn btn-primary" id="detailBtn">상세보기</button>
							&nbsp;&nbsp; -->
							<!-- <button type="submit" class="btn btn-danger">삭제</button>&nbsp;&nbsp; -->

							<%-- 
							<form class="form-inline"
								action="${pageContext.request.contextPath}/training/searchAll.do"
								method="get">
								<select class="form-control mr-2" name="action">
									<option value="title">교육명</option>
									<option value="content">교육내용</option>
									<option value="Instructor">강사명</option>
								</select> <input type="search" name="keyword" placeholder=" 검색"
									class="form-control mr-2" /> <input type="submit"
									class="btn btn-outline-primary" value="검색">
							</form>
 --%>

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
											<%--  조회된 목록 출력 부분 --%>
											<c:forEach var="training" items="${trainingList}">
												<tr>
													<td>${training.title}</td>
													<td>${training.courseContent}</td>
													<td>${training.instructorName}</td>
													<td>${training.startDate}</td>
													<td>${training.endDate}</td>
													<td>${training.capacity}</td>
													<td>${training.location}</td>
													
													<%-- <td>${training.TITLE}</td>
													<td>${training.COURSE_CONTENT}</td>
													<td>${training.INSTRUCTOR_NAME}</td>
													<td>${training.START_DATE}</td>
													<td>${training.END_DATE}</td>
													<td>${training.CAPACITY}</td>
													<td>${training.LOCATION}</td>
													 --%>
												</tr>
											</c:forEach>

										</tbody>
									</table>
										<div class="d-flex justify-content-center mb-4">
											<c:if test="${not empty param.action}">
												<button type="button"
													class="btn btn-secondary btn-icon-split mr-2"
													onclick="history.back();">
													<span class="icon text-white-50"> <i
														class="fas fa-arrow-left"></i>
													</span> <span class="text">이전페이지</span>
												</button>

												<!-- 목록으로 버튼: 검색 결과일 때만 보이기 -->

												<a href="${pageContext.request.contextPath}/list.do"
													class="btn btn-info btn-icon-split"> <span
													class="icon text-white-50"> <i class="fas fa-list"></i>
												</span> <span class="text">목록으로</span>
												</a>
											</c:if>
										</div>

									<!-- 페이징 영역 -->
									<c:import url="/WEB-INF/views/common/pagingView.jsp" />
									</div>
									
									<!-- ❗ 수강취소 안내 문구 및 테스트 메모 -->
									<div class="mt-4 p-3 border rounded bg-light">
										<p class="text-danger font-weight-bold mb-2">※ 수강 취소를 원하실
											경우 아래 관리자에게 연락 바랍니다.</p>
										<p class="mb-1">
											👤 담당자: <strong> 한지아 팀장 </strong>
										</p>
										<p class="mb-1">
											📞 담당자 연락처: <strong>010-8548-6552</strong>
										</p>
										<p class="mb-3">
											📧 이메일: <strong>jiahan28@naver.com</strong>
										</p>

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
