<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>출고서 목록</title>
<link
	href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
</head>
<body id="page-top">

	<div id="wrapper">
		<%@ include file="/WEB-INF/views/common/menubar.jsp"%>

		<div class="container-fluid mt-4">
			<!-- 타이틀 + 버튼 정렬 -->
			<h1 class="h4 text-gray-800 mb-3">문서 목록</h1>

			<div class="d-flex justify-content-between align-items-center mb-3">
				<!-- 좌측: 문서 탭 버튼 -->
				<div>
					<a
						href="${pageContext.request.contextPath}/businessdocument/OutboundList.do"
						class="btn btn-primary btn-sm">출고서 목록</a> <a
						href="${pageContext.request.contextPath}/businessdocument/TaxinvoiceList.do"
						class="btn btn-outline-primary btn-sm">세금계산서 목록</a>
				</div>

				<!-- 우측: 검색창 + 등록 버튼 -->
				<div class="d-flex">
					<form class="form-inline mr-2"
						action="${pageContext.request.contextPath}/businessdocument/outboundList.do"
						method="get">
						<select name="searchType" id="searchType"
							class="form-control mr-2">
							<option value="title"
								${param.searchType == 'title' ? 'selected' : ''}>제목</option>
							<option value="client"
								${param.searchType == 'client' ? 'selected' : ''}>거래처명</option>
							<option value="status"
								${param.searchType == 'status' ? 'selected' : ''}>상태</option>
						</select> <input type="text" class="form-control mr-2" id="textInput"
							placeholder="제목 입력" style="display: none;"
							value="${param.searchType == 'title' ? param.keyword : ''}" /> <input
							type="text" class="form-control mr-2" id="clientInput"
							placeholder="거래처명 입력" style="display: none;"
							value="${param.searchType == 'client' ? param.keyword : ''}" />
						<select name="approveStep" id="approveStepSelect"
							class="form-control mr-2" style="display: none;">
							<option value="">단계 선택</option>
							<option value="1" ${param.approveStep == '1' ? 'selected' : ''}>1차
								결재</option>
							<option value="2" ${param.approveStep == '2' ? 'selected' : ''}>2차
								결재</option>
						</select> <select name="approveStatus" id="approveStatusSelect"
							class="form-control mr-2" style="display: none;">
							<option value="">상태 선택</option>
							<option value="임시저장"
								${param.approveStatus == '임시저장' ? 'selected' : ''}>임시저장</option>
							<option value="대기"
								${param.approveStatus == '대기' ? 'selected' : ''}>대기</option>
							<option value="검토"
								${param.approveStatus == '검토' ? 'selected' : ''}>검토</option>
							<option value="승인"
								${param.approveStatus == '승인' ? 'selected' : ''}>승인</option>
							<option value="반려"
								${param.approveStatus == '반려' ? 'selected' : ''}>반려</option>
						</select> <input type="hidden" name="keyword" id="keywordHidden" />
						<button type="submit" class="btn btn-primary btn-sm">검색</button>
					</form>

					<!-- 문서 등록 버튼: 새 창으로 열기 -->
					<button type="button" class="btn btn-primary btn-sm"
						onclick="window.open(
							'${pageContext.request.contextPath}/businessdocument/outboundInsertForm.do',
							'출고서 등록',
							'width=1000,height=900,scrollbars=yes,resizable=yes'
						);">
						문서 등록</button>
				</div>
			</div>

			<!-- 목록 테이블 -->
			<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h6 class="m-0 font-weight-bold text-primary">출고서 목록</h6>
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<table class="table table-bordered text-center" width="100%"
							cellspacing="0">
							<thead class="thead-light">
								<tr>
									<th>문서번호</th>
									<th>출고일자</th>
									<th>제목</th>
									<th>거래처명</th>
									<th>거래처주소</th>
									<th>출고수량</th>
									<th>총금액</th>
									<th>작성자</th>
									<th>상태</th>
									<th>관리</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="doc" items="${documentList}">
									<tr>
										<td>${doc.documentId}</td>
										<td><fmt:formatDate value="${doc.documentDate}"
												pattern="yyyy-MM-dd" /></td>
										<td>${doc.title}</td>
										<td>${doc.clientName}</td>
										<td>${doc.address}</td>
										<td>${doc.productQuantity}</td>
										<td><fmt:formatNumber value="${doc.totalAmount}"
												pattern="#,###" />원</td>
										<td>${doc.writerName}</td>
										<td><span class="badge badge-secondary">${doc.status}</span></td>
										<td><a
											href="${pageContext.request.contextPath}/businessdocument/detail.do?documentId=${doc.documentId}"
											class="btn btn-sm"
											style="background-color: #e7f1ff; color: #004085; border: 1px solid #b8daff;">상세</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>

					<!-- 페이징 부분 -->
					<nav aria-label="Page navigation">
						<ul class="pagination justify-content-center">

							<!-- 맨 처음 페이지로 이동 -->
							<c:if test="${pageInfo.currentPage > 1}">
								<li class="page-item"><a class="page-link" href="?page=1">&laquo;</a>
								</li>
							</c:if>

							<!-- 이전 페이지로 이동 -->
							<c:if test="${pageInfo.currentPage > 1}">
								<li class="page-item"><a class="page-link"
									href="?page=${pageInfo.currentPage - 1}">&lsaquo;</a></li>
							</c:if>

							<!-- 페이지 번호 목록 -->
							<c:forEach var="p"
								begin="${pageInfo.currentPage - 2 < 1 ? 1 : pageInfo.currentPage - 2}"
								end="${pageInfo.currentPage + 2 > pageInfo.totalPage ? pageInfo.totalPage : pageInfo.currentPage + 2}">
								<li
									class="page-item ${p == pageInfo.currentPage ? 'active' : ''}">
									<a class="page-link" href="?page=${p}">${p}</a>
								</li>
							</c:forEach>

							<!-- 다음 페이지로 이동 -->
							<c:if test="${pageInfo.currentPage < pageInfo.totalPage}">
								<li class="page-item"><a class="page-link"
									href="?page=${pageInfo.currentPage + 1}">&rsaquo;</a></li>
							</c:if>

							<!-- 마지막 페이지로 이동 -->
							<c:if test="${pageInfo.currentPage < pageInfo.totalPage}">
								<li class="page-item"><a class="page-link"
									href="?page=${pageInfo.totalPage}">&raquo;</a></li>
							</c:if>

						</ul>
					</nav>
				</div>
			</div>
		</div>
	</div>
	</div>

	<!-- 검색 조건 스크립트 -->
	<script>
		function toggleSearchInputs(type) {
			document.getElementById('textInput').style.display = (type === 'title') ? 'block'
					: 'none';
			document.getElementById('clientInput').style.display = (type === 'client') ? 'block'
					: 'none';
			document.getElementById('approveStepSelect').style.display = (type === 'status') ? 'block'
					: 'none';
			document.getElementById('approveStatusSelect').style.display = (type === 'status') ? 'block'
					: 'none';
		}

		function syncKeywordInput() {
			const type = document.getElementById('searchType').value;
			const hidden = document.getElementById('keywordHidden');
			if (type === 'title') {
				hidden.value = document.getElementById('textInput').value;
			} else if (type === 'client') {
				hidden.value = document.getElementById('clientInput').value;
			} else {
				hidden.value = '';
			}
		}

		document.addEventListener('DOMContentLoaded', function() {
			const searchType = document.getElementById('searchType');
			toggleSearchInputs(searchType.value);
		});

		document.getElementById('searchType').addEventListener('change',
				function() {
					toggleSearchInputs(this.value);
				});

		document.querySelector('form').addEventListener('submit', function() {
			syncKeywordInput();
		});
	</script>

</body>
</html>
