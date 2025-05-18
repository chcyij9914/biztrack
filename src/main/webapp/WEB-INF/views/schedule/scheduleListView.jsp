<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>일정 관리</title>
  <style>
    .fc .fc-button {
      background-color: #f8f9fc;
      color: #4e73df;
      border: 1px solid #d1d3e2;
      font-size: 14px;
      font-weight: 600;
      border-radius: 5px;
      padding: 5px 10px;
    }
    .fc .fc-button:hover {
      background-color: #e2e6ea;
      color: #2e59d9;
    }
    .fc .fc-button-primary:not(:disabled).fc-button-active {
      background-color: #4e73df;
      color: white;
      border-color: #4e73df;
    }
    .fc-col-header-cell-cushion, .fc-daygrid-day-number {
      color: #858796;
      font-weight: 600;
    }
  </style>
</head>
<body id="page-top">
<div id="wrapper">
  <%@ include file="/WEB-INF/views/common/menubar.jsp"%>
  <div id="content-wrapper" class="d-flex flex-column">
    <div id="content">
    <c:import url="/WEB-INF/views/common/topbar.jsp" />
      <div class="container-fluid">

        <!-- 상단 타이틀 + 검색 + 등록 -->
        <div class="d-flex align-items-center justify-content-between flex-wrap mb-4">
          <div class="mb-2">
            <h1 class="h4 text-gray-800 mb-0">일정 관리</h1>
            <div class="text-muted small">일정 목록</div>
          </div>
          <div class="d-flex align-items-center">
            <form action="${pageContext.request.contextPath}/schedule/SearchSchedule.do" method="get" id="searchForm" class="d-flex align-items-center mr-3">
              <div class="form-inline">
                <select name="searchType" id="searchType" class="form-control mr-2">
                  <option value="title">제목</option>
                  <option value="type">유형</option>
                  <option value="date">날짜</option>
                </select>
                <div id="searchInputs">
                  <input type="text" name="keyword" class="form-control" placeholder="검색어 입력">
                </div>
                <button type="submit" class="btn btn-primary ml-2">검색</button>
              </div>
            </form>
            <form action="${pageContext.request.contextPath}/schedule/ListSchedule.do" method="get" class="mr-2">
              <button type="submit" class="btn btn-primary shadow-sm" style="font-size: 15px; font-weight: 600; padding: 10px 20px; border-radius: 10px;">목록</button>
            </form>
           <a href="#" class="btn btn-primary shadow-sm" style="font-size: 15px; font-weight: 600; padding: 10px 20px; border-radius: 10px;"
               onclick="window.open('${pageContext.request.contextPath}/schedule/AddForm.do', 'schedulePopup', 'width=1200,height=850,resizable=yes,scrollbars=yes'); return false;">
              일정 등록
            </a>
          </div>
        </div>

        <script>
          const searchType = document.getElementById("searchType");
          const searchInputs = document.getElementById("searchInputs");
          function updateInputs() {
            const selected = searchType.value;
            let html = '';
            if (selected === 'title') {
              html = '<input type="text" name="keyword" class="form-control" placeholder="제목을 입력하세요">';
            } else if (selected === 'type') {
              html = '<select name="keyword" class="form-control">'
                    + '<option value="PUBLIC">공적</option>'
                    + '<option value="PRIVATE">사적</option>'
                    + '</select>';
            } else if (selected === 'date') {
              html = '<input type="date" name="beginDate" class="form-control mr-1"> ~ '
                    + '<input type="date" name="endDate" class="form-control ml-1">';
            }
            searchInputs.innerHTML = html;
          }
          updateInputs();
          searchType.addEventListener("change", updateInputs);
        </script>

        <!-- 캘린더 + 목록 -->
        <div class="row">
          <div class="col-md-5 mb-4">
            <div class="card shadow">
              <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">캘린더</h6>
              </div>
              <div class="card-body">
                <div id="calendar"></div>
              </div>
            </div>
          </div>

          <div class="col-md-7 mb-4">
            <div class="card shadow">
              <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">일정 목록</h6>
              </div>
              <div class="card-body">
                <div class="table-responsive">
                  <table class="table table-bordered text-center" width="100%">
                    <thead class="thead-light">
                      <tr>
                        <th>일정 번호</th>
                        <th>제목</th>
                        <th>시작일</th>
                        <th>종료일</th>
                        <th>장소</th>
                        <th>유형</th>
                        <th>관리</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="schedule" items="${scheduleList}">
                        <c:if test="${schedule.scType eq 'PUBLIC' or schedule.empId eq loginEmpId}">
                          <tr>
                            <td>${schedule.scId}</td>
                            <td>${schedule.scTitle}</td>
                            <td class="${schedule.scType eq 'PUBLIC' ? 'text-danger' : 'text-primary'}">
                              <fmt:formatDate value="${schedule.startDatetime}" pattern="yyyy-MM-dd HH:mm"/>
                            </td>
                            <td class="${schedule.scType eq 'PUBLIC' ? 'text-danger' : 'text-primary'}">
                              <fmt:formatDate value="${schedule.endDatetime}" pattern="yyyy-MM-dd HH:mm"/>
                            </td>
                            <td>${schedule.place}</td>
                            <td><span class="badge ${schedule.scType eq 'PUBLIC' ? 'badge-danger' : 'badge-primary'}">${schedule.scType}</span></td>
                            <td>
                              <c:choose>
                                <c:when test="${schedule.scType eq 'PRIVATE' and schedule.empId eq loginEmpId}">
                                  <a href="${pageContext.request.contextPath}/schedule/UpdateForm.do?scId=${schedule.scId}" class="btn btn-sm btn-outline-info">수정</a>
                                  <a href="${pageContext.request.contextPath}/schedule/DeleteSchedule.do?scId=${schedule.scId}" class="btn btn-sm btn-outline-danger" onclick="return confirm('삭제하시겠습니까?');">삭제</a>
                                </c:when>
                                <c:when test="${schedule.scType eq 'PUBLIC' and adminYN eq 'Y'}">
                                  <a href="${pageContext.request.contextPath}/schedule/UpdateForm.do?scId=${schedule.scId}" class="btn btn-sm btn-outline-info">수정</a>
                                  <a href="${pageContext.request.contextPath}/schedule/DeleteSchedule.do?scId=${schedule.scId}" class="btn btn-sm btn-outline-danger" onclick="return confirm('삭제하시겠습니까?');">삭제</a>
                                </c:when>
                                <c:otherwise>
                                  <span class="text-muted">권한 없음</span>
                                </c:otherwise>
                              </c:choose>
                            </td>
                          </tr>
                        </c:if>
                      </c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- 일정 상세 메시지 모달 -->
        <div class="modal fade" id="eventDetailModal" tabindex="-1" role="dialog" aria-labelledby="eventModalLabel" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div id="modalHeader" class="modal-header bg-primary text-white">
                <h5 class="modal-title" id="modalTitle">일정 제목</h5>
                <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <div><strong>일정 번호 :</strong> <span id="modalScId"></span></div>
                <div><strong>장소 :</strong> <span id="modalPlace"></span></div>
                <div><strong>유형 :</strong> <span id="modalType" class="badge"></span></div>
                <div><strong>시작일 :</strong> <span id="modalStart"></span></div>
                <div><strong>종료일 :</strong> <span id="modalEnd"></span></div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
              </div>
            </div>
          </div>
        </div>

      </div>
    </div>
    <c:import url="/WEB-INF/views/common/footer.jsp" />
  </div>
