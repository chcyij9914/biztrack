<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 부서 + 직원 실적 통합 블록 -->
<div class="card shadow mb-4">
  <div class="card-header py-3">
    <h6 class="m-0 font-weight-bold text-primary">부서별 + 직원별 영업실적</h6>
  </div>
  <div class="card-body">
    <div class="row">
      <!-- 왼쪽: 부서 그래프 + 표 -->
      <div class="col-md-6">
        <div id="departmentSalesChart" style="width: 100%; height: 400px;"></div>
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
                  <td><fmt:formatNumber value="${g.deptSalesAmount}" type="number" groupingUsed="true" /> 원</td>
                  <td>${g.deptTransactionCount}</td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>

      <!-- 오른쪽: 직원별 실적 -->
      <div class="col-md-6">
        <h6 class="font-weight-bold text-secondary mb-3">직원별 실적 상세</h6>
        <table class="table table-bordered text-center small">
          <thead class="thead-light">
            <tr>
              <th>이름</th>
              <th>부서명</th>
              <th>거래실적</th>
              <th>거래상황</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="s" items="${empGraphList}">
              <tr>
                <td>${s.empName}</td>
                <td>
  				<c:if test="${not empty s.deptName}">
    			<span class="text-muted">${s.deptName}</span>
  				</c:if>
				</td>
                <td>${s.empTransactionCount}건</td>
                <td>${s.empTransactionSummary}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>

<!-- 부서별 그래프용 JS (함수 중복되지 않게 별도 관리 권장) -->
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
        pieSliceText: 'value'
      };

      const chart = new google.visualization.PieChart(document.getElementById('departmentSalesChart'));
      chart.draw(dataTable, options);
    });
  }
</script>
