<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>영업 그래프 대시보드</title>

  <!-- Google Charts -->
  <script src="https://www.gstatic.com/charts/loader.js"></script>

  <!-- jQuery -->
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
        <div class="container-fluid">

          <!-- 타이틀 -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h4 mb-0 text-gray-800">영업 그래프 대시보드</h1>
          </div>

          <!-- 1:1:1 비율 - 가로 3분할 -->
          <div class="row">

            <!-- ① 이익 분석 -->
            <div class="col-md-4 mb-4">
              <div class="card shadow">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">① 이익 분석</h6>
                </div>
                <div class="card-body">
                  <div id="profitChart" style="width:100%; height:300px;"></div>
                </div>
              </div>
            </div>

            <!-- ② 부서별 영업 실적 -->
            <div class="col-md-4 mb-4">
              <div class="card shadow">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">② 부서별 영업 실적</h6>
                </div>
                <div class="card-body">
                  <div id="deptChart" style="width:100%; height:300px;"></div>
                </div>
              </div>
            </div>

            <!-- ③ 거래 건수 -->
            <div class="col-md-4 mb-4">
              <div class="card shadow">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">③ 거래 건수</h6>
                </div>
                <div class="card-body">
                  <div id="countChart" style="width:100%; height:300px;"></div>
                </div>
              </div>
            </div>

          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- JS -->
  <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>

  <!-- Google Charts Script -->
  <script>
    var contextPath = '${pageContext.request.contextPath}';
    google.charts.load("current", { packages: ["corechart"] });
    google.charts.setOnLoadCallback(drawAllCharts);

    function drawAllCharts() {
      drawProfitChart();
      drawDeptChart();
      drawCountChart();
    }

    function drawProfitChart() {
      $.getJSON(contextPath + "/graph/api/profitData.do", function(data) {
        const chartData = [['제품명', '총이익', { role: 'style' }]];
        data.forEach(row => {
          chartData.push([row.productName, row.totalProfit, '#4e73df']);
        });
        const dataTable = google.visualization.arrayToDataTable(chartData);
        const options = { height: 300, legend: 'none' };
        new google.visualization.ColumnChart(document.getElementById('profitChart')).draw(dataTable, options);
      });
    }

    function drawDeptChart() {
      $.getJSON(contextPath + "/graph/api/departmentData.do", function(data) {
        const chartData = [['부서', '실적']];
        data.forEach(row => {
          chartData.push([row.departmentName, row.totalSales]);
        });
        const dataTable = google.visualization.arrayToDataTable(chartData);
        const options = { height: 300 };
        new google.visualization.ColumnChart(document.getElementById('deptChart')).draw(dataTable, options);
      });
    }

    function drawCountChart() {
      $.getJSON(contextPath + "/graph/api/transactionCount.do", function(data) {
        const chartData = [['회사명', '건수']];
        data.forEach(row => {
          chartData.push([row.clientName, row.count]);
        });
        const dataTable = google.visualization.arrayToDataTable(chartData);
        const options = { height: 300, bars: 'horizontal' };
        new google.visualization.BarChart(document.getElementById('countChart')).draw(dataTable, options);
      });
    }
  </script>
</body>
</html>
