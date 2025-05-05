<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
  <%-- 전체 페이지로 접근한 경우 --%>
  <c:when test="${empty param.fragment}">
    <!DOCTYPE html>
    <html lang="ko">
    <head>
      <meta charset="UTF-8">
      <title>제품별 영업이익 분석</title>
      <script src="https://www.gstatic.com/charts/loader.js"></script>
      <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
      <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
      <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
    </head>
    <body id="page-top">
      <div id="wrapper">
        <%@ include file="/WEB-INF/views/common/menubar.jsp" %>
        <div id="content-wrapper" class="d-flex flex-column">
          <div id="content">
            <div class="container-fluid">
              <jsp:include page="profitGraphView.jsp">
                <jsp:param name="fragment" value="true"/>
              </jsp:include>
            </div>
          </div>
        </div>
      </div>

      <!-- JS -->
      <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
      <script src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
      <script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>
    </body>
    </html>
  </c:when>

  <%-- fragment 모드: 대시보드에서 import 시 --%>
  <c:otherwise>
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
                <th>판매 수량</th>
                <th>총매출</th>
                <th>매출원가</th>
                <th>총이익</th>
                <th>이익률 (%)</th>
                <th>단위당 이익</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="profitGraph" items="${profitGraphList}">
                <tr>
                  <td>${profitGraph.productName}</td>
                  <td>${profitGraph.outboundQuantity}</td>
                  <td><fmt:formatNumber value="${profitGraph.totalRevenue}" type="number" groupingUsed="true" /></td>
                  <td><fmt:formatNumber value="${profitGraph.totalCost}" type="number" groupingUsed="true" /></td>
                  <td><fmt:formatNumber value="${profitGraph.totalProfit}" type="number" groupingUsed="true" /></td>
                  <td><fmt:formatNumber value="${profitGraph.profitMargin}" type="number" maxFractionDigits="2" /></td>
                  <td><fmt:formatNumber value="${profitGraph.profitPerUnit}" type="number" groupingUsed="true" /></td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>

    <!-- 그래프 JS -->
    <script>
      var contextPath = '${pageContext.request.contextPath}';
      google.charts.load("current", { packages: ["corechart"] });
      google.charts.setOnLoadCallback(drawProfitChart);

      function drawProfitChart() {
        $.getJSON(contextPath + "/graph/api/profitData.do", function(data) {
          const chartData = [['제품명', '총이익', { role: 'style' }]];
          data.forEach(row => {
            chartData.push([row.productName, row.totalProfit, '#4e73df']);
          });

          const dataTable = google.visualization.arrayToDataTable(chartData);
          const options = {
        		width: '100%',
            	height: 400,
            	chartArea: { width: '85%', height: '70%' },
            	fontName: 'Noto Sans KR',
            	legend: 'none'
          };

          new google.visualization.ColumnChart(document.getElementById('profitChart')).draw(dataTable, options);
        });
      }
    </script>
  </c:otherwise>
</c:choose>
