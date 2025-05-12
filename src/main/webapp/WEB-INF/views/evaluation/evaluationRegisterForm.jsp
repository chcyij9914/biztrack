<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                  <input type="text" name="empId" class="form-control" required>
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
                  <select name="recordId" class="form-control" required>
                    <option value="2023Q1">25년1분기</option>
                    <option value="2023Q2">25년2분기</option>
                    <option value="2023Q3">25년3분기</option>
                    <option value="2023Q4">25년4분기</option>
                    <option value="2023H1">25년1반기</option>
                    <option value="2023H2">25년2반기</option>
                    <option value="2023ALL">25년전체</option>
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
                <label class="col-sm-2 col-form-label">교육 점수</label>
                <div class="col-sm-4">
                  <input type="number" name="scoreTraining" class="form-control" min="0" max="100" required>
                </div>
                <label class="col-sm-2 col-form-label">코멘트</label>
                <div class="col-sm-4">
                  <input type="text" name="comments" class="form-control">
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
