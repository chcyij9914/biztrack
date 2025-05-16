<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>출고서 수정</title>
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
					<h1 class="h3 mb-4 text-gray-800">출고서 수정</h1>
					<form
						action="${pageContext.request.contextPath}/businessdocument/outboundUpdate.do"
						method="post" enctype="multipart/form-data">
						<input type="hidden" name="documentId"
							value="${document.documentId}" />

						<div class="card shadow mb-4">
							<div class="card-body">
								<table class="table table-bordered">
									<tr>
										<th>제목</th>
										<td colspan="3"><input type="text" class="form-control"
											name="title" value="${document.title}" /></td>
									</tr>
									<tr>
										<th>출고일</th>
										<td><input type="date" class="form-control"
											name="documentDate" value="${document.documentDate}" /></td>
										<th>결제수단</th>
										<td><select class="form-control" name="paymentMethod">
												<option value="현금"
													${document.paymentMethod == '현금' ? 'selected' : ''}>현금</option>
												<option value="카드"
													${document.paymentMethod == '카드' ? 'selected' : ''}>카드</option>
												<option value="계좌이체"
													${document.paymentMethod == '계좌이체' ? 'selected' : ''}>계좌이체</option>
										</select></td>
									</tr>
									<tr>
										<th>비고</th>
										<td colspan="3"><textarea name="remarks"
												class="form-control">${document.remarks}</textarea></td>
									</tr>
								</table>

								<h5 class="mt-4">품목 목록</h5>
								<table class="table table-bordered text-center">
									<thead>
										<tr>
											<th>품목명</th>
											<th>수량</th>
											<th>단가</th>
											<th>합계</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item" items="${document.items}"
											varStatus="status">
											<tr>
												<!-- 품목 ID와 상품 ID는 hidden 처리 -->
												<td><input type="hidden"
													name="items[${status.index}].productId"
													value="${item.productId}" /> <input type="hidden"
													name="items[${status.index}].itemId" value="${item.itemId}" />
													<input type="text" class="form-control"
													value="${item.productName}" readonly /></td>

												<!-- 수량은 수정 가능 -->
												<td><input type="number" class="form-control text-end"
													name="items[${status.index}].quantity"
													value="${item.quantity}" /></td>

												<!-- 단가는 readonly -->
												<td><input type="number" class="form-control text-end"
													name="items[${status.index}].unitPrice"
													value="${item.unitPrice}" readonly /></td>

												<!-- 합계는 수량 * 단가 -->
												<td><input type="text" class="form-control text-end"
													value="${item.quantity * item.unitPrice}" readonly /></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>


								<h5 class="mt-4">첨부파일</h5>
								<c:if test="${not empty file}">
									<p>
										기존 파일: <a
											href="${pageContext.request.contextPath}/filedown.do?fileName=${file.renameFileName}">${file.originalFileName}</a>
									</p>
									<input type="checkbox" name="deleteFlag" value="yes" /> 기존 파일 삭제
                            </c:if>
								<input type="file" name="uploadFiles"
									class="form-control-file mt-2" />

								<div class="text-center mt-4">
									<button type="submit" class="btn btn-primary">수정 완료</button>
									<button type="button" class="btn btn-secondary"
										onclick="history.back();">취소</button>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script
		src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>
	<script>
    $(document).ready(function () {
        function updateAmount() {
            $('tr').each(function () {
                const qty = parseFloat($(this).find('.quantity').val()) || 0;
                const price = parseFloat($(this).find('.unitPrice').val()) || 0;
                const total = qty * price;
                $(this).find('.amount').val(total);
            });
        }

        $('.quantity').on('input', updateAmount);
        updateAmount();
    });
</script>
</body>
</html>
