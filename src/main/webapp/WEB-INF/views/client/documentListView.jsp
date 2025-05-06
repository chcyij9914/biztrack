<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>계약/제안서 관리</title>
    <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
    <style>
        td {
            white-space: nowrap;
            text-overflow: ellipsis;
            overflow: hidden;
        }
    </style>
</head>
<body id="page-top">
    <div id="wrapper">

        <!-- Sidebar -->
        <c:import url="/WEB-INF/views/common/menubar.jsp" />

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">

                <!-- Topbar -->
                <c:import url="/WEB-INF/views/common/topbar.jsp" />

                <!-- Page Content -->
                <div class="container-fluid">

                    <!-- Heading -->
                    <div class="d-sm-flex justify-content-between align-items-center mb-4">
                        <div>
                            <h1 class="h3 mb-0 text-gray-800">계약/제안서 관리</h1>
                            <p class="small text-muted mb-0">문서 목록</p>
                        </div>
                        <a href="#" class="btn btn-primary"
                           onclick="window.open('${pageContext.request.contextPath}/document/insertForm.do', 'insertDocWindow', 'width=800,height=900'); return false;">
                            + 문서 등록
                        </a>
                    </div>

                    <!-- Table -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered text-center" width="100%" cellspacing="0">
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
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${doc.documentType eq '계약서'}">계약서</c:when>
                                                        <c:when test="${doc.documentType eq '제안서'}">제안서</c:when>
                                                        <c:otherwise>기타</c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td><fmt:formatDate value="${doc.createdDate}" pattern="yyyy-MM-dd" /></td>
                                                <td>${doc.title}</td>
                                                <td>${doc.clientName}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${doc.status eq '작성중'}">작성중</c:when>
                                                        <c:when test="${doc.status eq '1차 승인 대기'}">1차 승인 대기</c:when>
                                                        <c:when test="${doc.status eq '1차 승인 완료'}">1차 승인 완료</c:when>
                                                        <c:when test="${doc.status eq '2차 승인 대기'}">2차 승인 대기</c:when>
                                                        <c:when test="${doc.status eq '2차 승인 완료'}">2차 승인 완료</c:when>
                                                        <c:when test="${doc.status eq '반려됨'}">반려됨</c:when>
                                                        <c:otherwise>미정</c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>
                                                    <a href="${pageContext.request.contextPath}/document/detail.do?documentId=${doc.documentId}"
                                                       class="btn btn-sm btn-outline-secondary">상세</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        <c:if test="${empty documentList}">
                                            <tr>
                                                <td colspan="7" class="text-muted text-center">등록된 문서가 없습니다.</td>
                                            </tr>
                                        </c:if>
                                    </tbody>
                                </table>
                            </div>

                            <!-- 페이징 -->
                            <c:import url="/WEB-INF/views/common/pagingView.jsp" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- JS -->
    <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>
</body>
</html>
