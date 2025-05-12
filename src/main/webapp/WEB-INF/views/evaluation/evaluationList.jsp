<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
          <!-- 제목과 등록 버튼 -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h4 mb-0 text-gray-800">직원 평가 목록</h1>
            <a href="${pageContext.request.contextPath}/evaluation/registerForm.do" class="btn btn-sm btn-primary">평가 등록</a>
          </div>

          <!-- 세부점수 구성 안내 -->
          <div class="card shadow mb-4">
            <div class="card-header font-weight-bold text-primary">세부점수 (과목당 100점 기준)</div>
            <div class="card-body p-2">
              <table class="table table-bordered text-center mb-0">
                <thead class="thead-light">
                  <tr>
                    <th>실적</th>
                    <th>근태</th>
                    <th>교육</th>
                    <th>총점</th>
                    <th>평균</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>영업실적 점수</td>
                    <td>근무태도 점수</td>
                    <td>교육 점수</td>
                    <td>score 합</td>
                    <td>총점 / 3</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>

          <!-- 연도/분기별 평가이력 버튼 -->
          <div class="card shadow mb-4">
            <div class="card-header font-weight-bold text-primary">평가 이력</div>
            <div class="card-body">
              <table class="table text-center">
                <thead>
                  <tr>
                    <th>2024년</th>
                    <th>2025년</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>
                      <a href="?year=2024&quarter=1분기" class="btn btn-sm btn-outline-dark">1분기</a>
                      <a href="?year=2024&quarter=2분기" class="btn btn-sm btn-outline-dark">2분기</a>
                      <a href="?year=2024&quarter=3분기" class="btn btn-sm btn-outline-dark">3분기</a>
                      <a href="?year=2024&quarter=4분기" class="btn btn-sm btn-outline-dark">4분기</a>
                      <a href="?year=2024&quarter=1반기" class="btn btn-sm btn-outline-secondary">1반기</a>
                      <a href="?year=2024&quarter=2반기" class="btn btn-sm btn-outline-secondary">2반기</a>
                      <a href="?year=2024&quarter=전체" class="btn btn-sm btn-outline-primary">전체</a>
                    </td>
                    <td>
                      <a href="?year=2025&quarter=1분기" class="btn btn-sm btn-outline-dark">1분기</a>
                      <a href="?year=2025&quarter=2분기" class="btn btn-sm btn-outline-dark">2분기</a>
                      <a href="?year=2025&quarter=3분기" class="btn btn-sm btn-outline-dark">3분기</a>
                      <a href="?year=2025&quarter=4분기" class="btn btn-sm btn-outline-dark">4분기</a>
                      <a href="?year=2025&quarter=1반기" class="btn btn-sm btn-outline-secondary">1반기</a>
                      <a href="?year=2025&quarter=2반기" class="btn btn-sm btn-outline-secondary">2반기</a>
                      <a href="?year=2025&quarter=전체" class="btn btn-sm btn-outline-primary">전체</a>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>

          <!-- 평가 목록 테이블 -->
          <div class="card shadow mb-4">
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered text-center" width="100%" cellspacing="0">
                  <thead class="thead-light">
                    <tr>
                      <th>사번</th>
                      <th>부서명</th>
                      <th>사원명</th>
                      <th>평가기준일</th>
                      <th>점수</th>
                      <th>등급</th>
                      <th>cmt</th>
                      <th>기능</th>
                    </tr>
                  </thead>
                  <tbody>
                   
                      <c:if test="${not empty evaluationList}">
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
                              <a href="${pageContext.request.contextPath}/evaluation/detail.do?evaluationId=${eval.evaluationId}" class="btn btn-sm btn-outline-primary">세부점수</a>
                              <a href="${pageContext.request.contextPath}/evaluation/history.do?empId=${eval.empId}&year=2024&quarter=1분기" class="btn btn-sm btn-outline-secondary">이력보기</a>
                            </td>
                          </tr>
                        </c:forEach>
                      </c:if>
                     <c:if test="${empty evaluationList}">
                        <tr>
                          <td colspan="9" class="text-center">조회된 평가 결과가 없습니다.</td>
                        </tr>
                      </c:if>
                    
                  </tbody>
                </table>
              </div>
            </div>
          </div>

          <!-- 등급 안내 -->
          <div class="card shadow mb-4">
            <div class="card-header font-weight-bold text-primary">등급 안내</div>
            <div class="card-body">
              <table class="table table-bordered text-center">
                <thead>
                  <tr><th>A</th><th>B</th><th>C</th><th>D</th><th>E</th><th>F</th></tr>
                </thead>
                <tbody>
                  <tr>
                    <td>300~281</td>
                    <td>261~280</td>
                    <td>241~260</td>
                    <td>221~240</td>
                    <td>201~220</td>
                    <td>0~200</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>

        </div> <!-- /.container-fluid -->
      </div> <!-- /#content -->
      <jsp:include page="/WEB-INF/views/common/footer.jsp" />
    </div> <!-- /#content-wrapper -->
  </div> <!-- /#wrapper -->

  <!-- JS -->
  <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>
</body>
</html>
