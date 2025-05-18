<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    // 로그인 세션 체크
    if (session.getAttribute("loginInfo") == null) {
        response.sendRedirect(request.getContextPath() + "/login.do");
        return; // 여기서 return 꼭 해줘야 아래 코드 실행 안 됨!!
    }
%>

<!-- Topbar -->
<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

  <!-- Sidebar Toggle (Topbar) -->
  <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
    <i class="fa fa-bars"></i>
  </button>

  <!-- Topbar Navbar -->
  <ul class="navbar-nav ml-auto">


    <!-- 사용자 정보 (이미지 제거됨) -->
    <li class="nav-item dropdown no-arrow">
      <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
         data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <span class="mr-2 d-none d-lg-inline text-gray-600 small">
          <c:out value="${loginInfo.empName}" />님
        </span>
        <!-- 프로필 이미지 제거됨 -->
      </a>

      <!-- 드롭다운 - 사용자 메뉴 -->
      <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
        <a class="dropdown-item" href="${pageContext.request.contextPath}/changePassword.do">
          <i class="fas fa-key fa-sm fa-fw mr-2 text-gray-400"></i> 비밀번호 변경
        </a>
        <a class="dropdown-item" href="${pageContext.request.contextPath}/logout.do">
          <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i> 로그아웃
        </a>
      </div>
    </li>

  </ul>
</nav>
<!-- End of Topbar -->


<!-- JS Scripts -->
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>
<script>
  $(document).ready(function () {
    $('#userDropdown').on('click', function (e) {
      e.preventDefault();
      $(this).next('.dropdown-menu').toggle();
    });
  });
</script>

</body>
</html>