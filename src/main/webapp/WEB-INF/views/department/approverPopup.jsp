<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>결재자 선택</title>
  <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
</head>
<body class="bg-light">
<div class="container mt-4">
  <div class="card shadow">
    <div class="card-header bg-primary text-white">
      <h5 class="m-0">결재자 선택</h5>
    </div>
    <div class="card-body">
      <div class="form-group">
        <c:import url="/WEB-INF/views/department/orgTree.jsp" />
      </div>
      <div class="text-right mt-4">
        <button type="button" class="btn btn-primary" onclick="applyToParent()">결재자 적용</button>
      </div>
      <input type="text" id="approver1" class="form-control mt-3" placeholder="1차 결재자" readonly />
      <input type="text" id="approver2" class="form-control mt-2" placeholder="2차 결재자" readonly />
    </div>
  </div>
</div>

<script>
function applyToParent() {
  const approver1Id = $("#approver1").attr("data-real-id") || "";
  const approver2Id = $("#approver2").attr("data-real-id") || "";

  if (window.opener && !window.opener.closed) {
    window.opener.applyApprovers(approver1Id, approver2Id);
    window.close();
  }
}
</script>
</body>
</html>
