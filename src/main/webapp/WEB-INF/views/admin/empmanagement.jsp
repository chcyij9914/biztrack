<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${empty sessionScope.loginInfo}">
	<c:redirect url="/login.do" />
</c:if>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>직원관리</title>
<link
	href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,400,700,900"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css"
	rel="stylesheet">
<style>
.pagination .page-link {
	border: none;
	background-color: transparent;
}

.pagination .page-item.active .page-link {
	background-color: transparent;
	color: #4e73df;
	font-weight: bold;
	border: none;
}

.pagination .page-link:hover {
	background-color: #f0f0f0;
}

img.icon {
	width: 18px;
	height: 18px;
}

.table th,
.table td {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  vertical-align: middle;
}

.table th:nth-child(1), .table td:nth-child(1) { width: 10%; }  /* 직원번호 */
.table th:nth-child(2), .table td:nth-child(2) { width: 8%; }   /* 직급 */
.table th:nth-child(3), .table td:nth-child(3) { width: 12%; }  /* 이름 */
.table th:nth-child(4), .table td:nth-child(4) { width: 15%; }  /* 전화번호 */
.table th:nth-child(5), .table td:nth-child(5) { width: 25%; }  /* 이메일 */
.table th:nth-child(6), .table td:nth-child(6),
.table th:nth-child(7), .table td:nth-child(7) { width: 10%; text-align: center; } /* 수정/삭제 */


.modal-overlay {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
	display: flex;
	align-items: center;
	justify-content: center;
	z-index: 9999;
}

.modal-content {
	background: #fff;
	padding: 30px 40px;
	border-radius: 6px;
	width: 400px;
	max-width: 90%;
	max-height: 80vh;
	overflow-y: auto;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
}

.modal-content h2 {
	font-size: 20px;
	margin-bottom: 20px;
	border-bottom: 1px solid #ccc;
	padding-bottom: 10px;
}

.form-group {
	margin-bottom: 15px;
}

.form-group label {
	display: block;
	margin-bottom: 5px;
	font-weight: 500;
}

.form-control {
	width: 100%;
	padding: 8px 10px;
	border: 1px solid #ddd;
	border-radius: 4px;
	background-color: #f5f7fa;
}

.btn-group {
	display: flex;
	justify-content: flex-end;
	gap: 10px;
}

.btn {
	padding: 8px 18px;
	border: none;
	cursor: pointer;
	border-radius: 4px;
}

.btn-primary {
	background-color: #0062ff;
	color: white;
}

