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
<title>문서 상세보기</title>
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
					<h1 class="h3 mb-2 text-gray-800">문서 상세보기</h1>
					<p class="mb-4 small text-muted">
						등록된 <strong>${document.documentId}</strong> 제안서의 상세 내용을 확인합니다.
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
										<td>${document.documentName}</td>
										<th>제목</th>
										<td>${document.title}</td>
									</tr>
									<tr>
										<th>거래처명</th>
										<td>${document.clientName}</td>
										<th>작성자</th>
										<td>${document.documentWriterId}/
											${document.documentWriterName} /
											${document.documentWriterJobTitle}</td>
										<th>담당자</th>
										<td>${document.documentManagerId}/
											${document.documentManagerName} /
											${document.documentManagerJobTitle}</td>
									</tr>
									<tr>
										<th>작성일</th>
										<td><fmt:formatDate value="${document.createdDate}"
												pattern="yyyy-MM-dd" /></td>
										<th>입고일자</th>
										<td><fmt:formatDate value="${document.documentDate}"
												pattern="yyyy-MM-dd" /></td>
										<th>결제수단</th>
										<td>${document.paymentMethod}</td>
									</tr>
								</tbody>
							</table>

							<!-- 결재자 정보 블럭 -->
							<h5 class="mt-4">결재 정보</h5>
							<table class="table table-bordered text-center">
								<thead class="thead-light">
									<tr>
										<th>구분</th>
										<th>사번</th>
										<th>이름</th>
										<th>직급</th>
										<th>직책</th>
										<th>부서</th>
										<th>결재일</th>
										<th>상태</th>
										<c:if test="${not empty loginInfo.roleId}">
											<th>조치</th>
										</c:if>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>1차 결재자</td>
										<td>${approval.firstApproverId}</td>
										<td>${approval.firstApproverName}</td>
										<td>${approval.firstApproverJobTitle}</td>
										<td>${approval.firstApproverRoleName}</td>
										<td>${approval.firstApproverDeptName}</td>
										<td><c:choose>
												<c:when test="${not empty approval.firstApproveDate}">
													<fmt:formatDate value="${approval.firstApproveDate}"
														pattern="yyyy-MM-dd" />
												</c:when>
												<c:otherwise>미결재</c:otherwise>
											</c:choose></td>
										<td>${approval.firstApproveStatus}</td>
										<!-- 조치 버튼: 본인이 1차 결재자일 때만 보여줌 -->
										<c:if test="${loginInfo.empId == approval.firstApproverId}">
											<td><c:choose>
													<c:when test="${approval.firstApproveStatus == '결재 대기'}">
														<a href="javascript:void(0);"
															onclick="goApproval('${document.documentId}', 1, '승인')"
															class="btn btn-success btn-sm">승인</a>
														<a href="javascript:void(0);"
															onclick="goApproval('${document.documentId}', 1, '반려')"
															class="btn btn-danger btn-sm">반려</a>
													</c:when>
													<c:when test="${approval.firstApproveStatus == '승인'}">
														<span class="text-success font-weight-bold">완료</span>
													</c:when>
													<c:otherwise>-</c:otherwise>
												</c:choose></td>
										</c:if>
									</tr>
									<tr>
										<td>2차 결재자</td>
										<td>${approval.secondApproverId}</td>
										<td>${approval.secondApproverName}</td>
										<td>${approval.secondApproverJobTitle}</td>
										<td>${approval.secondApproverRoleName}</td>
										<td>${approval.secondApproverDeptName}</td>
										<td><c:choose>
												<c:when test="${not empty approval.secondApproveDate}">
													<fmt:formatDate value="${approval.secondApproveDate}"
														pattern="yyyy-MM-dd" />
												</c:when>
												<c:otherwise>미결재</c:otherwise>
											</c:choose></td>
										<td>${approval.secondApproveStatus}</td>
										<!-- 조치 버튼: 본인이 2차 결재자일 때만 보임 -->
										<c:if test="${loginInfo.empId == approval.secondApproverId}">
											<td><c:choose>
														<c:when test="${approval.secondApproveStatus == '결재 대기'}">
														<a href="javascript:void(0);"
															onclick="goApproval('${document.documentId}', 2, '승인')"
															class="btn btn-success btn-sm">승인</a>
														<a href="javascript:void(0);"
															onclick="goApproval('${document.documentId}', 2, '반려')"
															class="btn btn-danger btn-sm">반려</a>
													</c:when>
													<c:when test="${approval.secondApproveStatus == '승인'}">
														<span class="text-success font-weight-bold">완료</span>
													</c:when>
													<c:otherwise>-</c:otherwise>
												</c:choose></td>
										</c:if>
									</tr>
								</tbody>
							</table>

							<!-- 품목 목록 -->
							<h5 class="mt-4">품목 목록</h5>
							<table class="table table-bordered text-center">
								<thead class="thead-light">
									<tr>
										<th>#</th>
										<th>제품코드</th>
										<th>제품명</th>
										<th>수량</th>
										<th>단가</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${document.items}"
										varStatus="status">
										<tr>
											<td>${status.index + 1}</td>
											<td>${item.productId}</td>
											<td>${item.productName}</td>
											<td>${item.quantity}</td>
											<td><fmt:formatNumber value="${item.unitPrice}"
													pattern="#,##0" />원</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

							<!-- 비고 -->
							<h5 class="mt-4">비고</h5>
							<p>${document.remarks}</p>

							<!-- 첨부파일 -->
							<h5 class="mt-4">첨부파일</h5>
							<c:choose>
								<c:when test="${not empty file}">
									<p class="mb-2 font-weight-bold">${file.originalFileName}</p>
									<!-- 원래 파일명 표시 -->

									<a class="btn btn-sm btn-info" target="_blank"
										href="${pageContext.request.contextPath}${file.filePath}/${file.renameFileName}">
										<i class="fas fa-eye"></i> 보러가기
									</a>

									<a class="btn btn-sm btn-outline-primary"
										href="${pageContext.request.contextPath}/inbound/documentDownload.do?ofile=${file.originalFileName}&rfile=${file.renameFileName}">
										<i class="fas fa-download"></i> 다운로드
									</a>
								</c:when>
								<c:otherwise>
									<p class="text-muted">첨부파일 없음</p>
								</c:otherwise>
							</c:choose>

							<!-- 하단 버튼 -->
							<div class="text-right mt-4">
								<c:if
									test="${loginInfo.roleId == 'A2' && approval.firstApproveStatus == '결재 대기'
			          || loginInfo.roleId == 'A3' && approval.firstApproveStatus == '결재 대기'
			          || loginInfo.empId == document.documentManagerId && approval.firstApproveStatus == '결재 대기'}">
									<a
										href="${pageContext.request.contextPath}/inbound/inbound-update.do?documentId=${document.documentId}"
										class="btn btn-warning"> <i class="fas fa-edit"></i> 수정
									</a>
								</c:if>
								<button type="button" class="btn btn-secondary"
									onclick="history.back();">
									<i class="fas fa-arrow-left"></i> 이전페이지
								</button>
								<a
									href="${pageContext.request.contextPath}/inbound/inbound-document.do"
									class="btn btn-info"> <i class="fas fa-list"></i> 목록으로
								</a>
								<c:if
									test="${loginInfo.roleId == 'A2' && approval.firstApproveStatus == '결재 대기'
			          || loginInfo.roleId == 'A3' && approval.firstApproveStatus == '결재 대기'
			          || loginInfo.empId == document.documentManagerId && approval.firstApproveStatus == '결재 대기'}">
									<button type="button" class="btn btn-danger"
										onclick="confirmDelete('${document.documentId}')">
										<i class="fas fa-trash-alt"></i> 삭제
									</button>
								</c:if>
								<script>
									function confirmDelete(documentId) {
										if (confirm("정말 이 문서를 삭제하시겠습니까? 삭제 후 복구할 수 없습니다.")) {
											location.href = '${pageContext.request.contextPath}/inbound/documentDelete.do?documentId='
													+ documentId;
										}
									}
								</script>
							</div>
						</div>
					</div>
					<script>
						/**
						 * 결재 상태 변경 요청 (검토 / 승인 / 반려)
						 * @param {string} documentId - 문서 ID
						 * @param {number} step - 결재 단계 (1: 1차, 2: 2차)
						 * @param {string} status - 상태 문자열 (예: '1차 결재 검토', '2차 결재 승인', '반려')
						 */
						function goApproval(documentId, step, status) {
							const encodedStatus = encodeURIComponent(status);
							location.href = '${pageContext.request.contextPath}/inbound/updateApprovalStatus.do'
									+ '?documentId='
									+ documentId
									+ '&step='
									+ step + '&status=' + encodedStatus;
						}

						/**
						 * 문서 삭제 요청 전 확인 알림
						 * @param {string} documentId - 삭제할 문서 ID
						 */
						function confirmDelete(documentId) {
							if (confirm("정말 이 문서를 삭제하시겠습니까?\n삭제 후에는 복구할 수 없습니다.")) {
								location.href = '${pageContext.request.contextPath}/inbound/inbound-document.do?documentId='
										+ documentId;
							}
						}
					</script>
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
