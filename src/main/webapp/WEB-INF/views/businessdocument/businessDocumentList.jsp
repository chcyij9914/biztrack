<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>영업문서 목록</title>
<link
	href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet">
</head>
<body id="page-top">

	<div id="wrapper">
		<%@ include file="/WEB-INF/views/common/menubar.jsp"%>

		<div class="container-fluid mt-4">
			<h1 class="h4 text-gray-800 mb-4">영업문서 목록</h1>

			<!-- 출고서 목록 / 세금계산서 목록 + 검색/등록 한 줄 정렬 -->
			<div class="d-flex justify-content-between align-items-center mb-3">
				<!-- 왼쪽: 출고서/세금계산서 목록 -->
				<div>
					<a
						href="${pageContext.request.contextPath}/businessdocument/outboundList.do"
						class="btn btn-outline-primary btn-sm">출고서 목록</a> <a
						href="${pageContext.request.contextPath}/businessdocument/taxinvoiceList.do"
						class="btn btn-outline-primary btn-sm">세금계산서 목록</a>
				</div>

				<!-- 오른쪽: 검색창 + 문서 등록 -->
				<div class="d-flex">
					<form class="form-inline mr-2"
						action="${pageContext.request.contextPath}/businessdocument/BusinessDocumentList.do"
						method="get">
						<select name="searchType" class="form-control mr-2">
							<option value="title">제목</option>
							<option value="type">문서종류</option>
							<option value="client">거래처명</option>
						</select> <input type="text" name="keyword" class="form-control mr-2"
							placeholder="검색어 입력">
						<button type="submit" class="btn btn-primary">검색</button>
					</form>
					<a
						href="${pageContext.request.contextPath}/businessdocument/insertForm.do"
						class="btn btn-primary btn-sm">문서 등록</a>
				</div>
			</div>

			<!-- 문서 목록 카드 -->
			<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h6 class="m-0 font-weight-bold text-primary">문서 목록</h6>
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<table class="table table-bordered text-center" width="100%"
							cellspacing="0">
							<thead class="thead-light">
								<tr>
									<th>문서번호</th>
									<th>문서종류</th>
									<th>작성일자</th>
									<th>제목</th>
									<th>거래처명</th>
									<th>상태</th>
									<th>관리</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="doc" items="${documentList}">
									<tr>
										<td>${doc.documentId}</td>
										<td><c:choose>
												<c:when test="${doc.documentTypeId eq 'O'}">출고서</c:when>
												<c:when test="${doc.documentTypeId eq 'G'}">세금계산서</c:when>
												<c:otherwise>기타</c:otherwise>
											</c:choose></td>
										<td><fmt:formatDate value="${doc.createdDate}"
												pattern="yyyy-MM-dd" /></td>
										<td>${doc.title}</td>
										<td>${doc.clientName}</td>
										<td><span class="badge badge-secondary">${doc.status}</span></td>
										<td>
										<a href="${pageContext.request.contextPath}/businessdocument/detail.do?documentId=${doc.documentId}"
											class="btn btn-sm"
											style="background-color: #e7f1ff; color: #004085; border: 1px solid #b8daff;">
												상세 </a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>

					<!-- 페이징 -->
					<c:import url="/WEB-INF/views/common/pagingView.jsp" />
				</div>
			</div>
		</div>
	</div>

</body>
</html>
