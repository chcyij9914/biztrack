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
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>공지사항 등록</title>

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
.form-group.row label {
	font-weight: bold;
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
					<div class="d-flex justify-content-between align-items-center mb-4">
						<h1 class="h3 text-gray-800">공지글 등록</h1>
					</div>

					<div class="card shadow mb-4">
						<div class="card-body">
							<form
								action="${pageContext.request.contextPath}/ninsert.do"
								method="post" enctype="multipart/form-data">

								<!-- 제목 -->
								<div class="form-group row">
									<label class="col-sm-2 col-form-label">제목 : </label>
									<div class="col-sm-10">
										<input type="text" name="noticeTitle" class="form-control"
											required />
									</div>
								</div>
								
								<!-- 내용 -->
								<div class="form-group row">
									<label class="col-sm-2 col-form-label">공지 내용 : </label>
									<div class="col-sm-10">
										<textarea name="noticeContent" rows="2" class="form-control"
											required></textarea>
									</div>
								</div>										

								<div class="form-group row">
									<label class="col-sm-2 col-form-label">등록일 : </label>
									<div class="col-sm-5">
										<input type="date" class="form-control" name="noticeDate"
											value="${notice.noticeDate}">
									</div>
								</div>
								
								
								<div class="form-group row">
									<label class="col-sm-2 col-form-label">작성자 : </label>
									<div class="col-sm-10">
										<input type="text" name="noticeWriter" class="form-control"
											 value="${sessionScope.loginUser.userId}" />
									</div>
								</div>

								<!-- 중요 공지 여부 -->
								<div class="form-group row">
									<label class="col-sm-2 col-form-label">중요 공지 여부 : </label>
									<div class="col-sm-10">
										<div class="form-check">
											<input class="form-check-input" type="checkbox"
												name="importance" value="Y" id="impY" /> <label
												class="form-check-label" for="impY"> 중요 </label>
										</div>
									</div>
								</div>

								<div class="form-group row">
									<label class="col-sm-2 col-form-label"> 중요 공지 종료일 : </label>
									<div class="col-sm-5">
										<input type="date" class="form-control" name="impEndDate"
											value="${notice.impEndDate}">
									</div>
								</div>


								<!-- 첨부파일
								<div class="form-group row">
									<label class="col-sm-2 col-form-label">첨부파일 : </label>
									<div class="col-sm-10">
										<input type="file" name="ofile" class="form-control-file" />
									</div>
								</div> -->

								
								<!-- 버튼 그룹 -->
								<div class="form-group row">
									<div class="col-sm-12 text-center">
										<button type="submit" class="btn btn-primary px-5">등록</button>
										&nbsp;


										<!--    <button type="button"
                            class="btn btn-info px-5"
                            onclick="history.back();">목록</button> -->
									</div>
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
