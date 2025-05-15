<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>영업문서 목록</title>
  <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
</head>
<body id="page-top">

<div id="wrapper">
  <%@ include file="/WEB-INF/views/common/menubar.jsp" %>

  <div class="container-fluid mt-4">
    <h1 class="h4 text-gray-800 mb-4">영업문서 목록</h1>

    <!-- 상단 버튼 + 검색창 -->
    <div class="d-flex justify-content-between align-items-center mb-3">
      <div>
        <a href="${pageContext.request.contextPath}/businessdocument/outboundList.do" class="btn btn-outline-primary btn-sm">출고서 목록</a>
        <a href="${pageContext.request.contextPath}/businessdocument/taxinvoiceList.do" class="btn btn-outline-primary btn-sm">세금계산서 목록</a>
      </div>

      <div class="d-flex">
        <form class="form-inline mr-2" action="${pageContext.request.contextPath}/businessdocument/BusinessDocumentList.do" method="get">
          <!-- 검색 조건 -->
          <select name="searchType" id="searchType" class="form-control mr-2">
            <option value="title" ${param.searchType == 'title' ? 'selected' : ''}>제목</option>
            <option value="client" ${param.searchType == 'client' ? 'selected' : ''}>거래처명</option>
            <option value="status" ${param.searchType == 'status' ? 'selected' : ''}>상태</option>
          </select>

          <!-- 제목 검색 -->
          <input type="text" class="form-control mr-2" id="textInput" placeholder="제목 입력"
                 style="display:none;" value="${param.searchType == 'title' ? param.keyword : ''}"/>

          <!-- 거래처 검색 -->
          <input type="text" class="form-control mr-2" id="clientInput" placeholder="거래처명 입력"
                 style="display:none;" value="${param.searchType == 'client' ? param.keyword : ''}"/>

          <!-- 상태 검색 -->
          <select name="approveStep" id="approveStepSelect" class="form-control mr-2" style="display:none;">
            <option value="">단계 선택</option>
            <option value="1" ${param.approveStep == '1' ? 'selected' : ''}>1차 결재</option>
            <option value="2" ${param.approveStep == '2' ? 'selected' : ''}>2차 결재</option>
          </select>

          <select name="approveStatus" id="approveStatusSelect" class="form-control mr-2" style="display:none;">
            <option value="">상태 선택</option>
            <option value="임시저장" ${param.approveStatus == '임시저장' ? 'selected' : ''}>임시저장</option>
            <option value="대기" ${param.approveStatus == '대기' ? 'selected' : ''}>대기</option>
            <option value="검토" ${param.approveStatus == '검토' ? 'selected' : ''}>검토</option>
            <option value="승인" ${param.approveStatus == '승인' ? 'selected' : ''}>승인</option>
            <option value="반려" ${param.approveStatus == '반려' ? 'selected' : ''}>반려</option>
          </select>

          <!-- keyword 전달용 hidden input -->
          <input type="hidden" name="keyword" id="keywordHidden"/>

          <button type="submit" class="btn btn-primary">검색</button>
        </form>

        <a href="${pageContext.request.contextPath}/businessdocument/insertForm.do" class="btn btn-primary btn-sm">문서 등록</a>
      </div>
    </div>

    <!-- 목록 테이블 -->
    <div class="card shadow mb-4">
      <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">문서 목록</h6>
      </div>
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
                      <c:when test="${doc.documentTypeId eq 'O'}">출고서</c:when>
                      <c:when test="${doc.documentTypeId eq 'G'}">세금계산서</c:when>
                      <c:otherwise>기타</c:otherwise>
                    </c:choose>
                  </td>
                  <td><fmt:formatDate value="${doc.createdDate}" pattern="yyyy-MM-dd"/></td>
                  <td>${doc.title}</td>
                  <td>${doc.clientName}</td>
                  <td><span class="badge badge-secondary">${doc.status}</span></td>
                  <td>
                    <a href="${pageContext.request.contextPath}/businessdocument/outboundDetail.do?documentId=${doc.documentId}"
                       class="btn btn-sm"
                       style="background-color: #e7f1ff; color: #004085; border: 1px solid #b8daff;">상세</a>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>

        <c:import url="/WEB-INF/views/common/pagingView.jsp"/>
      </div>
    </div>
  </div>
</div>

<!-- 검색 조건 스크립트 -->
<script>
  function toggleSearchInputs(type) {
    document.getElementById('textInput').style.display = (type === 'title') ? 'block' : 'none';
    document.getElementById('clientInput').style.display = (type === 'client') ? 'block' : 'none';
    document.getElementById('approveStepSelect').style.display = (type === 'status') ? 'block' : 'none';
    document.getElementById('approveStatusSelect').style.display = (type === 'status') ? 'block' : 'none';
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

  document.addEventListener('DOMContentLoaded', function () {
    const searchType = document.getElementById('searchType');
    toggleSearchInputs(searchType.value);
  });

  document.getElementById('searchType').addEventListener('change', function () {
    toggleSearchInputs(this.value);
  });

  document.querySelector('form').addEventListener('submit', function () {
    syncKeywordInput();
  });
</script>

</body>
</html>
