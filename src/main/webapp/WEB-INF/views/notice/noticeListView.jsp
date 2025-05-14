<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>notice list</title>

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
		$('#detailBtn').on('click', function() {
			// 1) 선택된 라디오 찾기
			const $sel = $('input[name="selectedId"]:checked');
			if ($sel.length === 0) {
				alert('공지사항을 하나 선택해주세요.');
				return;
			}

			// 2) noticeNo 값 꺼내서 URL 생성
			const noticeNo = $sel.val();
			const ctx = '${pageContext.request.contextPath}';
			const url = ctx + '/detail.do?no=' + noticeNo;

			// 3) 페이지 이동
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
							<h1 class="h3 text-gray-800 mb-1">공지사항</h1>
						</div>

						<div class="d-flex align-items-center">

							<%-- <c:if
								test="${ !empty sessionScope.loginUser and loginUser.adminYN eq 'Y' }"> --%>
							<button type="button" class="btn btn-primary" id="writeBtn"
								onclick="location.href='${pageContext.servletContext.contextPath}/moveWrite.do';">
								+ 새 공지글 등록</button>
							&nbsp;&nbsp;
							<%-- </c:if> --%>

<!-- 
							<button type="button" class="btn btn-primary" id="detailBtn">상세보기</button>
							&nbsp;&nbsp; -->
							<!-- <button type="submit" class="btn btn-danger">삭제</button>&nbsp;&nbsp; -->


							<form id="searchForm" class="form-inline" method="get">
								<!-- 1) 검색 옵션: action 파라미터로 넘길 값(title, writer, content, date) -->
								<select id="searchAction" name="action"
									class="form-control mr-2">
									<option value="title">공지 제목</option>
									<option value="writer">작성자</option>
									<option value="content">공지 내용</option>
									<option value="date">공지 등록일</option>
								</select>

								<!-- 2) 키워드 입력: 기본 노출 -->
								<input type="search" id="keywordInput" name="keyword"
									placeholder="검색 " class="form-control mr-2" />

								<!-- 3) 날짜 입력: 기본 숨김, date 옵션일 때만 보임 -->
								<input type="date" id="beginInput" name="begin"
									class="form-control mr-2" style="display: none" /> <input
									type="date" id="endInput" name="end" class="form-control mr-2"
									style="display: none" />

								<button type="submit" class="btn btn-outline-primary">검색</button>
							</form>

							<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
							<script>
								$(function() {
									// 옵션 변경 시: 입력창 토글
									$('#searchAction').change(
											function() {
												if (this.value === 'date') {
													$('#keywordInput').hide()
															.prop('disabled',
																	true);
													$('#beginInput, #endInput')
															.show().prop(
																	'disabled',
																	false);
												} else {
													$('#keywordInput').show()
															.prop('disabled',
																	false);
													$('#beginInput, #endInput')
															.hide().prop(
																	'disabled',
																	true);
												}
											}).trigger('change');

									// 폼 제출 직전: 실제 매핑된 URL로 action 덮어쓰기
									$('#searchForm')
											.submit(
													function() {
														const ctx = '${pageContext.request.contextPath}';
														const act = $(
																'#searchAction')
																.val();
														let target = '/nsearchTitle.do';

														switch (act) {
														case 'title':
															target = '/nsearchTitle.do';
															break;
														case 'writer':
															target = '/nsearchWriter.do';
															break;
														case 'content':
															target = '/nsearchContent.do';
															break;
														case 'date':
															target = '/nsearchDate.do';
															break;
														}
														this.action = ctx
																+ target;
													});
								});
							</script>
						</div>
					</div>

					<%-- 조회된 공지사항 목록 출력 --%>
					<!-- Table -->
					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-bordered" id="trainingTable"
								style="table-layout: fixed; width: 100%;" cellspacing="0">
								<colgroup>
									<col style="width: 15%;">
									<col style="width: 48%;">
									<col style="width: 62%;">
									<col style="width: 30%;">
									<col style="width: 20%;">
									<col style="width: 20%;">
									<col style="width: 25%;">
									<col style="width: 15%;">
								</colgroup>
								<thead class="text-center bg-light">
									<table class="table table-bordered text-center">
										<thead class="thead-light">
											<tr>
												<!-- <th><input type="radio"></th> -->
												<th>공지 번호</th>
												<th>제목</th>
												<th>공지 내용</th>
												<th>등록일</th>
												<th>작성자</th>
												<!-- <th>중요 공지여부</th> -->
												<th>중요 공지여부 종료일</th>
												<th>조회수</th>

											</tr>
										</thead>
										<tbody class="text-center bg-white">
											<!-- 🔸 중요공지 먼저 출력 -->
											<c:forEach var="notice" items="${noticeList}">
												<c:if test="${notice.importance == 'Y'}">
													<tr style="background-color: #fff3cd;">
														<%-- <td><input type="radio" name="selectedId"
															value="${item.noticeNo}"></td> --%>
														<td><span class="badge badge-danger">중요</span></td>
														<td><a
															href="${pageContext.request.contextPath}/detail.do?no=${notice.noticeNo}">
															${notice.noticeTitle} </a>
														</td>
														<td>${notice.noticeContent}</td>
														<td>${notice.noticeDate}</td>
														<td>${notice.noticeWriter}</td>
														<td><fmt:formatDate value="${notice.noticeDate}"
																pattern="yyyy-MM-dd" /></td>
														<td>${notice.readCount}</td>
													</tr>
												</c:if>
											</c:forEach>

											<!-- 🔹 일반 공지 출력 -->
											<c:forEach var="notice" items="${noticeList}">
												<c:if test="${notice.importance != 'Y'}">
													<tr>
														<%-- <td><input type="radio" name="selectedId"
															value="${item.noticeNo}"></td> --%>
														<td>${notice.noticeNo}</td>
														<td><a
															href="${pageContext.request.contextPath}/detail.do?no=${notice.noticeNo}">
																${notice.noticeTitle} </a></td>
														<td>${notice.noticeContent}</td>
														<td>${notice.noticeDate}</td>
														<td>${notice.noticeWriter}</td>
														<td><fmt:formatDate value="${notice.noticeDate}"
																pattern="yyyy-MM-dd" /></td>
														<td>${notice.readCount}</td>
													</tr>
												</c:if>
											</c:forEach>


											<%--  조회된 목록 출력 부분 --%>
											<%-- <c:forEach var="item" items="${notice}">
												<tr>
													<td><input type="radio" name="selectedId"
														value="${item.noticeNo}"></td>
													<td>${item.noticeNo}</td>
													<td>${item.noticeTitle}</td>
													<td>${item.noticeContent}</td>
													<td>${item.noticeDate}</td>
													<td>${item.noticeWriter}</td>
													<td>${item.importance}</td>
													<td>${item.impEndDate}</td>
													<td>${item.readCount}</td>
												</tr>
											</c:forEach>
 --%>
										</tbody>
									</table>
									</div>

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

											<a href="${pageContext.request.contextPath}/nlist.do"
												class="btn btn-info btn-icon-split"> <span
												class="icon text-white-50"> <i class="fas fa-list"></i>
											</span> <span class="text">목록으로</span>
											</a>
										</c:if>
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
