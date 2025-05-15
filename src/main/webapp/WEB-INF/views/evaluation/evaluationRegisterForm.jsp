<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty sessionScope.loginInfo}">
  <c:redirect url="/login.do" />
</c:if>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>평가 등록</title>
  <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body id="page-top">
<div id="wrapper">
  <%@ include file="/WEB-INF/views/common/menubar.jsp" %>

  <div id="content-wrapper" class="d-flex flex-column">
    <div id="content">
      <%@ include file="/WEB-INF/views/common/topbar.jsp" %>

      <div class="container-fluid">
        <h3 class="h4 mb-4 text-gray-800">평가 등록</h3>

        <form action="${pageContext.request.contextPath}/evaluation/register.do" method="post">
          <!-- 평가 대상 -->
          <div class="card shadow mb-4">
            <div class="card-header font-weight-bold text-primary">평가 대상</div>
            <div class="card-body">
              <div class="form-group row">
                <label class="col-sm-2 col-form-label">사번</label>
				<div class="col-sm-4">
				  <select name="empId" class="form-control" required>
				    <option value="">-- 사번 선택 --</option>
				    <c:forEach var="emp" items="${employeeList}">
				      <option value="${emp.empId}">${emp.empId} - ${emp.empName}</option>
				    </c:forEach>
				  </select>
				</div>
                <label class="col-sm-2 col-form-label">평가명</label>
                <div class="col-sm-4">
                  <input type="text" name="evaluationName" class="form-control" required>
                </div>
              </div>
              <div class="form-group row">
                <label class="col-sm-2 col-form-label">평가일</label>
                <div class="col-sm-4">
                  <input type="date" name="evaluationDay" class="form-control" required>
                </div>
                <label class="col-sm-2 col-form-label">평가기준</label>
                <div class="col-sm-4">
                 <select name="quarter" class="form-control" required>
				  <option value="1분기">25년 1분기</option>
				  <option value="2분기">25년 2분기</option>
				  <option value="3분기">25년 3분기</option>
				  <option value="4분기">25년 4분기</option>
</select>
                </div>
              </div>
            </div>
          </div>

          <!-- 평가 항목 -->
          <div class="card shadow mb-4">
            <div class="card-header font-weight-bold text-primary">평가 항목 (과목당 100점 기준)</div>
            <div class="card-body">
              <div class="form-group row">
                <label class="col-sm-2 col-form-label">영업실적 점수</label>
                <div class="col-sm-4">
                  <input type="number" name="scoreSales" class="form-control" min="0" max="100" required>
                </div>
                <label class="col-sm-2 col-form-label">근무태도 점수</label>
                <div class="col-sm-4">
                  <input type="number" name="scoreAttitude" class="form-control" min="0" max="100" required>
                </div>
              </div>
              <div class="form-group row">
			  <label class="col-sm-2 col-form-label">코멘트</label>
			  <div class="col-sm-10">
			    <textarea name="comments" class="form-control" rows="3" placeholder="평가 코멘트를 입력하세요."></textarea>
			  </div>
			</div>
           </div>
          </div>

          <!-- 등록 버튼 -->
          <div class="text-right">
            <button type="submit" class="btn btn-primary">평가 등록</button>
            <a href="${pageContext.request.contextPath}/evaluation/evaluationList.do" class="btn btn-secondary">목록</a>
          </div>
        </form>
      </div>
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
  </div>
</div>
</body>
</html>
