<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${empty sessionScope.loginInfo}">
    <c:redirect url="/login.do" />
</c:if>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>거래처 상세정보</title>

<!-- SB Admin 2 CSS / Bootstrap -->
<link
	href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css"
	rel="stylesheet">
</head>
<body id="page-top">
	<div id="wrapper">

		<!-- Sidebar -->
		<c:import url="/WEB-INF/views/common/menubar.jsp" />

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">

				<!-- Topbar -->
				<c:import url="/WEB-INF/views/common/topbar.jsp" />

				<div class="container-fluid">
					<h1 class="h3 mb-2 text-gray-800">거래처 상세정보</h1>
					<p class="mb-4 small text-muted">
						등록된 <strong>${client.clientName}</strong> 거래처의 상세 내용을 확인합니다.
					</p>

					<!-- 탭 버튼 + 버튼 오른쪽 배치 -->
					<div class="d-flex justify-content-between align-items-center mb-3">
						<!-- 탭 버튼 -->
						<ul class="nav nav-tabs" id="clientTab">
							<li class="nav-item"><a class="nav-link active"
								id="info-tab" data-toggle="tab" href="#info">상세보기</a></li>
							<li class="nav-item"><a class="nav-link" id="contract-tab"
								data-toggle="tab" href="#contract">계약서 보기</a></li>
						</ul>

						<!-- 버튼 영역 (오른쪽) -->
						<div>
							<c:if test="${loginInfo.roleId == 'A2' 
							          || loginInfo.roleId == 'A3' 
							          || loginInfo.empId == client.currentManagerId}">
							  <a href="${pageContext.request.contextPath}/client/delete.do?clientId=${client.clientId}" 
							     class="btn btn-danger btn-sm mr-1"
							     onclick="return confirm('정말 삭제하시겠습니까?');">
							     <i class="fas fa-trash-alt"></i> 삭제
							  </a>
							</c:if>
						    <c:if test="${loginInfo.roleId == 'A2' 
							          || loginInfo.roleId == 'A3' 
							          || loginInfo.empId == client.currentManagerId}">
							  <a href="${pageContext.request.contextPath}/client/cupdate.do?clientId=${client.clientId}" 
							     class="btn btn-primary btn-sm mr-1">
							     <i class="fas fa-edit"></i> 수정하기
							  </a>
							</c:if>
						    <button type="button" class="btn btn-secondary btn-sm mr-1" onclick="history.back();">
						        <i class="fas fa-arrow-left"></i> 이전페이지
						    </button>
						    <a href="${pageContext.request.contextPath}/client/clist.do" class="btn btn-info btn-sm">
						        <i class="fas fa-list"></i> 목록으로
						    </a>
						</div>
					</div>

					<!-- 탭 콘텐츠 -->
					<div class="tab-content">

						<!-- 상세보기 탭 -->
						<div class="tab-pane fade show active" id="info">
							<div class="card shadow mb-4">
								<div class="card-body">

									<table class="table table-bordered">
										<tbody>
											<tr>
												<th>거래처명</th>
												<td>${client.clientName}</td>
												<th>대표자명</th>
												<td>${client.ceoName}</td>
											</tr>
											<tr>
												<th>사업자번호</th>
												<td>${client.businessNumber}</td>
												<th>카테고리명</th>
												<td>${client.categoryName}</td>
											</tr>
											<tr>
												<th>회사 전화번호</th>
												<td>${client.companyPhone}</td>
												<th>팩스번호</th>
												<td>${client.fax}</td>
											</tr>
											<tr>
												<th>주소</th>
												<td colspan="3">${client.address}</td>
											</tr>
											<tr>
												<th>홈페이지</th>
												<td colspan="3"><a href="${client.url}" target="_blank">${client.url}</a></td>
											</tr>
											<tr>
												<th>계약 시작일</th>
												<td>${client.contractStartDate}</td>
												<th>계약 종료일</th>
												<td>${client.contractEndDate}</td>
											</tr>
											<tr>
												<th>최초 담당자</th>
												<td colspan="3">(${client.firstManagerId})
													${client.firstManagerName} / ${client.firstManagerJob}</td>
											</tr>
											<tr>
												<th>현재 담당자</th>
												<td colspan="3">(${client.currentManagerId})
													${client.currentManagerName} / ${client.currentManagerJob}</td>
											</tr>
										</tbody>
									</table>

									<!-- Kakao 지도 -->
									<div class="mt-4">
										<h5>회사 위치</h5>
										<div id="map"
											style="width: 100%; height: 400px; background: #e9ecef;"></div>
									</div>

									<!-- 담당자 정보 -->
									<h5 class="mt-4">담당자 정보</h5>
									<table class="table table-bordered">
										<tbody>
											<tr>
												<th>담당자명</th>
												<td>${client.directorName}</td>
												<th>전화번호</th>
												<td>${client.directorPhone}</td>
											</tr>
											<tr>
												<th>이메일</th>
												<td colspan="3">${client.email}</td>
											</tr>
										</tbody>
									</table>

									<c:if test="${not empty businessCardFilePath}">
										<h6 class="mt-3">명함 이미지</h6>
										<img
											src="${pageContext.request.contextPath}${businessCardFilePath}"
											alt="명함 이미지" style="max-width: 300px;">
									</c:if>

								</div>
							</div>
						</div>

						<!-- 계약서보기 탭 -->
						<div class="tab-pane fade" id="contract">
							<div class="card shadow mb-4">
								<div class="card-body">
									<c:if test="${not empty contractList}">
									  <table class="table table-bordered text-center">
									    <thead class="thead-light">
									      <tr>
									        <th>문서번호</th>
									        <th>제목</th>
									        <th>계약일자</th>
									        <th>총금액</th>
									        <th>결제수단</th>
									        <th>관리</th>
									      </tr>
									    </thead>
									    <tbody>
									      <c:forEach var="doc" items="${contractList}">
									        <tr>
									          <td>${doc.documentId}</td>
									          <td class="text-left">${doc.title}</td>
									          <td><fmt:formatDate value="${doc.documentDate}" pattern="yyyy-MM-dd" /></td>
									          <td><fmt:formatNumber value="${doc.totalAmount}" type="currency" currencySymbol="₩" /></td>
									          <td>${doc.paymentMethod}</td>
									          <td>
									            <a href="${pageContext.request.contextPath}/client/documentDetailView.do?documentId=${doc.documentId}"
									               class="btn btn-outline-primary btn-sm">보러가기</a>
									          </td>
									        </tr>
									      </c:forEach>
									    </tbody>
									  </table>
									</c:if>
									<c:if test="${empty contractList}">
									  <p class="text-muted">계약 내역이 없습니다.</p>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Scroll to Top -->
	<a class="scroll-to-top rounded" href="#page-top"><i
		class="fas fa-angle-up"></i></a>

	<!-- JS -->
	<script
		src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>

	<!-- Kakao Map -->
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8795d3da9dc709f51ab344182f2fa218&libraries=services"></script>

	<script>
  window.onload = function() {
    var mapContainer = document.getElementById('map');
    var mapOption = {
      center: new kakao.maps.LatLng(37.5665, 126.9780),
      level: 3
    };

    var map = new kakao.maps.Map(mapContainer, mapOption);

    var geocoder = new kakao.maps.services.Geocoder();

    var address = "${client.address}";

    geocoder.addressSearch(address, function(result, status) {
      if (status === kakao.maps.services.Status.OK) {
        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        var marker = new kakao.maps.Marker({
          map: map,
          position: coords
        });

        map.setCenter(coords);
      } else {
        alert("주소를 찾을 수 없습니다: " + address);
      }
    });
  }
</script>




</body>
</html>
