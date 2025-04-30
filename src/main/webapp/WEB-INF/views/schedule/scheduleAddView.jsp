<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>일정 등록</title>

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">

    <style>
        body {
            padding: 40px;
            background-color: #f9f9f9;
            font-family: 'Segoe UI', 'Malgun Gothic', sans-serif;
        }

        .form-container {
            max-width: 600px;
            margin: 0 auto;
            background: white;
            padding: 30px;
            border-radius: 16px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.08);
        }

        h2 {
            font-weight: 700;
            text-align: center;
            margin-bottom: 25px;
        }

        .form-group label {
            font-weight: 600;
        }

        .btn-submit {
            font-weight: 600;
            padding: 12px;
            border-radius: 8px;
        }
    </style>
</head>

<body>
    <div class="form-container">
        <h2>일정 등록</h2>

        <form action="${pageContext.request.contextPath}/schedule/AutoAddSchedule.do" method="post">


            <div class="form-group">
                <label for="empId">작성자 사번</label>
                <input type="text" class="form-control" name="empId" id="empId" required>
            </div>
            
            <div class="form-group">
    			<label for="empName">직원명</label>
    			<input type="text" class="form-control" name="empName" id="empName" required>
			</div>

            <div class="form-group">
                <label for="scTitle">제목</label>
                <input type="text" class="form-control" name="scTitle" id="scTitle" required>
            </div>

            <div class="form-group">
    			<label for="startDatetime">시작일</label>
    			<input type="datetime-local" name="startDatetime" id="startDatetime" class="form-control" required />
			</div>

			<div class="form-group">
    			<label for="endDatetime">종료일</label>
    			<input type="datetime-local" name="endDatetime" id="endDatetime" class="form-control" required />
			</div>


            <div class="form-group">
                <label for="scType">유형</label>
                <select class="form-control" name="scType" id="scType" onchange="setColorByType()" required>
                    <option value="">-- 선택 --</option>
                    <option value="PUBLIC">공적</option>
                    <option value="PRIVATE">사적</option>
                </select>
            </div>

            <input type="hidden" name="calColor" id="calColor">

            <div class="form-group">
                <label for="place">장소</label>
                <input type="text" class="form-control" name="place" id="place">
            </div>

            <button type="submit" class="btn btn-primary btn-block btn-submit">등록</button>
        </form>
    </div>

    <script>
    function setColorByType() {
        const type = document.getElementById("scType").value;
        const colorInput = document.getElementById("calColor");
        if (type === "PUBLIC") {
            colorInput.value = "RED";
        } else {
            colorInput.value = "BLUE";  // 사적 
        }
    }
    </script>

    <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>
</body>
</html>