</div>

<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/fullcalendar/core/index.global.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/fullcalendar/daygrid/index.global.min.js"></script>
<script>
document.addEventListener('DOMContentLoaded', function () {
  var calendarEl = document.getElementById('calendar');
  var calendar = new FullCalendar.Calendar(calendarEl, {
    initialView: 'dayGridMonth',
    locale: 'ko',
    events: '${pageContext.request.contextPath}/schedule/events.do',
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth'
    },
    buttonText: {
      today: '오늘',
      month: '월'
    },
    eventClick: function (info) {
      const event = info.event;
      const ext = event.extendedProps;
      const isPublic = ext.scType === 'PUBLIC';
      document.getElementById('modalTitle').innerText = event.title;
      document.getElementById('modalScId').innerText = ext.scId || '-';
      document.getElementById('modalPlace').innerText = ext.place || '-';
      document.getElementById('modalType').innerText = ext.scType || '-';
      document.getElementById('modalType').className = isPublic ? 'badge badge-danger' : 'badge badge-primary';
      document.getElementById('modalStart').innerText = ext.startDatetime || '-';
      document.getElementById('modalEnd').innerText = ext.endDatetime || '-';
      $('#eventDetailModal').modal('show');
      info.jsEvent.preventDefault();
    }
  });
  calendar.render();
});
</script>
</body>
</html>
