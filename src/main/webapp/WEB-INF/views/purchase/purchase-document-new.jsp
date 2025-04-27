<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>새 문서 작성</title>

<!-- Font Awesome -->
<link
	href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet">

<!-- SB Admin 2 CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css"
	rel="stylesheet">
	
	
<%@ page import="java.text.SimpleDateFormat, java.util.Date"%>
<%
// 오늘 날짜 :  yyyy/MM/dd 
String today = new SimpleDateFormat("yy/MM/dd").format(new Date());
request.setAttribute("today", today);
%>
</head>

<body class="p-4 bg-light">

	<div class="container mt-2">
		<h2>새 문서 작성</h2>
		<br>
		<!-- 탭 -->
		<ul class="nav nav-tabs mb-4" id="docTab" role="tablist">
			<li class="nav-item"><a class="nav-link active"
				id="purchase-tab" data-toggle="tab" href="#purchase" role="tab">구매품의서</a>
			</li>
			<li class="nav-item"><a class="nav-link" id="payment-tab"
				data-toggle="tab" href="#payment" role="tab">지출결의서 작성</a></li>
		</ul>

		<!-- 탭 내용 -->
		<div class="tab-content" id="docTabContent">
			<div class="tab-pane fade show active" id="purchase" role="tabpanel">

				<!-- 결재 테이블 -->
				<div class="d-flex justify-content-end mb-3">
					<table class="table table-bordered text-center"
						style="width: 70%; font-size: 0.8rem;">
						<tbody style="background-color: #ffffff;">
							<tr style="background-color: #fdfdfe;">
								<th rowspan="3"
									style="vertical-align: middle; padding: 4px 8px;">결재</th>
								<th style="padding: 4px 8px;">사원</th>
								<th style="padding: 4px 8px;">팀장</th>
								<th style="padding: 4px 8px;">부서장</th>
							</tr>
							<tr>
								<td style="padding: 4px 8px;">기안</td>
								<td style="padding: 4px 8px;">기안</td>
								<td style="padding: 4px 8px;">기안</td>
							</tr>
							<tr>
								<td style="padding: 4px 8px;">${today}</td>
								<td style="padding: 4px 8px;">25/04/03</td>
								<td style="padding: 4px 8px;">25/04/04</td>
							</tr>
						</tbody>
					</table>
				</div>
				<br>
				<!-- 문서 작성 폼 -->
				<form method="post" enctype="multipart/form-data"
					action="yourActionUrl">

					<table class="table table-bordered text-center"
						style= font-size: 0.8rem;">
						<tr>
							<th style="padding: 4px 8px;">문서번호</th>
							<td style="padding: 4px 8px;">
							</th>
							<th style="padding: 4px 8px;">작성자</th>
							<td style="padding: 4px 8px;">
							</th>
						</tr>
						</tbody>
					</table>

					<div class="form-group d-flex align-items-center">
						<label for="documentId" class="mr-3 mb-0" style="width: 80px;">제목</label>
						<input type="text" name="title" id="title" class="form-control"
							placeholder="내용" />
					</div>
					<div class="form-group d-flex align-items-center">
						<label for="documentId" class="mr-3 mb-0" style="width: 80px;">구매처명</label>
						<input type="text" name="vendorName" id="vendorName"
							class="form-control" placeholder="내용" />
					</div>
					<div class="form-group">
						<label>내용</label>
						<textarea name="content" class="form-control" rows="5"
							placeholder="내용"></textarea>
					</div>

					<div class="form-group">
						<label>첨부파일</label>
						<div class="d-flex align-items-center">
							<input type="text" class="form-control" readonly value="파일명.pdf"
								style="flex: 1;" /> <label for="fileUpload"
								class="btn btn-outline-primary ml-2 mb-0"> <i
								class="fas fa-upload"></i>
							</label> <input type="file" name="file" id="fileUpload" class="d-none" />
						</div>
					</div>

					<button type="submit" class="btn btn-primary btn-block">상신</button>
				</form>
			</div>

			<div class="tab-pane fade" id="payment" role="tabpanel">
				<!-- 지출결의서 작성 폼도 여기에 넣으면 됨 -->
			</div>
		</div>
	</div>

	<!-- JS -->
	<script
		src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>
</body>
</html>