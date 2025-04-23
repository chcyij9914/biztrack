<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Topbar -->
<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

  <!-- Sidebar Toggle (Topbar for mobile) -->
  <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
    <i class="fa fa-bars"></i>
  </button>

  <!-- Topbar Search -->
  <form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search"
        action="${pageContext.request.contextPath}/search" method="get">
    <div class="input-group">
      <input type="text" name="keyword" class="form-control bg-light border-0 small"
             placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
      <div class="input-group-append">
        <button class="btn btn-primary" type="submit">
          <i class="fas fa-search fa-sm"></i>
        </button>
      </div>
    </div>
  </form>

  <!-- Topbar Navbar -->
  <ul class="navbar-nav ml-auto">

    <!-- Alerts -->
    <li class="nav-item dropdown no-arrow mx-1">
      <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
         data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <i class="fas fa-bell fa-fw"></i>
        <span class="badge badge-danger badge-counter">3+</span>
      </a>
      <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
           aria-labelledby="alertsDropdown">
        <h6 class="dropdown-header">알림 센터</h6>
        <a class="dropdown-item d-flex align-items-center" href="#">
          <div class="mr-3">
            <div class="icon-circle bg-primary">
              <i class="fas fa-file-alt text-white"></i>
            </div>
          </div>
          <div>
            <div class="small text-gray-500">2025-04-23</div>
            <span class="font-weight-bold">새 공지사항이 등록되었습니다</span>
          </div>
        </a>
      </div>
    </li>

    <!-- Messages -->
    <li class="nav-item dropdown no-arrow mx-1">
      <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button"
         data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <i class="fas fa-envelope fa-fw"></i>
        <span class="badge badge-danger badge-counter">7</span>
      </a>
      <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
           aria-labelledby="messagesDropdown">
        <h6 class="dropdown-header">메시지 센터</h6>
        <a class="dropdown-item d-flex align-items-center" href="#">
          <div class="dropdown-list-image mr-3">
            <img class="rounded-circle" src="${pageContext.request.contextPath}/resources/img/undraw_profile_1.svg" alt="...">
            <div class="status-indicator bg-success"></div>
          </div>
          <div class="font-weight-bold">
            <div class="text-truncate">안녕하세요! 문의 사항이 있습니다.</div>
            <div class="small text-gray-500">Emily · 58분 전</div>
          </div>
        </a>
      </div>
    </li>

    <!-- Divider -->
    <div class="topbar-divider d-none d-sm-block"></div>

    <!-- User Info -->
    <li class="nav-item dropdown no-arrow">
      <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
         data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        <span class="mr-2 d-none d-lg-inline text-gray-600 small">
          <c:out value="${sessionScope.username != null ? sessionScope.username : 'Guest'}" />
        </span>
        <img class="img-profile rounded-circle"
             src="${pageContext.request.contextPath}/resources/img/undraw_profile.svg">
      </a>
      <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
           aria-labelledby="userDropdown">
        <a class="dropdown-item" href="#"><i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> 프로필</a>
        <a class="dropdown-item" href="#"><i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i> 설정</a>
        <a class="dropdown-item" href="#"><i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i> 활동 기록</a>
        <div class="dropdown-divider"></div>
        <a class="dropdown-item" href="${pageContext.request.contextPath}/logout.jsp">
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

</body>
</html>