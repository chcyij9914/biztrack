<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>loginpage</title>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body, html {
	height: 100%;
	font-family: 'Segoe UI', sans-serif;
}

.login_mapping {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	height: 100%; /* 화면 전체 높이에서 정중앙 정렬 */
}

.login_wrap {
	background-color: white; /* 흰 배경 */
	padding: 50px; /* 내부 여백 */
	border-radius: 30px; /* 둥근 테두리 */
	box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2); /* 그림자 */
	text-align: center;
}

.loginimg img {
	max-width: 300px; /* 로고 이미지 크기 조정 */
	margin-bottom: 20px;
}

.login {
	text-align: center;
}

.login h1 {
	margin-bottom: 20px;
}

.userinfo input {
	display: block;
	width: 300px;
	padding: 12px;
	margin: 10px auto;
	font-size: 16px;
	border: 1px solid #ccc;
	border-radius: 6px;
}

.login a {
	text-decoration: none;
	color: black;
	font-weight: bold;
	margin: 0 10px;
	font-size: 14px;
}

.login a:hover {
	text-decoration: underline;
}

.dept_no {
	display: block;
	width: 300px;
	padding: 12px;
	margin: 10px auto;
	font-size: 16px;
	color: white !important;
	border: 1px solid #ccc;
	border-radius: 6px;
	background-color: #4e73df;
	text-align: center;
	text-decoration: none;
}
</style>
</head>
<body>
	<div class="login_mapping">
		<div class="login_wrap">
			<div class="loginimg">
				<img
					src="${pageContext.request.contextPath}/resources/img/biztracklogo.png"
					alt="logo">
			</div>
			<!-- 퇴직자 로그인 차단 -->
			<c:if test="${not empty error}">
				<div class="error" style="color: red; margin-top: 10px;">
					${error}</div>
			</c:if>


			<!-- ① action, method, name 속성 반드시 지정 -->
			<form class="userinfo"
				action="${pageContext.request.contextPath}/loginCheck.do"
				method="post">
				<input type="text" name="empId" placeholder="사원번호" required /> <input
					type="password" name="empPwd" placeholder="비밀번호" required />

				<div>
					<button type="submit" class="dept_no">로그인</button>
					<br> <a
						href="${pageContext.request.contextPath}/findPassword.do">비밀번호
						찾기</a>
				</div>
			</form>

			<!-- 로그인 실패 메시지 출력 -->
			<c:if test="${not empty msg}">
				<div class="error">${msg}</div>
			</c:if>
		</div>
	</div>

</body>
</html>
