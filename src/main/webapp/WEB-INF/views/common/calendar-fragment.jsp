<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 캘린더 카드 -->
<div class="card shadow">
  <div class="card-header py-3">
    <h6 class="m-0 font-weight-bold text-primary">캘린더</h6>
  </div>
  <div class="card-body">
    <div id="calendar" style="min-height: 600px;, width : auto;"></div>
  </div>
</div>

<script src="${pageContext.request.contextPath}/resources/vendor/fullcalendar/core/index.global.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/fullcalendar/daygrid/index.global.min.js"></script>
<script>
document.addEventListener('DOMContentLoaded', function () {
	  var calendarEl = document.getElementById('calendar');
	  var calendar = new FullCalendar.Calendar(calendarEl, {
	    initialView: 'dayGridMonth',
	    locale: 'ko',
	    windowResize: function(view) {
	      calendar.updateSize();
	    },
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

	  // 윈도우 리사이즈 대응
	  window.addEventListener('resize', function () {
	    calendar.updateSize();
	  });
	});
</script>