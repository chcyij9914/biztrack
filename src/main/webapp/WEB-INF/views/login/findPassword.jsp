<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>비밀번호 찾기</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
    .reset-container input, .reset-container button {
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
    .reset-container .sub-btn {
      background-color: #6c757d;
      margin-top: 10px;
    }
    .info {
      font-size: 13px;
      color: gray;
    }
    .timer {
      font-size: 13px;
      color: red;
      margin-top: 5px;
    }
  </style>
</head>
<body>
<div class="reset-container">
  <img src="${pageContext.request.contextPath}/resources/img/biztracklogo.png" alt="BizTrack">

  <input type="text" id="empId" placeholder="사원번호 입력" required />
  <input type="email" id="email" placeholder="이메일 입력" required />
  <button id="sendBtn" type="button">인증번호 발송</button>

  <form method="post" action="verifyCode.do" id="authForm" style="display:none;">
    <input type="text" name="authCode" placeholder="인증번호 6자리 입력" required />
    <button type="submit" class="sub-btn">인증 확인</button>
    <p id="timerText" class="timer" style="display:none;"></p>
  </form>

  <p class="info">입력한 이메일로 인증번호가 전송됩니다.</p>
</div>

<script>
  $('#sendBtn').click(function () {
    const empId = $('#empId').val();
    const email = $('#email').val();

    $.post('sendAuthCode.do', { empId, email }, function (res) {
      if (res === 'invalid') {
        alert('입력한 사원번호 또는 이메일이 올바르지 않습니다.');
      } else {
        alert('인증번호가 이메일로 전송되었습니다.');
        $('#empId, #email').attr('readonly', true);
        $('#authForm').show();
        startTimer(300); // 5분 타이머 시작
      }
    });
  });

  function startTimer(duration) {
    let timer = duration;
    const $display = $('#timerText');
    $display.show();

    const interval = setInterval(function () {
      const minutes = String(Math.floor(timer / 60)).padStart(2, '0');
      const seconds = String(timer % 60).padStart(2, '0');
      $display.text("인증번호 유효시간: " + minutes + ":" + seconds);

      if (--timer < 0) {
        clearInterval(interval);
        $display.text("⛔ 인증번호 시간이 만료되었습니다. 다시 요청해주세요.");
      }
    }, 1000);
  }
</script>
</body>
</html>
