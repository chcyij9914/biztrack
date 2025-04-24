<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	 background-color: white;        /* 흰 배경 */
  padding: 50px;                  /* 내부 여백 */
  border-radius: 30px;            /* 둥근 테두리 */
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
				<img src="resources/img/biztracklogo.png" alt="logo">
			</div>
	
			<div class="login">
				<form class="userinfo">
					<input type="text" placeholder="사원번호">
					<input type="password" placeholder="비밀번호">
	
					<div>
						<a href="#" class="dept_no">로그인</a> <br>
						<a href="#">비밀번호 찾기</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
