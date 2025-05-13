<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="card shadow mb-4">
  <div class="card-header py-3">
    <h6 class="m-0 font-weight-bold text-primary">거래처별 거래실적</h6>
  </div>
  <div class="card-body">
    <div id="countChart" style="width:100%; height:400px;"></div>

    <div class="table-responsive mt-4">
      <table class="table table-bordered text-center">
        <thead class="thead-light">
          <tr>
            <th>거래처명</th>
            <th>거래건수</th>
            <th>총거래금액</th>
            <th>최근거래일</th>
            <th>담당자명</th>
            <th>담당자연락처</th>
            <th>계약상태</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="tc" items="${transactionCountList}">
            <tr>
              <td>${tc.clientName}</td>
              <td>${tc.count}</td>
              <td><fmt:formatNumber value="${tc.totalAmount}" type="number" groupingUsed="true"/></td>
              <td>${tc.lastTransactionDate}</td>
              <td>${tc.managerName}</td>
              <td>${tc.managerPhone}</td>
              <td>${tc.clientStatus}</td>
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
  google.charts.setOnLoadCallback(drawCountChart);

  function drawCountChart() {
    $.getJSON(contextPath + "/graph/api/transactionCountGraph.do", function(data) {
      const chartData = [['거래처명', '거래건수']];
      data.forEach(row => {
        chartData.push([row.clientName, row.count]);
      });

      const dataTable = google.visualization.arrayToDataTable(chartData);
      const options = {
        height: 400,
        chartArea: { width: '85%', height: '70%' },
        fontName: 'Noto Sans KR',
        legend: { position: 'none' },
        hAxis: { title: '거래처명', slantedText: false },
        vAxis: { title: '거래건수' },
        colors: ['#36b9cc']
      };

      new google.visualization.ColumnChart(document.getElementById('countChart')).draw(dataTable, options);
    });
  }
</script>
