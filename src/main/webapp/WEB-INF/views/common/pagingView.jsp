<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="paging" value="${requestScope.paging}" />

<%-- 안전한 queryParams 구성 --%>
<c:set var="docTypeParam" value="${not empty docType ? 'type=' += docType : ''}" />
<c:set var="searchTypeParam" value="${not empty search.scType ? '&searchType=' += search.scType : ''}" />
<c:set var="keywordParam" value="${not empty search.keyword ? '&keyword=' += search.keyword : ''}" />
<c:set var="statusParam" value="${not empty search.status ? '&approveStatus=' += search.status : ''}" />

<c:set var="queryParams" value="${docTypeParam}${searchTypeParam}${keywordParam}${statusParam}" />

<%-- 페이지네이션 표시 --%>
<nav aria-label="Page navigation example">
  <ul class="pagination justify-content-center">

    <!-- 맨 처음 -->
    <li class="page-item ${paging.currentPage == 1 ? 'disabled' : ''}">
      <a class="page-link" href="${paging.urlMapping}?page=1&${queryParams}">«</a>
    </li>

    <!-- 이전 그룹 -->
    <c:choose>
      <c:when test="${(paging.currentPage - 10) >= 1}">
        <li class="page-item">
          <a class="page-link" href="${paging.urlMapping}?page=${paging.startPage - 10}&${queryParams}">‹</a>
        </li>
      </c:when>
      <c:otherwise>
        <li class="page-item disabled"><a class="page-link" href="#">‹</a></li>
      </c:otherwise>
    </c:choose>

    <!-- 페이지 번호 -->
    <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="p">
      <li class="page-item ${p == paging.currentPage ? 'active' : ''}">
        <a class="page-link" href="${paging.urlMapping}?page=${p}&${queryParams}">${p}</a>
      </li>
    </c:forEach>

    <!-- 다음 그룹 -->
    <c:choose>
      <c:when test="${(paging.startPage + 10) <= paging.maxPage}">
        <li class="page-item">
          <a class="page-link" href="${paging.urlMapping}?page=${paging.startPage + 10}&${queryParams}">›</a>
        </li>
      </c:when>
      <c:otherwise>
        <li class="page-item disabled"><a class="page-link" href="#">›</a></li>
      </c:otherwise>
    </c:choose>

    <!-- 맨 끝 -->
    <li class="page-item ${paging.currentPage == paging.maxPage ? 'disabled' : ''}">
      <a class="page-link" href="${paging.urlMapping}?page=${paging.maxPage}&${queryParams}">»</a>
    </li>

  </ul>
</nav>
