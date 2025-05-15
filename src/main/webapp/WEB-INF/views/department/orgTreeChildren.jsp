<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="currentParentId" value="${param.parentId}" />
<ul>
  <c:forEach var="child" items="${deptList}">
    <c:if test="${child.parentId == currentParentId}">
      <li>
        <div class="node" data-dept="${child.deptId}" id="${child.deptId}" draggable="true"
             onclick="showDeptDetail('${child.deptId}', '${child.deptName}', '${child.phone}'); toggleEmployees('${child.deptId}')"
             ondragstart="drag(event)"
             ondragover="allowDrop(event)"
             ondrop="drop(event, '${child.deptId}')">
          ${child.deptId} - ${child.deptName}
          <c:choose>
            <c:when test="${not empty employeeMap[child.deptId]}">
              (${fn:length(employeeMap[child.deptId])})
            </c:when>
            <c:otherwise>(0)</c:otherwise>
          </c:choose>

          <div id="emp-${child.deptId}" class="employee-list">
            <c:forEach var="emp" items="${employeeMap[child.deptId]}">
              <div>${emp.empName}</div>
            </c:forEach>
          </div>
        </div>

        <jsp:include page="orgTreeChildren.jsp">
          <jsp:param name="parentId" value="${child.deptId}" />
        </jsp:include>
      </li>
    </c:if>
  </c:forEach>
</ul>
