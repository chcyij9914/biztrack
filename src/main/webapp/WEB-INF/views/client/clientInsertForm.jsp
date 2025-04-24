<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>거래처 등록</title>

  <!-- CSS -->
  <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
</head>

<body class="p-4 bg-light">

  <div class="container">
    <h2 class="mb-4">거래처 등록</h2>

    <form action="${pageContext.request.contextPath}/client/insert.do" method="post" enctype="multipart/form-data">

      <div class="form-group">
        <label for="clientName">거래처명</label>
        <input type="text" class="form-control" id="clientName" name="clientName" required>
      </div>

      <div class="form-group">
        <label for="ceoName">대표자명</label>
        <input type="text" class="form-control" id="ceoName" name="ceoName" required>
      </div>

      <div class="form-group">
        <label for="businessNumber">사업자번호</label>
        <input type="text" class="form-control" id="businessNumber" name="businessNumber" required>
      </div>

      <div class="form-group">
        <label for="companyPhone">회사 전화번호</label>
        <input type="text" class="form-control" id="companyPhone" name="companyPhone">
      </div>

      <div class="form-group">
        <label for="fax">팩스번호</label>
        <input type="text" class="form-control" id="fax" name="fax">
      </div>

      <div class="form-group">
        <label for="address">주소</label>
        <input type="text" class="form-control" id="address" name="address">
      </div>

      <div class="form-group">
        <label for="url">홈페이지 URL</label>
        <input type="url" class="form-control" id="url" name="url">
      </div>

      <div class="form-group">
        <label for="contractStartDate">계약 시작일</label>
        <input type="date" class="form-control" id="contractStartDate" name="contractStartDate">
      </div>

      <div class="form-group">
        <label for="contractEndDate">계약 종료일</label>
        <input type="date" class="form-control" id="contractEndDate" name="contractEndDate">
      </div>

      <div class="form-group">
        <label for="firstManagerId">최초 계약자</label>
        <input type="text" class="form-control" id="firstManagerId" name="firstManagerId">
      </div>

      <div class="form-group">
        <label for="currentManagerId">현재 관리자</label>
        <input type="text" class="form-control" id="currentManagerId" name="currentManagerId">
      </div>

      <div class="form-group">
        <label for="directorName">담당자명</label>
        <input type="text" class="form-control" id="directorName" name="directorName">
      </div>

      <div class="form-group">
        <label for="directorPhone">담당자 전화번호</label>
        <input type="text" class="form-control" id="directorPhone" name="directorPhone">
      </div>

      <div class="form-group">
        <label for="email">이메일</label>
        <input type="email" class="form-control" id="email" name="email">
      </div>

      <div class="form-group">
        <label for="contractFile">계약서 첨부</label>
        <input type="file" class="form-control-file" id="contractFile" name="uploadFile">
      </div>

      <button type="submit" class="btn btn-primary">등록</button>
    </form>
  </div>

  <!-- JS -->
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>

</body>
</html>
