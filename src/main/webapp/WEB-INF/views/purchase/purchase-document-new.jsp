<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>새 문서 작성</title>

  <!-- Font Awesome -->
  <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">

  <!-- SB Admin 2 CSS -->
  <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
</head>

<body class="p-4 bg-light">

  <div class="container">
    <h2 class="mb-4">새 문서 작성</h2>

    <!-- 추후 Controller에 맞게 action 경로 수정 필요 -->
    <form action="${pageContext.request.contextPath}/purchase/submit-document.do" method="post">

      <div class="form-group">
        <label for="docType">문서 종류</label>
        <select class="form-control" id="docType" name="docType" required>
          <option value="">선택하세요</option>
          <option value="구매품의서">구매품의서</option>
          <option value="지출결의서">지출결의서</option>
        </select>
      </div>

      <div class="form-group">
        <label for="docTitle">제목</label>
        <input type="text" class="form-control" id="docTitle" name="docTitle" required>
      </div>

      <div class="form-group">
        <label for="partner">거래처명</label>
        <input type="text" class="form-control" id="partner" name="partner" required>
      </div>

      <div class="form-group">
        <label for="status">상태</label>
        <select class="form-control" id="status" name="status">
          <option value="결재중">결재중</option>
          <option value="결재완료">결재완료</option>
        </select>
      </div>

      <div class="form-group">
        <label for="date">작성일</label>
        <input type="date" class="form-control" id="date" name="date" required>
      </div>

      <button type="submit" class="btn btn-primary">상신하기</button>
    </form>
  </div>

  <!-- JS -->
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>

</body>
</html>