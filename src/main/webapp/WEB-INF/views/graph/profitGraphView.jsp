<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="card shadow mb-4">
  <div class="card-header py-3">
    <h6 class="m-0 font-weight-bold text-primary">제품별 영업이익 분석</h6>
  </div>
  <div class="card-body">
    <div id="profitChart" style="width:100%; height:400px;"></div>

    <div class="table-responsive mt-4">
      <table class="table table-bordered text-center">
        <thead class="thead-light">
          <tr>
            <th>제품명</th>
            <th>출고수량</th>
            <th>판매가</th>
            <th>단가</th>
            <th>총이익</th>
            <th>이익률</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="g" items="${profitGraphList}">
            <tr>
              <td>${g.productName}</td>
              <td>${g.outboundQuantity}</td>
              <td><fmt:formatNumber value="${g.salePrice}" type="number" groupingUsed="true"/></td>
              <td><fmt:formatNumber value="${g.unitPrice}" type="number" groupingUsed="true"/></td>
              <td><fmt:formatNumber value="${g.totalProfit}" type="number" groupingUsed="true"/></td>
              <td><fmt:formatNumber value="${g.profitMargin / 100.0}" type="percent" maxFractionDigits="2"/></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>

<script>
  window.contextPath = window.contextPath || '${pageContext.request.contextPath}';
  google.charts.load("current", { packages: ["corechart"] });
  google.charts.setOnLoadCallback(drawProfitChart);

  function drawProfitChart() {
    $.getJSON(contextPath + "/graph/api/profitGraph.do", function(data) {
      const chartData = [['제품명', '총이익']];
      data.forEach(row => {
        chartData.push([row.productName, row.totalProfit]);
      });

      const dataTable = google.visualization.arrayToDataTable(chartData);
      const options = {
        title: "제품별 총이익",
        height: 400,
        hAxis: {
          title: '제품명',
          slantedText: false,
          textStyle: { fontSize: 12 }
        },
        vAxis: {
          title: '총이익',
          format: 'short'
        },
        legend: { position: 'none' },
        chartArea: { width: '85%', height: '70%' }
      };

      const chart = new google.visualization.ColumnChart(document.getElementById('profitChart'));
      chart.draw(dataTable, options);
    });
  }
</script>
