<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>거래처 관리</title>

<!-- Fonts & Styles -->
<link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,400,700,900" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">

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
</style>
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

         <!-- Begin Page Content -->
         <div class="container-fluid">

            <!-- Page Heading -->
            <div class="d-flex justify-content-between align-items-center mb-2">
               <div>
                  <h1 class="h3 text-gray-800 mb-1">거래처 관리</h1>
                  <p class="text-muted small mb-0">거래처 목록</p>
               </div>

               <div class="d-flex align-items-center">
				<a href="#" class="btn btn-primary px-3 py-2 mr-3"
				onclick="window.open('${pageContext.request.contextPath}/client/insertForm.do', 'insertClientWindow', 'width=800,height=900'); return false;">
				+ 거래처 등록
				</a>
                  <form class="form-inline" action="#" method="get">
                     <select class="form-control mr-2" name="filter">
                        <option>거래처명</option>
                        <option>대표자명</option>
                     </select>
                     <input type="text" class="form-control mr-2" name="keyword" placeholder="검색어 입력" />
                     <select class="form-control mr-2" name="status">
                        <option value="">전체 상태</option>
                        <option value="계약중">계약중</option>
                        <option value="만료">만료</option>
                     </select>
                     <button type="submit" class="btn btn-outline-primary">검색</button>
                  </form>
               </div>
            </div>

            <!-- Table -->
            <div class="card-body">
               <div class="table-responsive">
                  <table class="table table-bordered" style="table-layout: fixed; width: 100%;" cellspacing="0">
                     <colgroup>
                        <col style="width: 20%;">
                        <col style="width: 20%;">
                        <col style="width: 20%;">
                        <col style="width: 20%;">
                        <col style="width: 10%;">
                        <col style="width: 10%;">
                     </colgroup>
                     <thead class="text-center bg-light">
                        <tr>
                           <th>거래처명</th>
                           <th>대표자</th>
                           <th>사업자번호</th>
                           <th>연락처</th>
                           <th>상태</th>
                           <th>관리</th>
                        </tr>
                     </thead>
                     <tbody class="text-center bg-white">
                        <c:forEach var="client" items="${clientList}">
                           <tr>
                              <td>${client.clientName}</td>
                              <td>${client.ceoName}</td>
                              <td>${client.businessNumber}</td>
                              <td>${client.companyPhone}</td>
                              <td><span class="badge badge-secondary">${client.clientStatus}</span></td>
                              <td><a href="${pageContext.request.contextPath}/client/cdetail.do?clientId=${client.clientId}"
                              				class="btn btn-sm btn-outline-secondary">상세</a></td>
                           </tr>
                        </c:forEach>
                     </tbody>
                  </table>
               </div>
               
               
               
				<!-- 페이징 영역 -->
				<c:import url="/WEB-INF/views/common/pagingView.jsp" />
            </div>

         </div>
         <!-- End Page Content -->

         <c:import url="/WEB-INF/views/common/footer.jsp" />

      </div>
   </div>
</div>

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top"><i class="fas fa-angle-up"></i></a>

<!-- JS -->
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>
</body>
</html>