.btn-secondary {
	background-color: #ddd;
	color: black;
}
</style>
</head>
<body id="page-top">
	<div id="wrapper">
		<c:import url="/WEB-INF/views/common/menubar.jsp" />
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<c:import url="/WEB-INF/views/common/topbar.jsp" />
				<div class="container-fluid">
					<div class="d-flex justify-content-between align-items-center mb-2">
						<div>
							<h1 class="h3 text-gray-800 mb-1">직원 정보</h1>
						</div>
						<div class="d-flex align-items-center">
							<a href="javascript:void(0);"
								class="btn btn-primary px-3 py-2 mr-3"
								onclick="openRegisterModal()">+ 직원 등록</a>
							<form class="form-inline"
								action="${pageContext.request.contextPath}/adminMain.do"
								method="get">
								<input type="text" class="form-control mr-2" name="keyword"
									placeholder="직원 검색" value="${keyword}" />
								<button type="submit" class="btn btn-outline-primary">검색</button>
							</form>
						</div>
					</div>
					<div class="card-body">
						<div class="table-responsive">
						  <div style="overflow-x: auto;">
							<table class="table table-bordered"
								style="table-layout: fixed; width: 100%;">
								<thead class="text-center bg-light">
									<tr>
										<th>직원번호</th>
										<th>직급</th>
										<th>이름</th>
										<th>전화번호</th>
										<th>이메일</th>
										<th>수정</th>
										<th>삭제</th>
									</tr>
								</thead>
								<tbody class="text-center bg-white">
									<c:forEach var="emp" items="${employeeList}">
										<tr>
											<td>${emp.empId}</td>
											<td>${emp.jobId}</td>
											<td>${emp.empName}</td>
											<td>${emp.phone}</td>
											<td>${emp.email}</td>
											<td><a href="javascript:void(0);"
												onclick="openUpdateModal('${emp.empId}')"><img
													src="resources/img/update.png" class="icon" alt="수정아이콘"></a></td>
											<td><a href="javascript:void(0);"
												onclick="deleteEmployee('${emp.empId}')"><img
													src="resources/img/delete.png" class="icon" alt="삭제아이콘"></a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					  </div>
					</div>
					<nav aria-label="Page navigation example">
						<ul class="pagination justify-content-center">
							<c:if test="${currentPage > 1}">
								<li class="page-item"><a class="page-link"
									href="?page=${currentPage - 1}&keyword=${keyword}">«
										Previous</a></li>
							</c:if>
							<c:forEach begin="1" end="${totalPages}" var="i">
								<li class="page-item ${currentPage == i ? 'active' : ''}"><a
									class="page-link" href="?page=${i}&keyword=${keyword}">${i}</a></li>
							</c:forEach>
							<c:if test="${currentPage < totalPages}">
								<li class="page-item"><a class="page-link"
									href="?page=${currentPage + 1}&keyword=${keyword}">Next »</a></li>
							</c:if>
						</ul>
					</nav>
				</div>
			</div>
			<!-- 수정 모달 -->
			<div id="updateModal" class="modal-overlay" style="display: none;">
				<div class="modal-content">
					<h2>직원 정보 수정</h2>
					<form id="updateForm" method="post"
						action="${pageContext.request.contextPath}/employeeUpdate.do">
						<input type="hidden" name="empId">
						<div class="form-group">
							<label>이름</label> <input type="text" name="empName"
								class="form-control" readonly>
						</div>
						<div class="form-group">
							<label>주민번호</label> <input type="text" name="empNo"
								class="form-control" readonly>
						</div>
						<div class="form-group">
							<label>부서</label> <select name="deptId" class="form-control">
								<option value="">-- 선택 --</option>
								<option value="100">본사</option>
								<option value="201">영업부</option>
								<option value="211">국내영업팀</option>
								<option value="212">해외영업팀</option>
								<option value="202">영업관리부</option>
								<option value="203">구매부</option>
								<option value="204">자재부</option>
								<option value="205">총무부</option>
								<option value="206">교육부</option>
								<option value="207">인사부</option>
							</select>
						</div>
						<div class="form-group">
							<label>직책</label> <select name="roleId" class="form-control">
								<option value="">-- 선택 --</option>
								<option value="A1">A1 (대표이사)</option>
								<option value="A2">A2 (부사장)</option>
								<option value="A3">A3 (팀장)</option>
							</select>
						</div>
						<div class="form-group">
							<label>직급</label> <select name="jobId" class="form-control">
								<option value="">-- 선택 --</option>
								<option value="JG1">JG1 (대리)</option>
								<option value="JG2">JG2 (과장)</option>
								<option value="JG5">JG3 (차장)</option>
								<option value="JG5">JG4 (부장)</option>
								<option value="JG5">JG5 (본부장)</option>
								<option value="JG6">JG6 (이사)</option>
								<option value="JG7">JG7 (대표이사)</option>
							</select>
						</div>
						<div class="form-group">
							<label>전화번호</label> <input type="text" name="phone"
								class="form-control">
						</div>
						<div class="form-group">
							<label>이메일</label> <input type="email" name="email"
								class="form-control">
						</div>
						<div class="form-group">
							<label>급여</label> <input type="text" name="salary"
								class="form-control">
						</div>
						<div class="form-group">
							<label>관리자 여부</label> <select name="adminYN" class="form-control">
								<option value="">-- 선택 --</option>
								<option value="Y">Y (관리자)</option>
								<option value="N">N (일반)</option>
							</select>
						</div>
						<div class="form-group">
							<label>결혼 여부</label> <select name="marriageYN"
								class="form-control">
								<option value="">-- 선택 --</option>
								<option value="Y">Y (기혼)</option>
								<option value="N">N (미혼)</option>
							</select>
						</div>
						<div class="btn-group">
							<button type="submit" class="btn btn-primary">수정</button>
							<button type="button" class="btn btn-secondary"
								onclick="closeUpdateModal()">취소</button>
						</div>
					</form>
				</div>
			</div>

			<!-- 등록 모달 -->
			<div id="registerModal" class="modal-overlay" style="display: none;">
				<div class="modal-content">
					<h2>직원 등록</h2>
					<form id="registerForm"
						action="${pageContext.request.contextPath}/employeeInsert.do"
						method="post">

						<div class="form-group">
							<label>사원번호</label> <input type="text" class="form-control"
								name="empId" required>
						</div>

						<div class="form-group">
							<label>이름</label> <input type="text" class="form-control"
								name="empName" required>
						</div>

						<div class="form-group">
							<label>주민번호</label> <input type="text" class="form-control"
								name="empNo" placeholder="YYMMDD-******" required>
						</div>

						<div class="form-group">
							<label>부서</label> <select class="form-control" name="deptId"
								required>
								<option value="">-- 선택 --</option>
								<option value="SA">SA (영업부)</option>
								<option value="SAR">SAR (영업관리부)</option>
								<option value="SB">SB (구매부)</option>
								<option value="IN">IN (자재부)</option>
								<option value="RET">RET (총무부)</option>
								<option value="REE">REE (교육부)</option>
								<option value="REP">REP (인사부)</option>
							</select>
						</div>

						<div class="form-group">
							<label>직책</label> <select class="form-control" name="roleId"
								required>
								<option value="">-- 선택 --</option>
								<option value="A2">A2 (부사장)</option>
								<option value="A3">A3 (팀장)</option>
							</select>
						</div>

						<div class="form-group">
							<label>직급</label> <select class="form-control" name="jobId"
								required>
								<option value="">-- 선택 --</option>
								<option value="JG1">JG1 (대리)</option>
								<option value="JG2">JG2 (과장)</option>
								<option value="JG5">JG3 (차장)</option>
								<option value="JG5">JG4 (부장)</option>
								<option value="JG5">JG5 (본부장)</option>
								<option value="JG6">JG6 (이사)</option>
								<option value="JG7">JG7 (대표이사)</option>
							</select>
						</div>

						<div class="form-group">
							<label>관리자여부</label> <select class="form-control" name="adminYN"
								required>
								<option value="">-- 선택 --</option>
								<option value="Y">Y (관리자)</option>
								<option value="N">N (일반)</option>
							</select>
						</div>

						<div class="form-group">
							<label>이메일</label> <input type="email" class="form-control"
								name="email" required>
						</div>

						<div class="form-group">
							<label>전화번호</label> <input type="text" class="form-control"
								name="phone" required>
						</div>

						<div class="form-group">
							<label>입사일</label> <input type="date" class="form-control"
								name="hireDay" required>
						</div>


						<div class="form-group">
							<label>급여</label> <input type="text" class="form-control"
								name="salary" required>
						</div>

						<div class="form-group">
							<label>결혼여부</label> <select class="form-control"
								name="marriageYN" required>
								<option value="">-- 선택 --</option>
								<option value="Y">Y (기혼)</option>
								<option value="N">N (미혼)</option>
							</select>
						</div>

						<div class="btn-group">
							<button type="submit" class="btn btn-primary">등록</button>
							<button type="button" class="btn btn-secondary"
								onclick="closeRegisterModal()">취소</button>
						</div>
					</form>
				</div>
			</div>


			<script>
