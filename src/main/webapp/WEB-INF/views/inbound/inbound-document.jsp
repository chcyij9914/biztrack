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
<title>inbound document</title>

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
					<div class="mb-2">
						<!-- 상단: 제목 + 검색/작성 버튼 -->
						<div class="d-flex justify-content-between align-items-center">
							<!-- 제목 -->
							<div>
								<h1 class="h3 text-gray-800 mb-1">입고</h1>
								<p class="text-muted small mb-0">물품 입고 목록</p>
							</div>

							<!-- 우측: 문서작성 + 검색 폼 -->
							<div class="d-flex align-items-center">
								<a href="#" class="btn btn-primary px-3 py-2 mr-3"
									onclick="window.open('${pageContext.request.contextPath}/inbound/new-inbound.do', 'newWindow', 'width=1000,height=800'); return false;">
									+ 입고서 </a>

								<!-- 검색 기준 -->
								<label class="mr-2 mb-0">검색 기준</label> <select id="searchType"
									class="form-control mr-2" style="width: 120px;">
									<option value="documentId">문서번호</option>
									<option value="title">제목</option>
									<option value="status">상태</option>
								</select>

								<form id="searchForm" method="get" class="form-inline"
									action="searchByDocumentId.do">
									<!-- 텍스트 입력 -->
									<input type="text" id="keywordInput" name="keyword"
										class="form-control mr-2" placeholder="문서번호를 입력하세요.">

									<!-- 상태 드롭다운 -->
									<select name="statusParam" id="statusSelect"
										class="form-control mr-2 d-none">
										<option value="상신">상신</option>
										<option value="결재중">결재중</option>
										<option value="결재완료">결재완료</option>
										<option value="반려">반려</option>
									</select>

									<button type="submit" class="btn btn-primary"
										onclick="updateSearchAction()">검색</button>
								</form>
							</div>
						</div>

						<!-- Table -->
					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-bordered" id="purchaseTable"
								style="table-layout: fixed; width: 100%;" cellspacing="0">
								<colgroup>
									<col style="width: 17%;">
									<col style="width: 15%;">
									<col style="width: 14%;">
									<col style="width: 23%;">
									<col style="width: 14%;">
									<col style="width: 12%;">
									<col style="width: 8%;">
								</colgroup>
								<thead class="text-center bg-light">
									<tr>
										<th>작성일자</th>
										<th>문서 종류</th>
										<th>문서번호</th>
										<th>제목</th>
										<th>구매처명</th>
										<th>상태</th>
										<th>관리</th>
									</tr>
								</thead>
								<tbody class="text-center bg-white">
									<!-- 예시 데이터 반복 -->
									<c:forEach var="inbound" items="${documentList}">
										<tr>
											<td>${inbound.createdDate}</td>
											<td>${inbound.documentName}</td>
											<td>${inbound.documentId}</td>
											<td>${inbound.title}</td>
											<td>${inbound.clientName}</td>
											<td><span class="badge badge-primary">${inbound.status}</span></td>
											<td><a
												href="${pageContext.request.contextPath}/inbound/inbound-detail.do?documentId=${inbound.documentId}"
												class="btn btn-sm btn-outline-secondary">상세</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

							<!-- Paging -->
							<c:import url="/WEB-INF/views/common/pagingView.jsp" />

							<!-- Footer -->
							<c:import url="/WEB-INF/views/common/footer.jsp" />

							<!-- Scroll to Top Button-->
							<a class="scroll-to-top rounded" href="#page-top"><i
								class="fas fa-angle-up"></i></a>

							<!-- Logout Modal-->
							<div class="modal fade" id="logoutModal" tabindex="-1"
								role="dialog" aria-labelledby="exampleModalLabel"
								aria-hidden="true">
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
							<script>
								function updateSearchAction() {
									const selected = document
											.getElementById("searchType").value;
									const searchForm = document
											.getElementById("searchForm");
									const keywordInput = document
											.getElementById("keywordInput");
									const statusSelect = document
											.getElementById("statusSelect");

									if (selected === "documentId") {
										searchForm.action = "searchByDocumentId.do";
										keywordInput.classList.remove("d-none");
										keywordInput.name = "keyword";
										keywordInput.placeholder = "문서번호를 입력하세요.";
										statusSelect.classList.add("d-none");
										statusSelect.name = "statusParam";
									} else if (selected === "title") {
										searchForm.action = "searchByTitle.do";
										keywordInput.classList.remove("d-none");
										keywordInput.name = "keyword";
										keywordInput.placeholder = "내용을 입력하세요.";
										statusSelect.classList.add("d-none");
										statusSelect.name = "statusParam";
									} else if (selected === "status") {
										searchForm.action = "searchByStatus.do";
										keywordInput.classList.add("d-none");
										keywordInput.name = "";
										statusSelect.classList.remove("d-none");
										statusSelect.name = "status";
									}
								}

								// select 박스 바뀔 때마다 호출되도록 연결
								document
										.addEventListener(
												'DOMContentLoaded',
												function() {
													document
															.getElementById(
																	'searchType')
															.addEventListener(
																	'change',
																	updateSearchAction);
												});
							</script>
</body>
</html>