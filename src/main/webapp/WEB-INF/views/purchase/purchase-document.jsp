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
<title>purchase document</title>

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
							<h1 class="h3 text-gray-800 mb-1">구매 문서</h1>
							<p class="text-muted small mb-0">품의서 및 지출결의서</p>
						</div>

						<div class="d-flex align-items-center">
							<a href="#" class="btn btn-primary px-3 py-2 mr-3"
								onclick="window.open('${pageContext.request.contextPath}/purchase/purchase-document-new.do', 'newWindow', 'width=800,height=600'); return false;">
								+ 새문서 </a> 
			
							<form class="form-inline"
								action="${pageContext.request.contextPath}/purchase/search.do"
								method="get">
								<input type="date" class="form-control mr-2" name="date" /> <input
									type="text" class="form-control mr-2" name="content"
									placeholder="내용 검색" /> <select class="form-control mr-2"
									name="status">
									<option value="">결재상태</option>
									<option value="결재중">결재중</option>
									<option value="결재완료">결재완료</option>
									<option value="반려">반려</option>
								</select>
								<button type="submit" class="btn btn-outline-primary">검색</button>
							</form>
						</div>
					</div>

					<!-- Table -->
					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-bordered" id="purchaseTable"
								style="table-layout: fixed; width: 100%;" cellspacing="0">
								<colgroup>
									<col style="width: 5%;">
									<col style="width: 10%;">
									<col style="width: 20%;">
									<col style="width: 35%;">
									<col style="width: 20%;">
									<col style="width: 10%;">
								</colgroup>
								<thead class="text-center bg-light">
									<tr>
										<th><input type="checkbox"></th>
										<th>문서 종류</th>
										<th>문서번호</th>
										<th>제목</th>
										<th>거래처명</th>
										<th>상태</th>
									</tr>
								</thead>
								<%-- 		<tbody class="text-center bg-white">
									<!-- 예시 데이터 반복 -->
									<c:forEach var="doc" items="${documentList}">
										<tr>
											<td><input type="checkbox" /></td>
											<td>${doc.type}</td>
											<td>${doc.number}</td>
											<td>${doc.title}</td>
											<td>${doc.clientName}</td>
											<td><span class="badge badge-primary">${doc.status}</span></td>
										</tr>
									</c:forEach>
								</tbody> --%>
								<tbody class="text-center bg-white">
									<tr>
										<td><input type="checkbox"></td>
										<td>구매품의서</td>
										<td>20250409-3</td>
										<td>방염 처리 폴리에스터 원단 500m</td>
										<td>한화원단</td>
										<td><span class="badge badge-primary">결재중</span></td>
									</tr>
									<tr>
										<td><input type="checkbox"></td>
										<td>지출결의서</td>
										<td>20250407-1</td>
										<td>산업용 고해상 카메라 120EA</td>
										<td>(주)농촌공업</td>
										<td><span class="badge badge-primary">결재완료</span></td>
									</tr>
									<tr>
										<td><input type="checkbox"></td>
										<td>구매품의서</td>
										<td>20250401-3</td>
										<td>정전기 방지용 작업 장갑 300EA</td>
										<td>세미테크</td>
										<td><span class="badge badge-primary">결재완료</span></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>

					<!-- Pagination -->
					<nav aria-label="Page navigation example">
						<ul class="pagination justify-content-center">
							<li class="page-item"><a class="page-link" href="#">«
									Previous</a></li>
							<li class="page-item"><a class="page-link" href="#">1</a></li>
							<li class="page-item active"><a class="page-link" href="#">2</a></li>
							<li class="page-item"><a class="page-link" href="#">3</a></li>
							<li class="page-item"><a class="page-link" href="#">Next
									»</a></li>
						</ul>
					</nav>

				</div>

			</div>

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; BizTrack 2025</span>
					</div>
				</div>
			</footer>

		</div>

	</div>

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"><i
		class="fas fa-angle-up"></i></a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">로그아웃 하시겠습니까?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">현재 세션을 종료하려면 로그아웃을 클릭하세요.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">취소</button>
					<a class="btn btn-primary"
						href="${pageContext.request.contextPath}/logout.jsp">로그아웃</a>
				</div>
			</div>
		</div>
	</div>

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