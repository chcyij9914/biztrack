<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

        .content img {
            width: 160px;
            margin-bottom: 30px;
        }

        .content {
            flex: 1;
            padding: 40px;
            background-color: #fff;
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

        .send-btn {
            padding: 12px 30px;
            background-color: #4292f7;
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 15px;
            cursor: pointer;
            margin-top: 25px;
        }

        .send-btn:hover {
            background-color: #2e76d6;
        }
    </style>
</head>
<body>

<div class="container">
    <!-- 메일 작성 영역 -->
    <div class="content">
    	<a href="${pageContext.request.contextPath}/main.do">
        <img src="${pageContext.request.contextPath}/resources/img/biztracklogo.png" alt="BIZTRACK">
        </a>
    	
        <h2>메일쓰기</h2>

        <form method="post" action="${pageContext.request.contextPath}/mail/send.do" enctype="multipart/form-data">
            <div class="form-group">
                <label for="to">받는 사람</label>
                <input type="email" id="to" name="to" required placeholder="받는 사람 이메일 입력">
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

            <button type="submit" class="send-btn">메일 보내기</button>
            <button type="button" class="send-btn" onclick="history.back();">뒤로가기</button>
        </form>
    </div>
</div>

</body>
</html>
