<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">

<title>biztrack</title>

<!-- Google Fonts (Nunito) -->
<link href="https://fonts.googleapis.com/css?family=Nunito:200,300,400,700,900" rel="stylesheet">

<!-- Font Awesome -->
<link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">

<!-- SB Admin 2 CSS -->
<link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">

</head>
<body>
<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

   <!-- Sidebar - biztrack -->
   <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath}/main.do">
      <div class="sidebar-brand-icon rotate-n-15"></div>
      <div class="sidebar-brand-text mx-3">
         <img src="${pageContext.request.contextPath}/resources/img/logo-white.png" alt="BizTrack" width="140">
      </div>
   </a>
   
   	<!-- 관리자 전용 메뉴 -->
  <c:if test="${loginInfo.adminYN == 'Y'}">
    <!-- 관리자인 경우에만 보여줄 메뉴 -->
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/adminMain.do">
            <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
            직원관리
        </a>
    </li>
   </c:if>

   <!-- Nav Item - 공지사항 -->
   <li class="nav-item">
      <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseNotice"
         aria-expanded="true" aria-controls="collapseNotice">
         <i class="fas fa-fw fa-bell"></i>
         <span>공지사항</span>
      </a>
      <div id="collapseNotice" class="collapse" aria-labelledby="headingNotice" data-parent="#accordionSidebar">
         <div class="bg-white py-2 collapse-inner rounded">
            <a class="collapse-item" href="${ pageContext.servletContext.contextPath }/nlist.do?page=1">공지 목록 </a>
            <a class="collapse-item" href="${ pageContext.servletContext.contextPath }/moveWrite.do">공지 등록 </a>
            <!-- <a class="collapse-item" href="#">공지 수정 </a>
            <a class="collapse-item" href="#">공지 상세보기 </a> -->
         </div>
      </div>
   </li>

   <!-- Nav Item - 메일 -->
   <li class="nav-item">
	   <a class="nav-link" href="${pageContext.request.contextPath}/mail/form.do">
	      <i class="fas fa-fw fa-envelope-open-text"></i>
	      <span>메일</span>
	   </a>
	</li>

   <!-- Nav Item - 인사 -->
<li class="nav-item">
  <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseHR"
        aria-expanded="true" aria-controls="collapseHR">
    <i class="fas fa-fw fa-user"></i>
    <span>인사</span>
  </a>
  <div id="collapseHR" class="collapse" aria-labelledby="headingHR" data-parent="#accordionSidebar">
    <div class="bg-white py-2 collapse-inner rounded">
      
      <!-- ✅ 조직도 메뉴: 항상 보이게 -->
      <a class="collapse-item" href="${pageContext.request.contextPath}/organization/organizationList.do">조직도</a>

      <!-- ✅ 직원평가 메뉴: A1, A2, A3인 경우만 -->
      <c:if test="${loginInfo.roleId == 'A1' || loginInfo.roleId == 'A2' || loginInfo.roleId == 'A3'}">
        <a class="collapse-item" href="${pageContext.request.contextPath}/evaluation/evaluationList.do">직원평가</a>
      </c:if>

    </div>
  </div>
</li>

   <!-- Nav Item - 구매 -->
   <li class="nav-item">
      <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePurchase"
      		aria-expanded="true" aria-controls="collapsePurchase">
         <i class="fas fa-fw fa-file-invoice"></i>
         <span>구매</span>
      </a>
      <div id="collapsePurchase" class="collapse" aria-labelledby="headingPurchase" data-parent="#accordionSidebar">
         <div class="bg-white py-2 collapse-inner rounded">
            <a class="collapse-item" href="${pageContext.request.contextPath}/purchase/purchase-document.do">구매문서</a>
            <a class="collapse-item" href="${pageContext.request.contextPath}/inbound/inbound-document.do">입고</a>
         </div>
      </div>
   </li>

   <!-- Nav Item - 상품관리 -->
   <li class="nav-item">
      <a class="nav-link" href="${pageContext.request.contextPath}/product/product-list.do">
         <i class="fas fa-fw fa-box"></i>
         <span>상품관리</span>
      </a>
   </li>

   <!-- Nav Item - 영업 -->
   <li class="nav-item">
     <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseBusiness"
     		aria-expanded="true" aria-controls="collapseBusiness">
         <i class="fas fa-fw fa-chart-bar"></i>
         <span>영업</span>
      </a>
      <div id="collapseBusiness" class="collapse" aria-labelledby="headingBusiness" data-parent="#accordionSidebar">
         <div class="bg-white py-2 collapse-inner rounded">
            <a class="collapse-item" href="${pageContext.request.contextPath}/schedule/ListSchedule.do">일정</a>
            <a class="collapse-item" href="${pageContext.request.contextPath}/graph/dashBoard.do">그래프</a>
             <a class="collapse-item" href="${pageContext.request.contextPath}/reminder/ReminderList.do">리마인더</a>
              <a class="collapse-item" href="${pageContext.request.contextPath}/businessdocument/OutboundList.do">영업문서</a>
         </div>
      </div>
   </li>


   <!-- Nav Item - 거래처 -->
   <li class="nav-item">
      <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseClient"
      		aria-expanded="true" aria-controls="collapseClient">
         <i class="fas fa-fw fa-store"></i>
         <span>거래처</span>
      </a>
      <div id="collapseClient" class="collapse" aria-labelledby="headingClient" data-parent="#accordionSidebar">
         <div class="bg-white py-2 collapse-inner rounded">
            <a class="collapse-item" href="${pageContext.request.contextPath}/client/clist.do">거래처관리</a>
            <a class="collapse-item" href="${pageContext.request.contextPath}/client/documentList.do">계약/제안서관리</a>
         </div>
      </div>
   </li>

   <!-- Nav Item - 교육관리 -->
