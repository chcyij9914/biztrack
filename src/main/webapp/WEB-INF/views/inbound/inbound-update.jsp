<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>문서 수정</title>

<link
	href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
</head>
<body class="bg-light">
	<div class="container mt-5">
		<div class="card shadow mb-4">
			<div class="card-header py-3">
				<h4 class="m-0 font-weight-bold text-primary">문서 수정</h4>
			</div>
			<div class="card-body">
				<form
					action="${pageContext.request.contextPath}/inbound/inbound-update.do"
					method="post" enctype="multipart/form-data"
					onsubmit="reindexItemNames()">
					<!-- 문서번호, 문서유형 -->
					<input type="hidden" name="originalClientId"
						value="${document.clientId}" />
					<div class="form-group">
						<label>문서번호</label> <input type="text" name="documentId"
							class="form-control" value="${document.documentId}" readonly>
					</div>
					<div class="form-group">
						<label>문서유형</label> <input type="text" class="form-control"
							value="${document.documentName}" readonly> <input
							type="hidden" name="documentTypeId"
							value="${document.documentTypeId}">
					</div>

					<!-- 제목 -->
					<div class="form-group">
						<label>제목</label> <input type="text" name="title"
							class="form-control" value="${document.title}">
					</div>

					<!-- 거래처 -->
					<div class="form-group">
						<label>거래처</label> <select name="clientId" id="clientId"
							class="form-control">
							<c:forEach var="client" items="${clientList}">
								<option value="${client.clientId}"
									data-category="${client.categoryId}"
									${client.clientId == document.clientId ? 'selected' : ''}>
									${client.clientName}</option>
							</c:forEach>
						</select>
					</div>

					<!-- 작성자 -->
					<div class="form-group">
						<label>작성자</label> <input type="text" class="form-control mb-2"
							value="${document.documentWriterId}" readonly> <input
							type="text" class="form-control mb-2"
							value="${document.documentWriterName}" readonly> <input
							type="text" class="form-control"
							value="${document.documentWriterJobTitle}" readonly>
					</div>

					<!-- 담당자 -->
					<div class="form-group">
						<label>문서 담당자</label>
						<div class="input-group mb-2">
							<input type="text" class="form-control" id="documentManagerId"
								name="documentManagerId" value="${document.documentManagerId}"
								placeholder="사번 입력" />
							<div class="input-group-append">
								<button type="button" class="btn btn-outline-primary"
									id="fetchDocManagerBtn">조회</button>
							</div>
						</div>
						<input type="text" class="form-control mb-2"
							id="documentManagerName" value="${document.documentManagerName}"
							readonly> <input type="text" class="form-control"
							id="documentManagerJob"
							value="${document.documentManagerJobTitle}" readonly>
					</div>

					<!-- 날짜 -->
					<div class="form-group">
						<label>작성일</label> <input type="text" class="form-control"
							value="<fmt:formatDate value='${document.createdDate}' pattern='yyyy-MM-dd' />"
							disabled>
					</div>
					<div class="form-group">
						<label>입고일자</label> <input type="date" name="documentDate"
							class="form-control"
							value="<fmt:formatDate value='${document.documentDate}' pattern='yyyy-MM-dd' />">
					</div>

					<!-- 결재자 -->
					<div class="form-group">
						<label>1차 결재자</label> <input type="text" name="firstApproverId"
							class="form-control mb-2" id="approver1Display"
							value="${approve.firstApproverId}" readonly> <label>2차
							결재자</label> <input type="text" name="secondApproverId"
							class="form-control mb-2" id="approver2Display"
							value="${approve.secondApproverId}" readonly>
						<button type="button" class="btn btn-sm btn-outline-primary"
							onclick="openApproverPopup()">결재자 선택</button>
					</div>

					<!-- 비고 -->
					<div class="form-group">
						<label>비고</label>
						<textarea name="remarks" class="form-control" rows="4">${document.remarks}</textarea>
					</div>

					<!-- 파일 -->
					<div class="form-group">
						<label>기존 첨부파일</label>
						<c:if test="${not empty file.renameFileName}">
							<p class="text-muted">
								현재 파일: ${file.originalFileName} <label><input
									type="checkbox" name="deleteFlag" value="yes" checked>
									삭제</label>
							</p>
						</c:if>
					</div>
					<div class="form-group">
						<label>새 파일 업로드</label> <input type="file" name="uploadFile"
							class="form-control-file">
					</div>

					<!-- 품목 테이블 -->
					<jsp:include page="documentItemListPartial.jsp" />

					<!-- 버튼 -->
					<div class="text-right">
						<button type="submit" class="btn btn-primary">저장</button>
						 <a href="${pageContext.request.contextPath}/inbound/inbound-document.do" class="btn btn-info">
                <i class="fas fa-list"></i> 목록으로
            </a>
					</div>

				</form>
			</div>
		</div>
	</div>

	<!-- 담당자 조회 / 결재자 선택 팝업 -->
	<script>
  $("#fetchDocManagerBtn").on("click", function () {
    const empId = $("#documentManagerId").val().trim();
    if (!empId) return alert("사번을 입력하세요");
    $.get("${pageContext.request.contextPath}/client/documentManEmpInfo.do", { empId }, function (res) {
      if (res && res.empName) {
        $("#documentManagerName").val(res.empName);
        $("#documentManagerJob").val(res.jobTitle);
      } else {
        alert("존재하지 않는 사원입니다.");
        $("#documentManagerName, #documentManagerJob").val("");
      }
    }).fail(() => alert("조회 실패"));
  });

  function openApproverPopup() {
    window.open('${pageContext.request.contextPath}/department/approverPopup.do?target=both',
                'approverPopup', 'width=900,height=750');
  }

  function applyApprovers(approver1Id, approver2Id) {
    $("#approver1Display").val(approver1Id);
    $("#approver2Display").val(approver2Id);
  }
  
  function reindexItemNames() {
	  $('#itemTable tbody tr').each(function (index) {
	    $(this).find('input, select').each(function () {
	      const name = $(this).attr('name');
	      if (name && name.includes('items[')) {
	        const newName = name.replace(/\[.*?\]/, '[' + index + ']');
	        $(this).attr('name', newName);
	        
	      }
	    });
	  });
	}
</script>
</body>
</html>
