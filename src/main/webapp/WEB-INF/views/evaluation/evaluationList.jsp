<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${empty sessionScope.loginInfo}">
  <c:redirect url="/login.do" />
</c:if>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>직원 평가 목록</title>
  <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body id="page-top">
<div id="wrapper">
  <jsp:include page="/WEB-INF/views/common/menubar.jsp" />
  <div id="content-wrapper" class="d-flex flex-column">
    <div id="content">
      <jsp:include page="/WEB-INF/views/common/topbar.jsp" />
      <div class="container-fluid">

        <div class="d-sm-flex align-items-center justify-content-between mb-4">
          <h1 class="h4 mb-0 text-gray-800">직원 평가 목록</h1>
          <a href="${pageContext.request.contextPath}/evaluation/registerForm.do" class="btn btn-sm btn-primary">평가 등록</a>
        </div>

        <!-- 세부점수 요약 -->
        <div class="card shadow mb-4">
          <div class="card-header font-weight-bold text-primary">세부점수 (과목당 100점 기준)</div>
          <div class="card-body p-2">
            <table class="table table-bordered text-center mb-0">
              <thead class="thead-light">
              <tr>
                <th>실적</th>
                <th>근태</th>
                <th>총점</th>
                <th>평균</th>
              </tr>
              </thead>
              <tbody>
              <tr>
	              <td id="score-sales">-</td>
		          <td id="score-attitude">-</td>
		          <td id="score-total">-</td>
		          <td id="score-avg">-</td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>

        <!-- 연도/분기 필터 폼 -->
        <div class="text-center mb-3">
  <form action="${pageContext.request.contextPath}/evaluation/filter.do" method="get" id="filterForm" class="d-inline-block">
    <select name="year" class="form-control d-inline w-auto">
      <option value="2025" ${param.year == '2025' ? 'selected' : ''}>2025</option>
      <option value="2024" ${param.year == '2024' ? 'selected' : ''}>2024</option>
      <option value="2023" ${param.year == '2023' ? 'selected' : ''}>2023</option>
    </select>

    <input type="hidden" name="quarter" id="quarterInput">

    <c:set var="quarters" value="${fn:split('1분기,2분기,3분기,4분기', ',')}" />
	<c:forEach var="q" items="${quarters}">
	  <button type="submit"
	          onclick="setQuarter('${q}')"
	          class="btn btn-sm ${param.quarter == q ? 'btn-outline-dark' : 'btn-outline-secondary'}">
	    ${q}
	  </button>
	</c:forEach>   
  </form>
</div>

        <!-- 평가 테이블 -->  
        <div class="table-responsive">
          <table class="table table-bordered text-center" width="100%">
            <thead class="thead-light">
              <tr>
                <th>사번</th>
                <th>부서명</th>
                <th>사원명</th>
                <th>평가기준일</th>
                <th>점수</th>
                <th>등급</th>
                <th>cmt</th>
                <th>세부점수</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="eval" items="${evaluationList}">
                <tr>
                  <td>${eval.empId}</td>
                  <td>${eval.deptName}</td>
                  <td>${eval.empName}</td>
                  <td>${eval.evaluationDay}</td>
                  <td>${eval.totalScore}</td>
                  <td>${eval.grade}</td>
                  <td>${eval.comments}</td>
                  <td>
                   <button type="button"
              class="btn btn-sm btn-outline-primary detail-score-btn"
              data-sales="${eval.scoreSales}"
              data-attitude="${eval.scoreAttitude}"
              data-total="${eval.totalScore}">
        세부점수
      </button>
                  </td>
                </tr>
              </c:forEach>
              <c:if test="${empty evaluationList}">
                <tr><td colspan="8">조회된 평가 결과가 없습니다.</td></tr>
              </c:if>
            </tbody>
          </table>
        </div>

        <!-- 등급 안내 -->
        <div class="card shadow mb-4">
          <div class="card-header font-weight-bold text-primary">등급 안내</div>
          <div class="card-body">
            <table class="table table-bordered text-center">
              <thead><tr><th>A</th><th>B</th><th>C</th><th>D</th><th>E</th><th>F</th></tr></thead>
              <tbody>
              <tr>
                <td>200~181</td><td>180~161</td><td>160~141</td>
                <td>140~121</td><td>120~101</td><td>100~0</td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>

      </div>
    </div>
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
  </div>
</div>

<script>
function setQuarter(q) {
    document.getElementById('quarterInput').value = q;
  }
  

document.addEventListener("DOMContentLoaded", function () {
	  const buttons = document.querySelectorAll(".detail-score-btn");

	  buttons.forEach(function (btn) {
	    btn.addEventListener("click", function () {
	      const sales = parseInt(btn.dataset.sales) || 0;
	      const attitude = parseInt(btn.dataset.attitude) || 0;
	      const total = parseInt(btn.dataset.total) || 0;
	      const avg = Math.round(total / 2);  // ✅ 평균 2과목 기준

	      document.getElementById("score-sales").textContent = sales;
	      document.getElementById("score-attitude").textContent = attitude;
	      document.getElementById("score-total").textContent = total;
	      document.getElementById("score-avg").textContent = avg;
	    });
	  });
	});
</script>
</body>
</html>
