<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty sessionScope.loginInfo}">
	<c:redirect url="/login.do" />
</c:if>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>notice detail</title>

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
							<h1 class="h3 text-gray-800 mb-1">공지사항 상세보기</h1>
							<!-- <p class="text-muted small mb-0">교육 신청 전 상세 커리큘럼과 일정을 꼭
								확인하세요.</p> -->
						</div>

					</div>


					<%-- <c:set var="isAdmin" value="${loginInfo.admin_YN eq 'Y'}" />
					<c:set var="editMode" value="${param.editMode}" />

					<!-- 수정 / 삭제 버튼 (관리자만 보임) -->
					<c:if test="${isAdmin}"> --%>

					<div class="d-flex justify-content-end mb-3">
						<c:if
							test="${loginInfo.roleId == 'A1' 
				|| loginInfo.roleId == 'A2' 
				|| loginInfo.roleId == 'A3'}">
							<form method="post"
								action="${pageContext.request.contextPath}/notice/delete.do"
								style="display: inline;">
								<input type="hidden" name="no" value="${notice.noticeNo}" />
								<button type="submit" class="btn btn-danger btn-icon-split">
									<span class="icon text-white-50"><i class="fas fa-trash"></i></span>
									<span class="text">삭제</span>
								</button>
							</form>
						</c:if>
					</div>



					<%-- <div class="d-flex justify-content-end mb-3">
						<form method="post"
							action="${pageContext.request.contextPath}/notice/delete.do"
							style="display: inline;">
							<input type="hidden" name="no" value="${notice.noticeNo}" />
							<button type="submit" class="btn btn-danger btn-icon-split">
								<span class="icon text-white-50"><i class="fas fa-trash"></i></span>
								<span class="text">삭제</span>
							</button>
						</form>
					</div> --%>


					<!-- 상세 정보 -->
					<div class="card shadow mb-4">
						<div class="card-body">
							<form
								action="${pageContext.request.contextPath}/notice/update.do"
								method="post">

								<input type="hidden" name="noticeNo" value="${notice.noticeNo}" />


								<div class="form-group">
									<label> 공지사항 번호 </label> <input type="text"
										class="form-control" name="noticeNo"
										value="${notice.noticeNo}" />
								</div>

								<div class="form-group">
									<label> 제목</label> <input type="text" class="form-control"
										name="noticeTitle" value="${notice.noticeTitle}" />
								</div>

								<div class="form-group">
									<label>작성자</label> <input type="text" class="form-control"
										name="noticeWriter" value="${notice.noticeWriter}" />
								</div>

								<div class="form-group">
									<label>등록일</label> <input type="date" class="form-control"
										name="noticeDate" value="${notice.noticeDate}" />
								</div>

								<div class="form-group">
									<label> 공지 내용</label> <input type="text" class="form-control"
										name="noticeContent" value="${notice.noticeContent}" />
								</div>

								<div class="form-group">
									<label>조회수</label> <input type="number" class="form-control"
										name="readCount" value="${notice.readCount}" />
								</div>

								<div class="form-group">
									<label>중요 공지 여부</label>
									<div class="form-check">
										<input class="form-check-input" type="checkbox"
											id="importanceCheck" name="importance" value="Y"
											${notice.importance eq 'Y' ? 'checked' : ''}> <label
											class="form-check-label" for="importanceCheck"> </label>
									</div>
								</div>


								<div class="form-group">
									<label>중요 공지 종료일</label> <input type="date"
										class="form-control" name="impEndDate"
										value="${notice.impEndDate}" />
								</div>


								<div class="d-flex justify-content-center mb-4">

									<!-- 수정 / 삭제 버튼 (관리자만 보임) -->

									<c:if
										test="${loginInfo.roleId == 'A1' 
			|| loginInfo.roleId == 'A2' 
			|| loginInfo.roleId == 'A3'}">
										<form method="post"
											action="${pageContext.request.contextPath}/notice/update.do"
											style="display: inline;">
											<input type="hidden" name="no" value="${notice.noticeNo}" />
											<button type="submit"
												class="btn btn-primary btn-icon-split mr-2">
												<span class="icon text-white-50"><i
													class="fas fa-pen"></i></span> <span class="text">수정</span>
											</button>
										</form>
									</c:if>




									&nbsp;&nbsp;


									<%-- <form method="post"
										action="${pageContext.request.contextPath}/notice/update.do"
										style="display: inline;">
										<input type="hidden" name="no" value="${notice.noticeNo}" />
										<button type="submit"
											class="btn btn-primary btn-icon-split mr-2">
											<span class="icon text-white-50"><i class="fas fa-pen"></i></span>
											<span class="text">수정</span>
										</button>
									</form>
									&nbsp;&nbsp; --%>


									</a> <a href="javascript:history.back();"
										class="btn btn-secondary btn-icon-split mr-2"> <span
										class="icon text-white-50"> <i
											class="fas fa-arrow-left"></i>
									</span> <span class="text">이전페이지</span>
									</a>


									<!-- 목록으로 버튼 -->
									<a href="${pageContext.request.contextPath}/nlist.do"
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
