<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
  <c:when test="${empty param.fragment}">
    <!DOCTYPE html>
    <html lang="ko">
    <head>
      <meta charset="UTF-8">
      <title>제품별 영업이익 분석</title>
      <script src="https://www.gstatic.com/charts/loader.js"></script>
      <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    </head>
    <body>
      <jsp:include page="profitGraphView.jsp">
        <jsp:param name="fragment" value="true" />
      </jsp:include>
    </body>
    </html>
  </c:when>

  <c:otherwise>
    <div class="card shadow mb-4">
      <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">제품별 영업이익 분석</h6>
      </div>
      <div class="card-body">
        <!-- 그래프 영역 -->
        <div id="profitChart" style="width:100%; height:400px;"></div>

        <!-- 표 영역 -->
        <div class="table-responsive mt-4">
          <table class="table table-bordered text-center">
            <thead class="thead-light">
              <tr>
                <th>제품명</th>
                <th>출고수량</th>
                <th>판매가</th>
                <th>원가</th>
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
                  <td><fmt:formatNumber value="${g.costPrice}" type="number" groupingUsed="true"/></td>
                  <td><fmt:formatNumber value="${g.totalProfit}" type="number" groupingUsed="true"/></td>
                  <td><fmt:formatNumber value="${g.profitMargin}" type="percent" maxFractionDigits="2"/></td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>

    <!-- Google Chart Script -->
    <script>
      var contextPath = '${pageContext.request.contextPath}';
      google.charts.load("current", { packages: ["corechart"] });
      google.charts.setOnLoadCallback(drawProfitChart);

      function drawProfitChart() {
        $.getJSON(contextPath + "/graph/api/profitData.do", function(data) {
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
  </c:otherwise>
</c:choose>
