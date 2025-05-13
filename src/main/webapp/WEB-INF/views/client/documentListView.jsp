<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${empty sessionScope.loginInfo}">
  <c:redirect url="/login.do" />
</c:if>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>계약서 관리</title>
  <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
  <style>
    td {
      white-space: nowrap;
      text-overflow: ellipsis;
      overflow: hidden;
    }
    .tab-button {
      font-weight: bold;
      color: #4e73df;
      border: none;
      background: none;
      padding: 0.5rem 1rem;
      cursor: pointer;
    }
    .tab-button.active {
      border-bottom: 3px solid #4e73df;
    }
  </style>
</head>
<body id="page-top">
<div id="wrapper">
  <c:import url="/WEB-INF/views/common/menubar.jsp" />
  <div id="content-wrapper" class="d-flex flex-column">
    <div id="content">
      <c:import url="/WEB-INF/views/common/topbar.jsp" />

      <div class="container-fluid">

        <!-- 상단 바: 탭 + 등록 + 검색 -->
        <div class="d-flex justify-content-between align-items-center mb-3">

          <!-- 제목 + 탭 -->
          <div class="d-flex align-items-center">
            <div class="mr-4">
              <h1 class="h4 mb-0 text-gray-800">계약/제안서 관리</h1>
              <p class="text-muted small mb-0">문서 목록</p>
            </div>
            <div class="ml-4">
			  <a href="${pageContext.request.contextPath}/client/documentList.do?type=C" class="tab-button ${docType == 'C' ? 'active' : ''}">계약서</a>
			  <a href="${pageContext.request.contextPath}/client/documentList.do?type=D" class="tab-button ${docType == 'D' ? 'active' : ''}">제안서</a>
			</div>
          </div>	

          <!-- 등록 버튼 + 검색 -->
          <div class="d-flex align-items-center">

            <!-- 문서 등록 버튼 -->
			<a href="#" class="btn btn-primary btn-sm mr-3"
			   onclick="window.open('${pageContext.request.contextPath}/client/${docType eq "C" ? "cdocumentInsertForm" : "documentInsertForm"}.do?type=${docType}',
			                        'insertDocWindow', 'width=1000,height=800'); return false;">
			  + 문서 등록
			</a>

            <!-- 검색 폼 -->
            <form class="form-inline" action="${pageContext.request.contextPath}/client/documentSearch.do" method="get" onsubmit="syncKeywordInput()">
              <input type="hidden" name="type" value="C" />
              <label class="mr-2 font-weight-bold">검색 기준</label>

              <select name="searchType" id="searchType" class="form-control form-control-sm mr-2">
                <option value="title" ${search.scType == 'title' ? 'selected' : ''}>제목</option>
                <option value="client" ${search.scType == 'client' ? 'selected' : ''}>거래처명</option>
                <option value="status" ${search.scType == 'status' ? 'selected' : ''}>상태</option>
              </select>

              <input type="text" class="form-control form-control-sm mr-2" id="textInput" name="titleInput"
                     placeholder="제목 입력" style="display:none;" value="${search.scType == 'title' ? search.keyword : ''}" />

              <input type="text" class="form-control form-control-sm mr-2" id="clientInput" name="clientInput"
                     placeholder="거래처명 입력" style="display:none;" value="${search.scType == 'client' ? search.keyword : ''}" />

              <select name="approveStatus" id="approveStatusSelect" class="form-control form-control-sm mr-2" style="display:none;">
                <option value="" disabled selected>상태 선택</option>
                <option value="1차 결재 대기">1차 결재 대기</option>
                <option value="1차 결재 검토">1차 결재 검토</option>
                <option value="1차 결재 승인">1차 결재 승인</option>
                <option value="2차 결재 대기">2차 결재 대기</option>
                <option value="2차 결재 검토">2차 결재 검토</option>
                <option value="2차 결재 승인">2차 결재 승인</option>
                <option value="반려">반려</option>
              </select>

              <input type="hidden" name="keyword" id="keywordHidden" />
              <button type="submit" class="btn btn-sm btn-primary">검색</button>
            </form>
          </div>
        </div>

        <!-- 문서 테이블 공통 뷰 -->
        <c:set var="docType" value="C" />
        <c:import url="/WEB-INF/views/client/documentTableView.jsp" />

      </div>
    </div>
  </div>
</div>

<script>
function toggleSearchInputs(type) {
  document.getElementById('textInput').style.display = (type === 'title') ? 'inline-block' : 'none';
  document.getElementById('clientInput').style.display = (type === 'client') ? 'inline-block' : 'none';
  document.getElementById('approveStatusSelect').style.display = (type === 'status') ? 'inline-block' : 'none';
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
  toggleSearchInputs(document.getElementById('searchType').value);
});
document.getElementById('searchType').addEventListener('change', function () {
  toggleSearchInputs(this.value);
});
</script>

<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>
</body>
</html>
