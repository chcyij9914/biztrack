<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Table -->
<div class="card-body">
	<div class="table-responsive">
		<table class="table table-bordered" id="trainingTable"
			style="table-layout: fixed; width: 100%;" cellspacing="0">
			<colgroup>
				<col style="width: 25%;">
				<col style="width: 25%;">
				<col style="width: 25%;">
				<col style="width: 25%;">
			</colgroup>
			<thead class="text-center bg-light">
				<table class="table table-bordered text-center">
					<thead class="thead-light">
						<tr>
							<!-- <th><input type="radio"></th> -->
							<th>공지 번호</th>
							<th>제목</th>
							<th>공지 내용</th>
							<th>등록일</th>
						</tr>
					</thead>
					
					<tbody class="text-center bg-white">
						<!-- 공지 출력 -->
						<c:forEach var="notice" items="${noticeList}">
								<tr>
									<td>${notice.noticeNo}</td>
									<td>${notice.noticeTitle}</td>
									<td>${notice.noticeContent}</td>
									<td>${notice.noticeDate}</td>
								</tr>
						</c:forEach>


					</tbody>
				</table>

				<script
					src="${pageContext.request.contextPath}/resources/vendor/fullcalendar/core/index.global.min.js"></script>
				<script
					src="${pageContext.request.contextPath}/resources/vendor/fullcalendar/daygrid/index.global.min.js"></script>
				<script>
					document
							.addEventListener(
									'DOMContentLoaded',
									function() {
										var calendarEl = document
												.getElementById('calendar');
										if (calendarEl) {
											var calendar = new FullCalendar.Calendar(
													calendarEl,
													{
														initialView : 'dayGridMonth',
														locale : 'ko',
														events : '${pageContext.request.contextPath}/schedule/events.do',
														headerToolbar : {
															left : 'prev,next today',
															center : 'title',
															right : 'dayGridMonth'
														},
														buttonText : {
															today : '오늘',
															month : '월'
														},
														eventClick : function(
																info) {
															const event = info.event;
															const ext = event.extendedProps;
															const isPublic = ext.scType === 'PUBLIC';
															document
																	.getElementById('modalTitle').innerText = event.title;
															document
																	.getElementById('modalScId').innerText = ext.scId
																	|| '-';
															document
																	.getElementById('modalPlace').innerText = ext.place
																	|| '-';
															document
																	.getElementById('modalType').innerText = ext.scType
																	|| '-';
															document
																	.getElementById('modalType').className = isPublic ? 'badge badge-danger'
																	: 'badge badge-primary';
															document
																	.getElementById('modalStart').innerText = ext.startDatetime
																	|| '-';
															document
																	.getElementById('modalEnd').innerText = ext.endDatetime
																	|| '-';
															$(
																	'#eventDetailModal')
																	.modal(
																			'show');
															info.jsEvent
																	.preventDefault();
														}
													});
											calendar.render();
										}
									});
				</script>