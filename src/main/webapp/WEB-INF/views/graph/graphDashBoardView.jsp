<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>영업 그래프 대시보드</title>

  <!-- Google Charts & jQuery -->
  <script src="https://www.gstatic.com/charts/loader.js"></script>
  <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

  <!-- SB Admin 2 CSS -->
  <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
</head>

<body id="page-top">
<div id="wrapper">
  <%@ include file="/WEB-INF/views/common/menubar.jsp" %>

  <div id="content-wrapper" class="d-flex flex-column">
    <div id="content">
      <c:import url="/WEB-INF/views/common/topbar.jsp" />

      <div class="container-fluid">
        <!-- 타이틀 -->
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
          <h1 class="h4 mb-0 text-gray-800">영업 그래프 대시보드</h1>
        </div>

        <!-- 첫 번째 행: 제품별 영업이익, 거래처별 거래건수 -->
        <div class="row">
          <div class="col-md-6 mb-4">
            <c:import url="/graph/profitGraph.do?fragment=true" />
          </div>
          <div class="col-md-6 mb-4">
            <c:import url="/graph/transactionCountGraph.do?fragment=true" />
          </div>
        </div>

        <!-- 두 번째 행: 부서 + 직원 실적 -->
        <div class="row">
          <div class="col-md-12 mb-4">
            <c:import url="/graph/departmentSalesPerformanceGraph.do?fragment=true" />
          </div>
        </div>

      </div> <!-- container-fluid -->
    </div> <!-- content -->

    <c:import url="/WEB-INF/views/common/footer.jsp" />
  </div> <!-- content-wrapper -->
</div> <!-- wrapper -->

<!-- JS -->
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>

<!-- 부서별 파이차트 스크립트 -->
<script>
  window.contextPath = window.contextPath || '${pageContext.request.contextPath}';
  google.charts.load('current', { packages: ['corechart'] });
  google.charts.setOnLoadCallback(drawDepartmentSalesPieChart);

  function drawDepartmentSalesPieChart() {
    $.getJSON(contextPath + "/graph/api/departmentSalesPerformanceGraph.do", function(data) {
      const chartData = [['부서명', '총매출액']];
      data.forEach(row => {
        chartData.push([row.deptName, row.deptSalesAmount]);
      });

      const dataTable = google.visualization.arrayToDataTable(chartData);

      const options = {
        title: '부서별 영업실적 (총매출액 기준)',
        pieHole: 0.4,
        height: 400,
        fontName: 'Noto Sans KR',
        chartArea: { width: '90%', height: '80%' },
        tooltip: { text: 'percentage' },
        legend: { position: 'right', alignment: 'center' },
        pieSliceText: 'value',
        slices: {
          0: { color: '#4e73df' },
          1: { color: '#36b9cc' },
          2: { color: '#1cc88a' },
          3: { color: '#f6c23e' },
          4: { color: '#e74a3b' },
          5: { color: '#858796' },
          6: { color: '#5a5c69' }
        }
      };

      const chart = new google.visualization.PieChart(document.getElementById('departmentSalesChart'));
      chart.draw(dataTable, options);
    });
  }
</script>

</body>
</html>