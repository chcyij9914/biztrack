<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>메일 전송 결과</title>
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
            text-align: center;
            padding: 60px;
            background-color: #f4f6f9;
        }

        .logo {
            width: 180px;
            margin-bottom: 30px;
        }

        .box {
            border: 1px solid #ddd;
            padding: 40px;
            border-radius: 20px;
            display: inline-block;
            background: #ffffff;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
        }

        .success {
            color: #4A6CF7; /* 메일 보내기 버튼 계열 */
        }

        .fail {
            color: #D93025; /* 부드러운 강한 레드 */
        }

        .btn {
            padding: 12px 30px;
            font-size: 16px;
            margin-top: 25px;
            border: none;
            border-radius: 10px;
            cursor: pointer;
            font-weight: bold;
        }

        .btn-success {
            background: #4A6CF7;
            color: white;
        }

        .btn-fail {
            background: #D93025;
            color: white;
        }

        .emoji {
            font-size: 1.4em;
            margin-left: 6px;
        }
    </style>
</head>
<body>
    <div class="box">
        <c:choose>
            <c:when test="${result eq 'success'}">
                <h2 class="success">메일 전송 완료! <span class="emoji">😊</span></h2>
                <form action="${pageContext.request.contextPath}/mail/form.do" method="get">
                    <button type="submit" class="btn btn-success">새 메일 작성</button>
          
            </c:when>
            <c:otherwise>
                <h2 class="fail">메일 전송 실패 <span class="emoji">😥</span></h2>
                <form action="${pageContext.request.contextPath}/mail/form.do" method="get">
                    <button type="submit" class="btn btn-fail">이전으로</button>
                </form>
            </c:otherwise>
        </c:choose>
    </div>

</body>
</html>
