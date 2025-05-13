<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="card shadow mb-4">
  <div class="card-header py-3">
    <h6 class="m-0 font-weight-bold text-primary">부서별 영업실적</h6>
  </div>
  <div class="card-body">
    <!-- 그래프 영역 -->
    <div id="departmentSalesChart" style="width: 100%; height: 400px;"></div>

    <!-- 표 영역 -->
    <div class="mt-4">
      <table class="table table-bordered text-center small">
        <thead class="thead-light">
          <tr>
            <th>부서명</th>
            <th>총매출액</th>
            <th>거래건수</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="g" items="${deptGraphList}">
            <tr>
              <td>${g.deptName}</td>
              <td><fmt:formatNumber value="${g.deptSalesAmount}" type="number" groupingUsed="true"/> 원</td>
              <td>${g.deptTransactionCount}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>

<!-- Google Chart Script (PieChart 버전) -->
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
        pieHole: 0.4,  // 도넛형. 일반 원형으로 바꾸려면 이 줄 제거
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
