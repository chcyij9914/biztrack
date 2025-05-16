<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String trainingId = request.getParameter("id");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>수강신청</title>
<link
	href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css"
	rel="stylesheet">

<style>
.card-body {
	padding: 2rem;
}

.info-table {
	width: 100%;
	border-collapse: collapse;
	background-color: white;
}

.info-table th, .info-table td {
	border: 1px solid #dee2e6;
	padding: 12px 16px;
	text-align: left;
	font-size: 15px;
}

.info-table th {
	background-color: #f8f9fc;
	width: 160px;
	white-space: nowrap;
}

.btn-group {
	margin-top: 20px;
	display: flex;
	justify-content: start;
	gap: 10px;
}
</style>
</head>
<body id="page-top">

	<div id="wrapper">
		<!-- 왼쪽 사이드바 -->
		<c:import url="/WEB-INF/views/common/menubar.jsp" />

		<!-- 콘텐츠 영역 -->
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<!-- 상단 네비 -->
				<c:import url="/WEB-INF/views/common/topbar.jsp" />

				<!-- 본문 -->
				<div class="container-fluid">
					<h2 class="text-dark font-weight-bold mt-4">수강신청</h2>
					<p class="text-muted mb-4">신청할 교육 정보를 확인 후 진행하세요.</p>

					<div class="card shadow">
						<div class="card-header">신청 교육 정보</div>
						<div class="card-body">
							<table class="table table-bordered">
								<tr>
									<th>교육명</th>
									<td>${training.title}</td>
								</tr>
								<tr>
									<th>강사명</th>
									<td>${training.instructorName}</td>
								</tr>
								<tr>
									<th>시작일</th>
									<td>${training.startDate}</td>
								</tr>
								<tr>
									<th>종료일</th>
									<td>${training.endDate}</td>
								</tr>
								<tr>
									<th>장소</th>
									<td>${training.location}</td>
								</tr>
								<tr>
									<th>정원</th>
									<td>${training.capacity}</td>
								</tr>
								<tr>
									<th>교육내용</th>
									<td>${training.courseContent}</td>
								</tr>
								<tr>
									<th>교육 세부내용 (목표)</th>
									<td>${training.detailContent}</td>
								</tr>
							</table>

							<div class="d-flex justify-content-between mt-4">
								<button type="button" class="btn btn-success"
									data-toggle="modal" data-target="#registrationModal">
									<i class="fas fa-check"></i> 수강신청 입력
								</button>
								<a href="javascript:history.back();" class="btn btn-secondary">
									<i class="fas fa-arrow-left"></i> 이전 페이지
								</a>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<!-- 수강신청 입력 모달 -->
	<div class="modal fade" id="registrationModal" tabindex="-1"
		role="dialog" aria-labelledby="registrationModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<!-- ✅ form 하나만 사용 -->
				<form id="registrationForm"
					action="${pageContext.request.contextPath}/training/register.do"
					method="post">
					<!-- STEP 1: 입력 -->
					<div id="step1">
						<div class="modal-header bg-primary text-white">
							<h5 class="modal-title" id="registrationModalLabel">STEP 1
								신청서 작성</h5>
							<button type="button" class="close text-white"
								data-dismiss="modal">
								<span>&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<input type="hidden" name="trainingId"
								value="${training.trainingId}" />
							<div class="form-group">
								<label>신청자 이름</label> <input type="text" class="form-control"
									name="name" required />
							</div>
							<div class="form-group">
								<label>생년월일</label> <input type="date" class="form-control"
									name="birth" required />
							</div>
							<div class="form-group">
								<label>연락처</label> <input type="text" class="form-control"
									name="phone" required />
							</div>
							<div class="form-group">
								<label>이메일</label> <input type="email" class="form-control"
									name="email" required />
							</div>
							<div class="form-group">
								<label>소속</label> <input type="text" class="form-control"
									name="org" required />
							</div>
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-success">
								<i class="fas fa-check"></i> 신청 완료
							</button>
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">닫기</button>
						</div>
					</div>

					<!-- STEP 2: 신청 완료 메시지 -->
					<div id="step2" class="d-none">
						<div class="modal-header bg-info text-white">
							<h5 class="modal-title">STEP 2 신청완료</h5>
							<button type="button" class="close text-white"
								data-dismiss="modal">
								<span>&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<div class="alert alert-info text-center">
								<i class="fas fa-info-circle"></i> 신청해주셔서 감사합니다.
							</div>
							<table class="table table-bordered">
								<tr>
									<th>이름</th>
									<td id="confirmName"></td>
								</tr>
								<tr>
									<th>생년월일</th>
									<td id="confirmBirth"></td>
								</tr>
								<tr>
									<th>연락처</th>
									<td id="confirmPhone"></td>
								</tr>
								<tr>
									<th>이메일</th>
									<td id="confirmEmail"></td>
								</tr>
								<tr>
									<th>소속</th>
									<td id="confirmOrg"></td>
								</tr>
							</table>
						</div>

						<a
							href="${pageContext.request.contextPath}/training/applicant.do?trainingId=${training.trainingId}"
							class="btn btn-info btn-icon-split"> <span
							class="icon text-white-50"> <i class="fas fa-eye"></i>
						</span> <span class="text">수강신청 확인</span>
						</a>
					</div>
			</div>
			</form>
		</div>
	</div>
	</div>

	<!-- 기타 HTML 내용들 -->

	<!-- 모달, 폼 등 모두 작성 후 -->
	<!-- <script>
