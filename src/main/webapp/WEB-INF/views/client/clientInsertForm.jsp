<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>거래처 등록</title>

  <!-- SB Admin 2 CSS / Bootstrap -->
  <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body class="p-4 bg-light">

<div class="container">
  <h3 class="mb-4 text-gray-800">거래처 등록</h3>

  <form action="${pageContext.request.contextPath}/client/insert.do" method="post" enctype="multipart/form-data">
    
    <!-- 거래처 기본 정보 -->
    <div class="form-group">
      <label for="clientName">거래처명</label>
      <input type="text" name="clientName" id="clientName" class="form-control" required>
    </div>

    <div class="form-group">
      <label for="ceoName">대표자명</label>
      <input type="text" name="ceoName" id="ceoName" class="form-control" required>
    </div>

    <div class="form-group">
      <label for="businessNumber">사업자번호</label>
      <input type="text" name="businessNumber" id="businessNumber" class="form-control" required>
    </div>

    <div class="form-group">
      <label for="companyPhone">회사 전화번호</label>
      <input type="text" name="companyPhone" id="companyPhone" class="form-control">
    </div>

    <div class="form-group">
      <label for="fax">팩스번호</label>
      <input type="text" name="fax" id="fax" class="form-control">
    </div>

    <div class="form-group">
      <label for="address">주소</label>
      <input type="text" name="address" id="address" class="form-control">
    </div>

    <div class="form-group">
      <label for="url">홈페이지 URL</label>
      <input type="text" name="url" id="url" class="form-control">
    </div>

    <!-- 계약 정보 -->
    <div class="form-row">
      <div class="form-group col-md-6">
        <label for="contractStartDate">계약 시작일</label>
        <input type="date" name="contractStartDate" id="contractStartDate" class="form-control">
      </div>
      <div class="form-group col-md-6">
        <label for="contractEndDate">계약 종료일</label>
        <input type="date" name="contractEndDate" id="contractEndDate" class="form-control">
      </div>
    </div>

    <div class="form-row">
      <div class="form-group col-md-6">
        <label for="firstManagerId">최초 계약자 ID</label>
        <input type="text" name="firstManagerId" id="firstManagerId" class="form-control">
      </div>
      <div class="form-group col-md-6">
        <label for="currentManagerId">현재 관리자 ID</label>
        <input type="text" name="currentManagerId" id="currentManagerId" class="form-control">
      </div>
    </div>

    <!-- 담당자 정보 -->
    <div class="form-group">
      <label for="directorName">담당자명</label>
      <input type="text" name="directorName" id="directorName" class="form-control">
    </div>

    <div class="form-group">
      <label for="directorPhone">담당자 전화번호</label>
      <input type="text" name="directorPhone" id="directorPhone" class="form-control">
    </div>

    <div class="form-group">
      <label for="email">이메일</label>
      <input type="email" name="email" id="email" class="form-control">
    </div>

    <!-- 파일 첨부 영역 -->
    <div class="form-group">
      <label for="contractFile">계약서 파일 첨부</label>
      <input type="file" name="contractFile" id="contractFile" class="form-control-file">
    </div>

    <!-- 명함 이미지 첨부 및 적용하기 -->
    <div class="form-group">
      <label for="businessCard">명함 이미지 업로드</label>
      <input type="file" name="businessCard" id="businessCard" class="form-control-file">
      <button type="button" id="applyBusinessCardBtn" class="btn btn-info mt-2">적용하기</button>
    </div>

    <!-- 등록 버튼 -->
    <button type="submit" class="btn btn-primary">등록하기</button>
  </form>
</div>

<!-- JS -->
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>

<!-- 명함 OCR 적용하기 Ajax -->
<script>
$(function() {
  $('#applyBusinessCardBtn').click(function() {
    var fileInput = $('#businessCard')[0];
    if (fileInput.files.length === 0) {
      alert('명함 이미지를 선택해주세요.');
      return;
    }

    var formData = new FormData();
    formData.append('businessCard', fileInput.files[0]);

    $.ajax({
      url: '${pageContext.request.contextPath}/client/applyBusinessCard.do',
      type: 'POST',
      data: formData,
      processData: false,
      contentType: false,
      success: function(result) {
        // OCR 결과를 각각 채워 넣기
        $('#directorName').val(result.name);
        $('#directorPhone').val(result.phone);
        $('#email').val(result.email);
        $('#companyPhone').val(result.tel);
        $('#fax').val(result.fax);
        $('#address').val(result.address);
      },
      error: function() {
        alert('명함 인식에 실패했습니다. 다시 시도해주세요.');
      }
    });
  });
});
</script>

</body>
</html>
