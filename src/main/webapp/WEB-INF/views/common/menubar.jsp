<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>biztrack</title>
<style>
.sidebar {
    width: 200px;
    background-color: #f1f1f1;
    padding: 10px;
    height: 100vh;
}

.sidebar ul {
    list-style-type: none;
    padding: 0;
}

.sidebar ul li {
    margin: 10px 0;
}

.sidebar ul li a {
    text-decoration: none;
    color: #333;
    display: block;
    padding: 8px;
    background-color: #e4e4e4;
    border-radius: 4px;
}

.sidebar ul li a:hover {
    background-color: #d0d0d0;
}
</style>
</head>
<body>

<div class="sidebar">
	<ul>
		<li><a href="#">공지사항</a></li>
		<li><a href="#">메일</a></li>
		<li><a href="#">인사</a></li>
		<li><a href="#">구매</a></li>
		<li><a href="#">상품관리</a></li>
		<li><a href="#">영업</a></li>
		<li><a href="#">거래처</a></li>
		<li><a href="#">교육관리</a></li>
	</ul>
</div>

</body>
</html>
