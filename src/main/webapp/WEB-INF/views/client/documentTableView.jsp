<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
                  <c:when test="${doc.documentTypeId eq 'C'}">계약서</c:when>
                  <c:when test="${doc.documentTypeId eq 'D'}">제안서</c:when>
                  <c:otherwise>기타</c:otherwise>
                </c:choose>
              </td>
              <td><fmt:formatDate value="${doc.createdDate}" pattern="yyyy-MM-dd" /></td>
              <td>${doc.title}</td>
              <td>${doc.clientName}</td>
              <td>
                <c:choose>
                  <c:when test="${doc.status eq '반려'}">반려됨</c:when>
                  <c:when test="${doc.status eq '1차 결재 대기'}">1차 결재 대기</c:when>
                  <c:when test="${doc.status eq '1차 결재 검토'}">1차 결재 검토</c:when>
                  <c:when test="${doc.status eq '1차 결재 승인'}">1차 결재 승인</c:when>
                  <c:when test="${doc.status eq '2차 결재 대기'}">2차 결재 대기</c:when>
                  <c:when test="${doc.status eq '2차 결재 검토'}">2차 결재 검토</c:when>
                  <c:when test="${doc.status eq '2차 결재 승인'}">2차 결재 승인</c:when>
                  <c:otherwise>임시저장</c:otherwise>
                </c:choose>
              </td>
              <td>
                <a href="${pageContext.request.contextPath}/client/documentDetailView.do?documentId=${doc.documentId}"
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
	<c:set var="queryParams" value="type=${docType}&searchType=${search.scType}&keyword=${search.keyword}&approveStatus=${search.status}" />
	<c:import url="/WEB-INF/views/common/pagingView.jsp" />
  </div>
</div>
