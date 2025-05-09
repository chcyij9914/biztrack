<%@ page language="java" contentType="text/html; charset=UTF-8"%>
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
                        <button type="button" id="openSmsModal" class="btn btn-primary" style="padding: 8px 16px; font-size: 14px; margin-right: 90px;">문자 작성</button>
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
                                        <th>구매 수량</th>
                                        <th>거래건수</th>
                                        <th>수신확인</th>
                                        <th>리마인더 생성일</th>
                                        <th>전송된 문자 내용</th>
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
                                            <td>${row.outboundQuantity}</td>
                                            <td>${row.transactionCount}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${row.receiptConfirmation eq 'Y'}">확인</c:when>
                                                    <c:otherwise>미확인</c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td><fmt:formatDate value="${row.reminderDate}" pattern="yyyy-MM-dd" /></td>
                                            <td>${row.smsContent}</td>
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

<!-- 문자 작성 모달 -->
<div class="modal fade" id="smsModal" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title">※ 리마인더 문자 ※</h5>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <div class="modal-body">
                <div class="form-group text-center">
                    <label for="smsImage">이미지 첨부</label>
                    <input type="file" class="form-control-file" id="smsImage" accept="image/*">
                    <img id="imagePreview" src="#" alt="미리보기" class="img-fluid mt-2 border" style="max-height: 200px; display: none;">
                </div>

                <div class="form-group">
                    <label for="smsContent">문자 내용</label>
                    <textarea class="form-control" id="smsContent" rows="5" placeholder="거래처명을 포함한 맞춤형 문구를 입력하세요."></textarea>
                </div>
            </div>

            <div style="display: flex; justify-content: center; gap: 10px; margin-top: 20px; margin-bottom: 50px;">
                <button type="button" class="btn btn-primary" id="sendSms">문자 전송하기</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>

<!-- JS -->
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>

<script>
    $('#checkAll').on('click', function () {
        $('.rowCheck').prop('checked', this.checked);
    });

    $('#openSmsModal').on('click', function () {
        const selected = $('.rowCheck:checked');
        if (selected.length === 0) {
            alert('문자를 보낼 항목을 선택하세요.');
            return;
        }
        $('#smsModal').modal('show');
    });

    $('#smsImage').on('change', function (e) {
        const file = e.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (event) {
                $('#imagePreview').attr('src', event.target.result).show();
            };
            reader.readAsDataURL(file);
        } else {
            $('#imagePreview').hide();
        }
    });

    $('#sendSms').on('click', function () {
        const selectedRow = $('.rowCheck:checked').first().closest('tr');
        const phone = selectedRow.find('.phone-cell').text().trim();
        const content = $('#smsContent').val();
        const reminderId = selectedRow.find('.rowCheck').data('reminder-id');

        if (!phone || !content) {
            alert("수신번호와 내용을 모두 입력하세요.");
            return;
        }

        $.ajax({
            type: 'POST',
            url: '${pageContext.request.contextPath}/reminder/SendSms.do',
            data: {
                reminderId: reminderId,
                phone: phone,
                content: content
            },
            success: function (result) {
                alert(result);
                $('#smsModal').modal('hide');
            },
            error: function () {
                alert("문자 전송 중 오류 발생");
            }
        });
    });
</script>
</body>
</html>
