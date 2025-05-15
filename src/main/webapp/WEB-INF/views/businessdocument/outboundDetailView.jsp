<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${empty sessionScope.loginInfo}">
	<c:redirect url="/login.do" />
</c:if>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>출고서 상세보기</title>
<link
	href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css"
	rel="stylesheet">
</head>
<body id="page-top">
	<div id="wrapper">
		<c:import url="/WEB-INF/views/common/menubar.jsp" />
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<c:import url="/WEB-INF/views/common/topbar.jsp" />

				<div class="container-fluid">
					<h1 class="h3 mb-2 text-gray-800">출고서 상세보기</h1>
					<p class="mb-4 small text-muted">
						출고서 <strong>${document.documentId}</strong>의 상세 정보를 확인합니다.
					</p>

					<div class="card shadow mb-4">
						<div class="card-body">

							<!-- 문서 기본 정보 -->
							<table class="table table-bordered">
								<tbody>
									<tr>
										<th>문서번호</th>
										<td>${document.documentId}</td>
										<th>문서유형</th>
										<td>출고서</td>
										<th>제목</th>
										<td>${document.title}</td>
									</tr>
									<tr>
										<th>거래처명</th>
										<td>${document.clientName}</td>
										<th>주소</th>
										<td colspan="3">${document.address}</td>
									</tr>
									<tr>
										<th>작성자</th>
										<td>${document.documentWriterId}/${document.writerName}</td>
										<th>출고일자</th>
										<td><fmt:formatDate value="${document.documentDate}"
												pattern="yyyy-MM-dd" /></td>
										<th>결제수단</th>
										<td>${document.paymentMethod}</td>
									</tr>
								</tbody>
							</table>

							<!-- 결재 정보 -->
							<h5 class="mt-4">결재 정보</h5>
							<table class="table table-bordered text-center">
								<thead class="thead-light">
									<tr>
										<th>사번</th>
										<th>직급</th>
										<th>이름</th>
										<th>직책</th>
										<th>부서명</th>
										<th>상태</th>
									</tr>
								</thead>
								<tbody>
										<tr>
											<td>${approval.firstApproverId}</td>
											<td>${approval.firstApproverJobTitle}</td>
											<td>${approval.firstApproverName}</td>
											<td>${approval.firstApproverRoleName}</td>
											<td>${approval.firstApproverDeptName}</td>
											<td>${approval.firstApproveStatus}</td>
										</tr>
								</tbody>
							</table>

							<!-- 출고 품목 목록 -->
							<h5 class="mt-4">출고 품목 목록</h5>
							<table class="table table-bordered text-center">
								<thead class="thead-light">
									<tr>
										<th>제품코드</th>
										<th>제품명</th>
										<th>거래처명</th>
										<th>수량</th>
										<th>단가</th>
										<th>총 금액</th>
										<th>출고일자</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${document.items}">
										<tr>
											<td>${item.productId}</td>
											<td>${item.productName}</td>
											<td>${item.clientName}</td>
											<td>${item.quantity}</td>
											<td><fmt:formatNumber value="${item.unitPrice}"
													pattern="#,##0" />원</td>
											<td><fmt:formatNumber
													value="${item.unitPrice * item.quantity}" pattern="#,##0" />원</td>
											<td><fmt:formatDate value="${item.documentDate}"
													pattern="yyyy-MM-dd" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

							<!-- 첨부파일 -->
							<h5 class="mt-4">첨부파일</h5>
							<c:choose>
								<c:when test="${not empty file}">
									<p class="mb-2 font-weight-bold">${file.originalFileName}</p>
									<a class="btn btn-sm btn-info" target="_blank"
										href="${pageContext.request.contextPath}${file.filePath}/${file.renameFileName}">
										<i class="fas fa-eye"></i> 보러가기
									</a>
									<a class="btn btn-sm btn-outline-primary"
										href="${pageContext.request.contextPath}/client/documentDownload.do?ofile=${file.originalFileName}&rfile=${file.renameFileName}">
										<i class="fas fa-download"></i> 다운로드
									</a>
								</c:when>
								<c:otherwise>
									<label for="uploadFile"
										class="btn btn-outline-secondary btn-sm">첨부파일 선택</label>
									<input type="file" id="uploadFile" name="uploadFile"
										style="display: none;" onchange="showFileName(this)">
									<p id="fileNameDisplay" class="text-muted mt-2">첨부파일 없음</p>
								</c:otherwise>
							</c:choose>

							<!-- 하단 버튼 -->
							<div class="text-right mt-4">
								<a
									href="${pageContext.request.contextPath}/businessdocument/outboundUpdateForm.do?documentId=${document.documentId}"
									class="btn btn-warning"> <i class="fas fa-edit"></i> 수정
								</a>
								<button type="button" class="btn btn-secondary"
									onclick="history.back();">
									<i class="fas fa-arrow-left"></i> 이전페이지
								</button>
								<a
									href="${pageContext.request.contextPath}/businessdocument/OutboundList.do"
									class="btn btn-info"> <i class="fas fa-list"></i> 목록으로
								</a>
								<button type="button" class="btn btn-danger"
									onclick="confirmDelete('${document.documentId}')">
									<i class="fas fa-trash-alt"></i> 삭제
								</button>
							</div>

							<script>
								function confirmDelete(documentId) {
									if (confirm("정말 이 문서를 삭제하시겠습니까? 삭제 후 복구할 수 없습니다.")) {
										location.href = '${pageContext.request.contextPath}/businessdocument/delete.do?documentId='
												+ documentId;
									}
								}

								function showFileName(input) {
									const fileName = input.files.length > 0 ? input.files[0].name
											: '첨부파일 없음';
									document.getElementById('fileNameDisplay').textContent = fileName;
								}
							</script>

						</div>
					</div>

				</div>
			</div>
		</div>
	</div>

	<!-- 공통 JS -->
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
