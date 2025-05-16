<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty sessionScope.loginInfo}">
    <c:redirect url="/login.do" />
</c:if>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>메일쓰기</title>
    <style>
        body {
            margin: 0;
            font-family: 'Noto Sans KR', sans-serif;
            background-color: #e6ecf1;
        }

        .container {
            display: flex;
            height: 100vh;
            box-sizing: border-box;
        }

        .content {
            flex: 1;
            padding: 40px;
            background-color: #fff;
        }

        .content img {
            width: 160px;
            margin-bottom: 30px;
        }

        .content h2 {
            margin-bottom: 25px;
            font-size: 22px;
            color: #333;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"], input[type="email"], textarea {
            width: 100%;
            padding: 10px;
            border-radius: 6px;
            border: 1px solid #ccc;
            font-size: 14px;
            box-sizing: border-box;
        }

        textarea {
            resize: vertical;
            height: 250px;
        }

        .attach-box {
            border: 1px dashed #ccc;
            padding: 15px;
            border-radius: 10px;
            margin-top: 10px;
            background-color: #f9f9f9;
        }

        .attach-box p {
            margin: 0;
            font-size: 14px;
            color: #555;
        }

        .btn-area {
            display: flex;
            justify-content: center;
            gap: 15px;
            margin-top: 30px;
        }

        .btn-common {
            padding: 12px 30px;
            border: none;
            border-radius: 6px;
            font-size: 15px;
            cursor: pointer;
        }

        .send-btn {
            background-color: #4292f7;
            color: white;
        }

        .send-btn:hover {
            background-color: #2e76d6;
        }

        .back-btn {
            background-color: #ccc;
            color: #333;
        }

        .back-btn:hover {
            background-color: #aaa;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="content">
        <a href="${pageContext.request.contextPath}/main.do">
            <img src="${pageContext.request.contextPath}/resources/img/biztracklogo.png" alt="BIZTRACK">
        </a>

        <h2>메일쓰기</h2>

        <form method="post" action="${pageContext.request.contextPath}/reminder/sendEmail.do" enctype="multipart/form-data">
            <div class="form-group">
                <label for="to">받는 사람</label>
                <input type="email" id="to" name="to" required value="${param.clientEmail}" placeholder="받는 사람 이메일 입력">
            </div>

            <div class="form-group">
                <label for="subject">제목</label>
                <input type="text" id="subject" name="subject" required placeholder="메일 제목을 입력하세요">
            </div>

            <div class="form-group">
                <label for="fromEmail">보낸 사람</label>
                <input type="email" id="fromEmail" name="fromEmail" value="${loginInfo.email}" readonly>
            </div>

            <div class="form-group">
                <label for="text">내용</label>
                <textarea id="text" name="text" placeholder="메일 내용 입력란"></textarea>
            </div>

            <div class="form-group">
                <label for="file">첨부파일</label>
                <div class="attach-box">
                    <input type="file" id="file" name="file">
                    <p>※ 첨부파일은 5MB 이상, 20MB 이하만 허용됩니다.</p>
                </div>
            </div>

            <!-- reminderId도 같이 전달 (숨김 처리) -->
            <input type="hidden" name="reminderId" value="${param.reminderId}" />

            <div class="btn-area">
                <button type="submit" class="btn-common send-btn">메일 보내기</button>
                <a href="${pageContext.request.contextPath}/reminder/ReminderList.do" class="btn-common back-btn">목록으로</a>
            </div>
        </form>
    </div>
</div>

</body>
</html>
