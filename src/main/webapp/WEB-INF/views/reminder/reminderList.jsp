<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>리마인더 목록</title>
<link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
</head>

<body id="page-top">
<div id="wrapper">
    <%@ include file="/WEB-INF/views/common/menubar.jsp"%>
    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
            <c:import url="/WEB-INF/views/common/topbar.jsp" />
            <div class="container-fluid">
                <h1 class="h3 mb-4 text-gray-800">리마인더 목록</h1>
                <div class="card shadow mb-4">
                    <div class="card-header py-3 d-flex justify-content-between align-items-center">
                        <h6 class="m-0 font-weight-bold text-primary">리마인더 정보</h6>
                        <button type="button" id="goToMailForm" class="btn btn-primary" style="padding: 8px 16px; font-size: 14px; margin-right: 90px;">메일 작성</button>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered text-center">
                                <thead class="thead-light">
                                    <tr>
                                        <th><input type="checkbox" id="checkAll"></th>
                                        <th>물품명</th>
                                        <th>거래처명</th>
                                        <th>거래처번호</th>
                                        <th>거래처주소</th>
                                        <th class="email-cell-header">거래처 이메일</th>
                                        <th>거래건수</th>
                                        <th>수신확인</th>
                                        <th>리마인더 생성일</th>
                                        <th>전송된 메일 내용</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="row" items="${reminderList}">
                                        <tr>
                                            <td><input type="checkbox" class="rowCheck" data-reminder-id="${row.reminderId}" /></td>
                                            <td>${row.productName}</td>
                                            <td>${row.clientName}</td>
                                            <td class="phone-cell">${row.companyPhone}</td>
                                            <td>${row.address}</td>
                                            <td class="email-cell">${row.clientEmail}</td>
                                            <td>${row.transactionCount}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${row.receiptConfirmation eq 'Y'}">확인</c:when>
                                                    <c:otherwise>미확인</c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td><fmt:formatDate value="${row.reminderDate}" pattern="yyyy-MM-dd" /></td>
                                            <td>${row.mailContent}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <c:import url="/WEB-INF/views/common/footer.jsp" />
    </div>
</div>

<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>

<script>
    $('#checkAll').on('click', function () {
        $('.rowCheck').prop('checked', this.checked);
    });

    $('#goToMailForm').on('click', function () {
        const selected = $('.rowCheck:checked').first();
        if (selected.length === 0) {
            alert("메일을 보낼 항목을 선택하세요.");
            return;
        }
        const row = selected.closest('tr');
        const email = row.find('.email-cell').text().trim();
        const name = row.find('td').eq(2).text().trim();
        const reminderId = selected.data('reminder-id');

        const url = '${pageContext.request.contextPath}/reminder/sendForm.do'
                  + '?clientEmail=' + encodeURIComponent(email)
                  + '&clientName=' + encodeURIComponent(name)
                  + '&reminderId=' + encodeURIComponent(reminderId);
        
        window.location.href = url;
    });
</script>
</body>
</html>