<li class="nav-item">
  <a class="nav-link collapsed" href="#"
     data-toggle="collapse" data-target="#collapseEdu"
     aria-expanded="false" aria-controls="collapseEdu">
    <i class="fas fa-fw fa-book-open"></i>
    <span>교육관리</span>
  </a>
  <div id="collapseEdu" class="collapse" aria-labelledby="headingEdu" data-parent="#accordionSidebar">
    <div class="bg-white py-2 collapse-inner rounded">
      <h6 class="collapse-header">교육관리 메뉴:</h6>
      <a class="collapse-item" href="${pageContext.request.contextPath}/list.do">교육 목록</a>
      <a class="collapse-item" href="${pageContext.request.contextPath}/training/register.do">교육 등록</a>
    <%--  <a class="collapse-item" href="${pageContext.request.contextPath}/training/detail.do?id=TR001"> 교육 상세보기</a> --%>
    <%--   <a class="collapse-item" href="${pageContext.request.contextPath}/training/analysis.do" class="btn btn-outline-info">평가 분석</a> --%>

    </div>
  </div>
</li>

<!-- Nav Item - 학습관리 -->
<li class="nav-item">
  <a class="nav-link collapsed" href="#"
     data-toggle="collapse" data-target="#collapseLearn"
     aria-expanded="false" aria-controls="collapseLearn">
    <i class="fas fa-fw fa-book-open"></i>
    <span>학습관리</span>
  </a>
  <div id="collapseLearn" class="collapse" aria-labelledby="headingLearn" data-parent="#accordionSidebar">
    <div class="bg-white py-2 collapse-inner rounded">
      <h6 class="collapse-header">학습관리 메뉴:</h6>
      <a class="collapse-item" href="${pageContext.request.contextPath}/trainingRecord/list.do"> 내 수강일정</a>
      <a class="collapse-item" href="${pageContext.request.contextPath}/trainingRecord/progress.do"> 내 학습진도</a>
      <a class="collapse-item" href="${pageContext.request.contextPath}/trainingRecord/assignment.do">과제 제출</a> 
      <%-- <a class="collapse-item" href="${pageContext.request.contextPath}/trainingRecord/evaluation.do">평가 작성</a> --%>
    </div>
  </div>
</li>

<!-- Nav Item - 수강신청 관리 -->
<li class="nav-item">
  <a class="nav-link collapsed" href="#"
     data-toggle="collapse" data-target="#collapseApply"
     aria-expanded="false" aria-controls="collapseApply">
    <i class="fas fa-fw fa-book-open"></i>
    <span>수강신청 관리</span>
  </a>
  <div id="collapseApply" class="collapse" aria-labelledby="headingApply" data-parent="#accordionSidebar">
    <div class="bg-white py-2 collapse-inner rounded">
      <h6 class="collapse-header">수강신청관리 메뉴:</h6>
    <%--    <a class="collapse-item" href="${pageContext.request.contextPath}/trainingregistration/"> 수강신청 </a> --%>
     <a class="collapse-item" href="${pageContext.request.contextPath}/training/applicant.do?trainingId=${training.trainingId}">수강내역 확인</a>
      <a class="collapse-item" href="${pageContext.request.contextPath}/trainingregistration/history.do">수강신청자 관리</a>
     <%--  <a class="collapse-item" href="${pageContext.request.contextPath}/trainingregistration/applicant.do">수강내역 확인</a> --%>
    </div>
  </div>
</li>

   <!-- Divider -->
   <hr class="sidebar-divider d-none d-md-block">

   <!-- Sidebar Toggler -->
   <div class="text-center d-none d-md-inline">
      <button class="rounded-circle border-0" id="sidebarToggle"></button>
   </div>

</ul>
<!-- End of Sidebar -->

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>

<!-- Bootstrap JS -->
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Easing Plugin -->
<script src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages -->
<script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>

</body>
</html>
