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
<title>거래처 관리</title>

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
							<h1 class="h3 text-gray-800 mb-1">거래처 관리</h1>
							<p class="text-muted small mb-0">거래처 목록</p>
						</div>

						<div class="d-flex align-items-center">

							<!-- 거래처 검색 필터 (한 줄 정렬) -->
							<div class="d-flex align-items-center mb-3">

								<!-- 등록 버튼 -->
								<a href="#" class="btn btn-primary px-3 py-2 mr-3"
									onclick="window.open('${pageContext.request.contextPath}/client/insertForm.do', 'insertClientWindow', 'width=800,height=900'); return false;">
									+ 거래처 등록 </a>

								<!-- 검색 기준 -->
								<label class="mr-2 mb-0">검색 기준</label> <select id="searchType"
									class="form-control mr-2" style="width: 120px;">
									<option value="name">거래처명</option>
									<option value="status">계약상태</option>
									<option value="category">카테고리명</option>
								</select>

								<form id="searchForm" method="get" class="form-inline"
									action="csearchName.do">
									<input type="text" name="keyword" class="form-control mr-2"
										placeholder="거래처명을 입력하세요."> <select name="statusParam"
										class="form-control mr-2 d-none">
										<option value="계약중">계약중</option>
										<option value="만료">만료</option>
									</select> <select name="categoryId" class="form-control mr-2 d-none">
										<c:forEach var="cat" items="${categoryList}">
											<option value="${cat.categoryId}">${cat.categoryName}</option>
										</c:forEach>
									</select>

									<button type="submit" class="btn btn-primary">검색</button>
								</form>

							</div>
							<script>
							$(function() {
							    $('#searchType').on('change', function() {
							        const type = $(this).val();
							        const $form = $('#searchForm');

							        // 모든 입력/선택 필드 숨기기
							        $form.find('input[name="keyword"]').addClass('d-none');
							        $form.find('select[name="statusParam"]').addClass('d-none');
							        $form.find('select[name="categoryId"]').addClass('d-none');

							        // 타입별로 폼 액션 및 필드 표시 설정
							        if (type === 'name') {
							            $form.attr('action', 'csearchName.do');
							            $form.find('input[name="keyword"]').removeClass('d-none');
							        } else if (type === 'status') {
							            $form.attr('action', 'csearchStatus.do');
							            $form.find('select[name="statusParam"]').removeClass('d-none');
							        } else if (type === 'category') {
							            $form.attr('action', 'csearchCategory.do');
							            $form.find('select[name="categoryId"]').removeClass('d-none');
							        }
							    });
							});
							</script>
						</div>
					</div>
					<!-- Table -->
					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-bordered"
								style="table-layout: fixed; width: 100%;" cellspacing="0">
								<colgroup>
									<col style="width: 20%;">
									<col style="width: 20%;">
									<col style="width: 20%;">
									<col style="width: 20%;">
									<col style="width: 10%;">
									<col style="width: 10%;">
								</colgroup>
								<thead class="text-center bg-light">
									<tr>
										<th>거래처명</th>
										<th>대표자</th>
										<th>사업자번호</th>
										<th>연락처</th>
										<th>상태</th>
										<th>관리</th>
									</tr>
								</thead>
								<tbody class="text-center bg-white">
									<c:forEach var="client" items="${clientList}">
										<tr>
											<td>${client.clientName}</td>
											<td>${client.ceoName}</td>
											<td>${client.businessNumber}</td>
											<td>${client.companyPhone}</td>
											<td><span class="badge badge-secondary">${client.clientStatus}</span></td>
											<td><a
												href="${pageContext.request.contextPath}/client/cdetail.do?clientId=${client.clientId}"
												class="btn btn-sm btn-outline-secondary">상세</a></td>
										</tr>
									</c:forEach>
									<c:if test="${empty clientList}">
										<tr>
											<td colspan="6" class="text-center text-muted">검색 결과가
												없습니다.</td>
										</tr>
									</c:if>
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
