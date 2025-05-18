<%@ page language="java" contentType="text/html;charset=UTF-8"
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

		<!-- 컨텐츠 전체 -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- 콘텐츠 영역 -->
			<div id="content">

				<!-- 탑바 -->
				<%@ include file="/WEB-INF/views/common/topbar.jsp"%>
				
				<div class="container-fluid mt-4">
					<h1 class="h4 text-gray-800 mb-3">문서 목록</h1>

					<div class="d-flex justify-content-between align-items-center mb-3">
						<div>
							<a
								href="${pageContext.request.contextPath}/businessdocument/OutboundList.do"
								class="btn btn-primary btn-sm">출고서 목록</a>
							<%-- <a href="${pageContext.request.contextPath}/businessdocument/TaxinvoiceList.do" class="btn btn-outline-primary btn-sm">세금계산서 목록</a> --%>
						</div>

						<div class="d-flex">
							<form class="form-inline mr-2" id="searchForm"
								action="${pageContext.request.contextPath}/businessdocument/OutboundList.do"
								method="get">
								<select name="searchType" id="searchType"
									class="form-control mr-2">
									<option value="title"
										${param.searchType == 'title' ? 'selected' : ''}>제목</option>
									<option value="client"
										${param.searchType == 'client' ? 'selected' : ''}>거래처명</option>
									<option value="status"
										${param.searchType == 'status' ? 'selected' : ''}>상태</option>
								</select>

								<!-- 제목 검색 input -->
								<input type="text" class="form-control mr-2" id="textInput"
									placeholder="제목 입력" style="display: none;"
									value="${param.searchType == 'title' ? param.keyword : ''}" />

								<!-- 거래처명 검색 input -->
								<input type="text" class="form-control mr-2" id="clientInput"
									placeholder="거래처명 입력" style="display: none;"
									value="${param.searchType == 'client' ? param.keyword : ''}" />

								<!-- 상태 검색 단일 드롭다운 -->
								<select name="statusOption" id="statusSelect"
									class="form-control mr-2" style="display: none;">
									<option value="">상태 선택</option>
									<option value="1/대기"
										${param.approveStep == '1' && param.approveStatus == '대기' ? 'selected' : ''}>1차
										결재 대기</option>
									<option value="1/검토"
										${param.approveStep == '1' && param.approveStatus == '검토' ? 'selected' : ''}>1차
										결재 검토</option>
									<option value="1/승인"
										${param.approveStep == '1' && param.approveStatus == '승인' ? 'selected' : ''}>1차
										결재 승인</option>
									<option value="2/대기"
										${param.approveStep == '2' && param.approveStatus == '대기' ? 'selected' : ''}>2차
										결재 대기</option>
									<option value="2/검토"
										${param.approveStep == '2' && param.approveStatus == '검토' ? 'selected' : ''}>2차
										결재 검토</option>
									<option value="2/승인"
										${param.approveStep == '2' && param.approveStatus == '승인' ? 'selected' : ''}>2차
										결재 승인</option>
									<option value="/반려"
										${param.approveStatus == '반려' ? 'selected' : ''}>반려</option>
								</select>

								<!-- hidden 값 -->
								<input type="hidden" name="approveStep" id="approveStepHidden"
									value="${param.approveStep}" /> <input type="hidden"
									name="approveStatus" id="approveStatusHidden"
									value="${param.approveStatus}" /> <input type="hidden"
									name="keyword" id="keywordHidden" /> <input type="hidden"
									name="page" id="pageInput" value="${pageInfo.currentPage}" />
								<button type="submit" class="btn btn-primary btn-sm">검색</button>
							</form>

							<button type="button" class="btn btn-primary btn-sm"
								onclick="window.open('${pageContext.request.contextPath}/businessdocument/outboundInsertForm.do', '출고서 등록', 'width=1000,height=900,scrollbars=yes,resizable=yes');">
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
											<th>주소</th>
											<th>출고수량</th>
											<th>청구금액</th>
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
												<td><fmt:formatNumber
														value="${doc.outboundTotalAmount}" pattern="#,##0" />원</td>
												<td>${doc.writerName}</td>
												<td><c:choose>
														<c:when test="${empty doc.status}">
															<span class="badge badge-light">미결제</span>
														</c:when>
														<c:otherwise>
															<span class="badge badge-secondary">${doc.status}</span>
														</c:otherwise>
													</c:choose></td>
												<td><a
													href="${pageContext.request.contextPath}/businessdocument/outboundDetail.do?documentId=${doc.documentId}"
													class="btn btn-sm btn-outline-secondary">상세</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>

							<!-- 페이징 -->
							<nav aria-label="Page navigation">
								<ul class="pagination justify-content-center">
									<c:if test="${pageInfo.currentPage > 1}">
										<li class="page-item"><a class="page-link"
											href="javascript:void(0);" onclick="goToPage(1)">&laquo;</a></li>
										<li class="page-item"><a class="page-link"
											href="javascript:void(0);"
											onclick="goToPage(${pageInfo.currentPage - 1})">&lsaquo;</a></li>
									</c:if>
									<c:forEach var="p" begin="${pageInfo.startPage}"
										end="${pageInfo.endPage}">
										<li
											class="page-item ${p == pageInfo.currentPage ? 'active' : ''}">
											<a class="page-link" href="javascript:void(0);"
											onclick="goToPage(${p})">${p}</a>
										</li>
									</c:forEach>
									<c:if test="${pageInfo.currentPage < pageInfo.totalPage}">
										<li class="page-item"><a class="page-link"
											href="javascript:void(0);"
											onclick="goToPage(${pageInfo.currentPage + 1})">&rsaquo;</a></li>
										<li class="page-item"><a class="page-link"
											href="javascript:void(0);"
											onclick="goToPage(${pageInfo.totalPage})">&raquo;</a></li>
									</c:if>
								</ul>
							</nav>
						</div>
					</div>
					<c:import url="/WEB-INF/views/common/footer.jsp" />
					</div>
					</div>
				</div>
			</div>

			<script>
  function toggleSearchInputs(type) {
    document.getElementById('textInput').style.display = (type === 'title') ? 'block' : 'none';
    document.getElementById('clientInput').style.display = (type === 'client') ? 'block' : 'none';
    document.getElementById('statusSelect').style.display = (type === 'status') ? 'block' : 'none';
  }

  function syncKeywordInput() {
    const type = document.getElementById('searchType').value;
    const keywordHidden = document.getElementById('keywordHidden');
    if (type === 'title') {
      keywordHidden.value = document.getElementById('textInput').value;
    } else if (type === 'client') {
      keywordHidden.value = document.getElementById('clientInput').value;
    } else {
      keywordHidden.value = '';
    }
  }

  function goToPage(page) {
    document.getElementById('pageInput').value = page;
    syncKeywordInput();
    document.getElementById('searchForm').submit();
  }

  document.addEventListener('DOMContentLoaded', function () {
    toggleSearchInputs(document.getElementById('searchType').value);
  });

  document.getElementById('searchType').addEventListener('change', function () {
    toggleSearchInputs(this.value);
  });

  document.getElementById('statusSelect').addEventListener('change', function () {
    const selected = this.value;
    const [step, status] = selected.split('/');
    document.getElementById('approveStepHidden').value = step || '';
    document.getElementById('approveStatusHidden').value = status || '';
  });

  document.getElementById('searchForm').addEventListener('submit', function () {
    syncKeywordInput();
  });
</script>
</body>
</html>
