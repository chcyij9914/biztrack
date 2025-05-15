<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>조직도 관리</title>

  <!-- SB Admin 2 CSS -->
  <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
  <style>
    .tree ul {
      padding-top: 20px;
      position: relative;
      display: flex;
      justify-content: center;
      gap: 30px;
    }
    .tree li {
      list-style-type: none;
      text-align: center;
      position: relative;
      padding: 20px 5px 0 5px;
    }
    .tree li::before, .tree li::after {
      content: '';
      position: absolute;
      top: 0;
      border-top: 2px solid #ccc;
      width: 50%;
      height: 20px;
    }
    .tree li::before { right: 50%; border-right: 2px solid #ccc; }
    .tree li::after { left: 50%; border-left: 2px solid #ccc; }
    .tree li:only-child::before, .tree li:only-child::after { display: none; }

    .node {
      display: inline-block;
      background-color: #4e73df;
      color: white;
      padding: 8px 16px;
      border-radius: 10%;
      font-size: 13px;
      min-width: 150px;
      box-shadow: 0 4px 8px rgba(0,0,0,0.1);
      cursor: pointer;
    }

    .employee-list {
      display: none;
      background: #f8f9fc;
      color: #333;
      border: 1px solid #ccc;
      padding: 5px;
      margin-top: 5px;
      font-size: 12px;
    }

    #dept-detail input {
      margin-right: 10px;
    }

    .card {
      margin-top: 20px;
    }
  </style>
</head>
<body id="page-top">

  <!-- Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
    <c:import url="/WEB-INF/views/common/menubar.jsp" />

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <c:import url="/WEB-INF/views/common/topbar.jsp" />

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <h1 class="h3 mb-4 text-gray-800 text-center">조직도</h1>

          <div class="text-center mb-3">
			  <button class="btn btn-primary" id="toggleOrgBtn">조직관리</button>
			</div>

          <!-- 조직관리 영역 -->
          <div id="admin-controls" style="display:none;">
            <div class="card shadow mb-4">
              <div class="card-header py-3"><strong>부서 추가</strong></div>
              <div class="card-body">
                <form action="${pageContext.servletContext.contextPath}/department/add.do" method="post" class="form-inline justify-content-center">
                  <input type="text" name="deptId" placeholder="부서코드" class="form-control mx-1 mb-2" required />
                  <input type="text" name="deptName" placeholder="부서명" class="form-control mx-1 mb-2" required />
                  <input type="tel" name="phone" placeholder="전화번호" class="form-control mx-1 mb-2" />
                  <input type="text" name="parentId" placeholder="상위부서코드" class="form-control mx-1 mb-2" />
                  <button type="submit" class="btn btn-success mb-2">부서 추가</button>
                </form>
              </div>
            </div>

            <!-- 선택된 부서 상세 -->
            <div class="card shadow mb-4" id="dept-detail" style="display:none;">
              <div class="card-header py-3"><strong>부서 상세정보</strong></div>
              <div class="card-body text-center">
                <form action="${pageContext.servletContext.contextPath}/department/update.do" method="post">
                  <input type="text" name="deptId" id="editDeptId" readonly class="form-control d-inline w-auto" style="background:#e9e9e9;" />
                  <input type="text" name="deptName" id="editDeptName" class="form-control d-inline w-auto" />
                  <input type="text" name="phone" id="editPhone" class="form-control d-inline w-auto" />
                  <button type="submit" class="btn btn-warning">수정</button>
                </form>
                <form action="${pageContext.servletContext.contextPath}/department/delete.do" method="post" class="mt-2">
                  <input type="hidden" name="deptId" id="deleteDeptId" />
                  <button type="submit" class="btn btn-danger" onclick="return confirm('삭제하시겠습니까?')">삭제</button>
                </form>
              </div>
            </div>
          </div>

          <!-- 조직도 트리 -->
          <div class="card shadow mb-4">
            <div class="card-body">
              <div class="tree">
                <ul>
                  <c:forEach var="dept" items="${deptList}">
                    <c:if test="${empty dept.parentId}">
                      <li>
                        <div class="node" data-dept="${dept.deptId}" id="${dept.deptId}" draggable="true"
                             onclick="showDeptDetail('${dept.deptId}', '${dept.deptName}', '${dept.phone}'); toggleEmployees('${dept.deptId}')"
                             ondragstart="drag(event)"
                             ondragover="allowDrop(event)"
                             ondrop="drop(event, '${dept.deptId}')">
                          ${dept.deptId} - ${dept.deptName}
                          <c:choose>
                            <c:when test="${not empty employeeMap[dept.deptId]}">
                              (${fn:length(employeeMap[dept.deptId])})
                            </c:when>
                            <c:otherwise>(0)</c:otherwise>
                          </c:choose>
                          <div id="emp-${dept.deptId}" class="employee-list">
                            <c:forEach var="emp" items="${employeeMap[dept.deptId]}">
                              <div>${emp.empName}</div>
                            </c:forEach>
                          </div>
                        </div>
                        <jsp:include page="orgTreeChildren.jsp">
                          <jsp:param name="parentId" value="${dept.deptId}" />
                        </jsp:include>
                      </li>
                    </c:if>
                  </c:forEach>
                </ul>
              </div>
            </div>
          </div>

        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

      <%-- Footer는 필요 시 추가 가능 --%>

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- SB Admin 2 Scripts -->
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>

 <script>
  // 직원 토글
  function toggleEmployees(id) {
    const e = document.getElementById('emp-' + id);
    e.style.display = e.style.display === 'block' ? 'none' : 'block';
  }

  // 상세정보 보여주기
  function showDeptDetail(deptId, deptName, phone) {
    document.getElementById("editDeptId").value = deptId;
    document.getElementById("editDeptName").value = deptName;
    document.getElementById("editPhone").value = phone;
    document.getElementById("deleteDeptId").value = deptId;
  }

  // drag & drop
  function drag(ev) {
    ev.dataTransfer.setData("deptId", ev.target.id);
  }

  function allowDrop(ev) {
    ev.preventDefault();
  }

  function drop(ev, targetId) {
    ev.preventDefault();
    const draggedId = ev.dataTransfer.getData("deptId");
    fetch('${pageContext.servletContext.contextPath}/department/move.do', {
      method: 'POST',
      headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
      body: `deptId=${draggedId}&parentId=${targetId}`
    }).then(() => location.reload());
  }

  // 조직관리 버튼 누르면 추가/상세 모두 토글
  document.addEventListener("DOMContentLoaded", function () {
    const orgBtn = document.getElementById("toggleOrgBtn");
    const adminBox = document.getElementById("admin-controls");

    orgBtn.addEventListener("click", function () {
      const isVisible = adminBox.style.display === "block";
      adminBox.style.display = isVisible ? "none" : "block";
      
      // 상세정보도 같이 열고 닫음 (단, 열릴 때는 빈 값으로 초기화)
      const detailBox = document.getElementById("dept-detail");
      if (detailBox) {
        detailBox.style.display = isVisible ? "none" : "block";
      }

      if (!isVisible) {
        // 열릴 때 입력값 초기화
        document.getElementById("editDeptId").value = "";
        document.getElementById("editDeptName").value = "";
        document.getElementById("editPhone").value = "";
        document.getElementById("deleteDeptId").value = "";
      }
    });
  });
</script>


</body>
</html>
