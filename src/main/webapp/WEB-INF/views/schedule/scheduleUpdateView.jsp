<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>일정 수정</title>
<link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">일정 수정</h2>
        <form action="${pageContext.request.contextPath}/schedule/UpdateSchedule.do" method="post">
            <input type="hidden" name="scId" value="${schedule.scId}" />

            <div class="form-group">
                <label>작성자 사번</label>
                <input type="text" class="form-control" name="empId" value="${schedule.empId}" readonly>
            </div>

            <div class="form-group">
                <label>직원명</label>
                <input type="text" class="form-control" value="${schedule.empName}" readonly>
            </div>

            <div class="form-group">
                <label>제목</label>
                <input type="text" class="form-control" name="scTitle" value="${schedule.scTitle}" required>
            </div>

           <div class="form-group">
        		<label for="startDatetime">시작일</label>
        		<input type="datetime-local" class="form-control" name="startDatetime"
            	value="<fmt:formatDate value='${schedule.startDatetime}' pattern='yyyy-MM-dd\'T\'HH:mm' />" required />
    		</div>

    		<div class="form-group">
        		<label for="endDatetime">종료일</label>
        		<input type="datetime-local" class="form-control" name="endDatetime"
            	value="<fmt:formatDate value='${schedule.endDatetime}' pattern='yyyy-MM-dd\'T\'HH:mm' />" required />
     		</div>

            <div class="form-group">
                <label>유형</label>
                <select class="form-control" name="scType" onchange="setColorByType()" id="scType">
                    <option value="PUBLIC" ${schedule.scType eq 'PUBLIC' ? 'selected' : ''}>공적</option>
                    <option value="PRIVATE" ${schedule.scType eq 'PRIVATE' ? 'selected' : ''}>사적</option>
                </select>
            </div>

            <input type="hidden" name="calColor" id="calColor" value="${schedule.calColor}" />

            <div class="form-group">
                <label>장소</label>
                <input type="text" class="form-control" name="place" value="${schedule.place}">
            </div>

            <button type="submit" class="btn btn-primary btn-block">저장</button>
        </form>
    </div>

    <script>
    function setColorByType() {
        const type = document.getElementById("scType").value;
        const colorInput = document.getElementById("calColor");
        if (type === "PUBLIC") {
            colorInput.value = "#RED";
        } else {
            colorInput.value = "#BLUE";
        }
    }
    </script>
</body>
</html>