document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('registrationForm');
    const step1 = document.getElementById('step1');
    const step2 = document.getElementById('step2');


    form.addEventListener('submit', function (e) {
        e.preventDefault(); // 폼 기본 제출 막기

        const formData = new FormData(form);
        const data = {};
        formData.forEach((value, key) => { data[key] = value; });

        fetch('${pageContext.request.contextPath}/training/register.do', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        })
       .then(res => {
            if (!res.ok) throw new Error("전송 실패");
            return res.json();
        })
        .then(result => {
            if (result.status === 'success') {
                step1.classList.add('d-none');
                step2.classList.remove('d-none');

                document.getElementById('confirmName').innerText = data.name;
                document.getElementById('confirmBirth').innerText = data.birth;
                document.getElementById('confirmPhone').innerText = data.phone;
                document.getElementById('confirmEmail').innerText = data.email;
                document.getElementById('confirmOrg').innerText = data.org;
            } else {
                alert("신청 실패: " + result.message);
            }
        })
        .catch(err => {
            alert("오류 발생: " + err.message);
        });
    });
});
</script> -->

<script>
document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('registrationForm');
    const step1 = document.getElementById('step1');
    const step2 = document.getElementById('step2');

    form.addEventListener('submit', function (e) {
        e.preventDefault(); // 폼 기본 제출 막기

        const formData = new FormData(form);
        const data = {};

        formData.forEach((value, key) => {
            data[key] = value;
        });

        // 필수 파라미터 추가
        data.registrationAt = new Date().toISOString(); // 수강신청일
        data.isCancelled = "N"; // 기본값

        fetch("${pageContext.request.contextPath}/training/register.do", {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        })
        .then(res => {
            if (!res.ok) throw new Error("전송 실패");
            return res.json();
        })
        .then(result => {
            if (result.status === 'success') {
                // STEP1 → STEP2 전환
                step1.classList.add('d-none');
                step2.classList.remove('d-none');

                // 신청 확인 정보 채우기
                document.getElementById('confirmName').innerText = data.name;
                document.getElementById('confirmBirth').innerText = data.birth;
                document.getElementById('confirmPhone').innerText = data.phone;
                document.getElementById('confirmEmail').innerText = data.email;
                document.getElementById('confirmOrg').innerText = data.org;
            } else {
                alert("신청 실패: " + result.message);
            }
        })
        .catch(err => {
            alert("오류 발생: " + err.message);
        });
    });
});
</script>


</body>
</html>


</body>
</html>
