<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>비밀번호 변경</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css">
  <style>
    body {
      background-color: #f4f6f9;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      font-family: 'Segoe UI', sans-serif;
    }
    .reset-container {
      background: white;
      padding: 40px 50px;
      border-radius: 30px;
      box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
      text-align: center;
      width: 350px;
    }
    .reset-container img {
      width: 200px;
      margin-bottom: 30px;
    }
    .reset-container input,
    .reset-container button {
      width: 100%;
      padding: 12px;
      margin: 10px 0;
      border: 1px solid #ddd;
      border-radius: 10px;
      font-size: 14px;
      box-sizing: border-box;
    }
    .reset-container button {
      background-color: #4e73df;
      color: white;
      border: none;
      font-size: 16px;
      cursor: pointer;
    }
    .info {
      font-size: 13px;
      color: gray;
    }
  </style>
  <script>
    function validatePassword() {
      const pwd = document.getElementsByName("newPwd")[0].value;
      const confirm = document.getElementsByName("confirmPwd")[0].value;
      const regex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@!#$%&])[A-Za-z\d@!#$%&]{8,}$/;

      if (!regex.test(pwd)) {
        alert("비밀번호는 영문, 숫자, 특수문자(@!#$%&) 포함 8자 이상이어야 합니다.");
        return false;
      }
      if (pwd !== confirm) {
        alert("비밀번호가 일치하지 않습니다.");
        return false;
      }
      return true;
    }
  </script>
</head>
<body>
<div class="reset-container">
  <img src="${pageContext.request.contextPath}/resources/img/biztracklogo.png" alt="BizTrack">

  <form method="post" action="updatePassword.do" onsubmit="return validatePassword();">
  <input type="hidden" name="empId" value="${empId}" />
  <input type="password" name="newPwd" placeholder="새 비밀번호" required />
  <input type="password" name="confirmPwd" placeholder="새 비밀번호 확인" required />
  <button type="submit">변경</button>
</form>

<!-- 서버 유효성 검사 메시지 출력 -->
<c:if test="${not empty error}">
  <p style="color:red; font-size: 14px;">${error}</p>
</c:if>

  <p class="info">비밀번호는 특수문자 포함 8자 이상으로 입력해주세요.</p>
</div>
</body>
</html>