function openUpdateModal(empId) {
  fetch("${pageContext.request.contextPath}/employeeDetail.do?empId=" + empId)
	    .then(res => res.json())
	    .then(data => {
	     document.getElementById("updateModal").style.display = "flex";
	     document.querySelector("#updateForm input[name='empId']").value = data.empId;
	     document.querySelector("#updateForm input[name='empName']").value = data.empName;
	     document.querySelector("#updateForm input[name='empNo']").value = data.empNo;
	     document.querySelector("#updateForm select[name='deptId']").value = data.deptId || "";
	     document.querySelector("#updateForm select[name='roleId']").value = data.roleId || "";
	     document.querySelector("#updateForm select[name='jobId']").value = data.jobId || "";
	     document.querySelector("#updateForm input[name='phone']").value = data.phone || "";
	     document.querySelector("#updateForm input[name='email']").value = data.email || "";
	     document.querySelector("#updateForm input[name='salary']").value = data.salary || "";
	     document.querySelector("#updateForm select[name='adminYN']").value = data.adminYN || "";
	     document.querySelector("#updateForm select[name='marriageYN']").value = data.marriageYN || "";
	    })
	    .catch((err) => {
    		console.error("직원 정보 로딩 실패", err);
   			alert("직원 정보 로딩 실패");
	});
}
	function closeUpdateModal() {
	  document.getElementById("updateModal").style.display = "none";
	}
  function openRegisterModal() {
    document.getElementById("registerModal").style.display = "flex";
  }
  function closeRegisterModal() {
    document.getElementById("registerModal").style.display = "none";
  }
  function retireEmployee(empId) {
	  if (confirm("정말로 해당 직원을 퇴사 처리하시겠습니까?")) {
	    location.href = "${pageContext.request.contextPath}/retireEmployee.do?empId=" + empId;
	  }
	}
  
  function deleteEmployee(empId) {
	  if (confirm("정말로 퇴사 처리하시겠습니까?")) {
	    fetch("${pageContext.request.contextPath}/employeeDelete.do", {
	      method: "POST",
	      headers: {
	        "Content-Type": "application/x-www-form-urlencoded"
	      },
	      body: "empId=" + empId
	    })
	    .then(res => res.text())
	    .then(result => {
	      if (result === "success") {
	        alert("퇴사 처리 완료되었습니다.");
	        location.reload();
	      } else {
	        alert("퇴사 처리 실패했습니다.");
	      }
	    });
	  }
	}

</script>
</body>
</html>