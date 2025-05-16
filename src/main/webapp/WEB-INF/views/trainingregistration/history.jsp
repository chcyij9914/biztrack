<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%-- <c:if test="${empty sessionScope.loginInfo}">
  <c:redirect url="/login.do" />
</c:if> --%>

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
<
style>.card-body {
	padding: 2rem;
}

.table {
	width: 100%;
	font-size: 16px; /* 글자 크기 ↑ */
}

.table th, .table td {
	padding: 22px 25px; /* 셀 내부 여백 증가 */
	text-align: center;
	vertical-align: middle;
	font-size: 16px; /* 셀 글자 크기 ↑ */
}

.table th {
	background-color: #f1f3f5;
	font-weight: bold;
}

.table-bordered {
	border: 1px solid #dee2e6;
}

.table-bordered th, .table-bordered td {
	border: 1px solid #dee2e6;
}

.card {
	min-height: 500px; /* 카드 자체 크기 키워서 빈공간 방지 */
}

.container-fluid {
	min-height: 700px; /* 전체 높이 확보 (반응형) */
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
					<h2 class="text-dark font-weight-bold mt-4">수강신청자 관리</h2>
					<p class="text-muted mb-4">※ 신청 인원, 정원 등을 기준으로 수강 상태를 확인하세요.</p>

					<div class="card shadow">
						<div class="card-header">수강신청자 관리</div>
						<div class="card-body">
							<table class="table table-bordered">
								<tr>
									<th>과목명</th>
									<th>신청 인원</th>
									<th>정원</th>
									<th>신청 종료일</th>
									<th>상태</th>
								</tr>
								</thead>
								<tbody>
									<c:forEach var="course" items="${courseList}">
										<tr>
											<td>${course.TITLE}</td>
											<td>${course.APPLIEDCOUNT}</td>
											<td>${course.CAPACITY}</td>
											<td><fmt:formatDate value="${course.END_DATE}"
													pattern="yyyy-MM-dd" /></td>
											<td><c:choose>
													<c:when test="${empty course.STATUS}"> 미정 </c:when>
													<c:otherwise>${course.STATUS}</c:otherwise>
												</c:choose></td>
										</tr>
										<td><c:choose>
												<c:when test="${course.status == 'D-1'}">
													<span class="badge badge-warning">D-1</span>
												</c:when>
												<c:when test="${course.status == '신청전'}">
													<span>신청전</span>
												</c:when>
												<c:when test="${course.status == '신청완료'}">
													<span class="badge badge-secondary">신청완료</span>
												</c:when>
												<c:otherwise>
													<span>${course.status}</span>
												</c:otherwise>
											</c:choose></td>
										</tr>
									</c:forEach>
								</tbody>

							</table>

							<!-- <div class="d-flex justify-content-between mt-4">
								<button type="button" class="btn btn-success"
									data-toggle="modal" data-target="#registrationModal">
									<i class="fas fa-check"></i> 수강신청 입력
								</button> -->
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
						<div class="modal-footer">
							<button type="button" class="btn btn-primary"
								onclick="location.href='${pageContext.request.contextPath}/trainingregistration/history.do'">확인</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- 기타 HTML 내용들 -->

	<!-- 모달, 폼 등 모두 작성 후 -->
	<script>
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
</script>

</body>
</html>