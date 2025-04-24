<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>biztrack</title>
</head>
<body id="page-top">
   <div id="wrapper">
      
      <%@ include file="/WEB-INF/views/common/menubar.jsp" %>

      <!-- Content Wrapper -->
      <div id="content-wrapper" class="d-flex flex-column">
         <div id="content">
            

            <!-- Begin Page Content -->
            <div class="container-fluid">

               <!-- 상단 타이틀 + 검색필터 + 등록 버튼 -->
               <div class="d-flex align-items-center justify-content-between flex-wrap mb-4">
                  <!-- 좌측 제목 -->
                  <div class="mb-2">
                     <h1 class="h4 text-gray-800 mb-0">거래처 관리</h1>
                     <div class="text-muted small">거래처 목록</div>
                  </div>

                  <!-- 우측 검색 필터 + 버튼 -->
                  <div class="d-flex flex-wrap align-items-center">
                     <select class="form-control form-control-sm mr-2" style="width: 120px;">
                        <option>거래처명</option>
                        <option>대표자명</option>
                     </select>

                     <input type="text" class="form-control form-control-sm mr-2" style="width: 180px;" placeholder="검색어 입력">

                     <select class="form-control form-control-sm mr-2" style="width: 120px;">
                        <option>전체 상태</option>
                        <option>계약중</option>
                        <option>만료</option>
                     </select>

                     <button type="button" class="btn btn-outline-primary btn-sm mr-2">검색</button>
                     <button type="button" class="btn btn-primary btn-sm">거래처 등록</button>
                  </div>
               </div>

               <!-- 목록 테이블 -->
               <div class="card shadow mb-4">
                  <div class="card-body">
                     <div class="table-responsive">
                        <table class="table table-bordered text-center" width="100%" cellspacing="0">
                           <thead class="thead-light">
                              <tr>
                                 <th>거래처명</th>
                                 <th>대표자</th>
                                 <th>사업자번호</th>
                                 <th>연락처</th>
                                 <th>상태</th>
                                 <th>관리</th>
                              </tr>
                           </thead>
                           <tbody>
							<c:forEach var="client" items="${clientList}">
								<tr>
									<td>${client.clientId}</td>
									<td>${client.clientName}</td>
									<td>${client.ceoName}</td>
									<td>${client.companyPhone}</td>
									<td>${client.clientStatus}</td>
								</tr>
							</c:forEach>
                           </tbody>
                        </table>
                     </div>
                  </div>
               </div>
            </div>
            <!-- /.container-fluid -->
            
         </div>
         <!-- End of Main Content -->
         
      </div>
      <!-- End of Content Wrapper -->

   </div>
   <!-- End of Page Wrapper -->

   <!-- Scroll to Top Button-->
   <a class="scroll-to-top rounded" href="#page-top"><i class="fas fa-angle-up"></i></a>

<!-- JS 파일 -->
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>

</body>
</html>