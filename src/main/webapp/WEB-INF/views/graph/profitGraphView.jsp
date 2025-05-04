<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>영업이익 분석 그래프</title>

  <!-- Google Charts -->
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

  <!-- jQuery -->
  <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

  <script type="text/javascript">
    google.charts.load("current", {
      packages: ["corechart"]
    });
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
      var contextPath = '${pageContext.request.contextPath}';

      $.ajax({
        url: contextPath + '/graph/api/profitData.do',
        method: 'GET',
        dataType: 'json',
        success: function (data) {
          var chartData = [['제품명', '총이익', { role: 'style' }]];
          
          data.forEach(function (row) {
            chartData.push([
              row.productName,
              row.totalProfit,
              '#4e73df'
            ]);
          });

          var dataTable = google.visualization.arrayToDataTable(chartData);

          var options = {
            title: '제품별 총 영업이익 분석',
            height: 500,
            fontName: 'Noto Sans KR',
            hAxis: {
              title: '제품명',
              textStyle: { fontSize: 12 }
            },
            vAxis: {
              title: '총이익 (원)',
              textStyle: { fontSize: 12 }
            },
            legend: 'none'
          };

          var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
          chart.draw(dataTable, options);
        },
        error: function () {
          alert("그래프 데이터를 불러오는 데 실패했습니다.");
        }
      });
    }
  </script>
</head>
<body>
  <div style="width: 90%; margin: 0 auto; padding-top: 30px;">
    <h2 style="text-align:center;">제품별 영업이익 분석 그래프</h2>
    <div id="chart_div" style="width:100%; height:500px;"></div>
  </div>
</body>
</html>
