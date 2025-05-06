<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>인증번호 확인</title>
  <style>
    body {
      background-color: #f4f6f9;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      font-family: 'Segoe UI', sans-serif;
    }
    .verify-container {
      background: white;
      padding: 40px 50px;
      border-radius: 30px;
      box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
      text-align: center;
      width: 350px;
    }
    input, button {
      width: 100%;
      padding: 12px;
      margin: 10px 0;
      border-radius: 10px;
      border: 1px solid #ddd;
    }
    button {
      background-color: #4e73df;
      color: white;
      border: none;
      font-size: 16px;
      cursor: pointer;
    }
  </style>
</head>
<body>
<div class="verify-container">
  <h2>인증번호 확인</h2>
  <form method="post" action="verifyAuthCode.do">
    <input type="text" name="authCode" placeholder="인증번호 6자리 입력" required />
    <button type="submit">확인</button>
  </form>
</div>
</body>
</html>
