<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty sessionScope.loginInfo}">
    <c:redirect url="/login.do" />
</c:if>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>입고 작성</title>
<link
	href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<style>
#itemTable th, #itemTable td {
	vertical-align: middle;
	text-align: center;
	font-size: 15px;
}

td select.form-control.product-select {
	min-width: 180px;
}

td input.form-control.product-name {
	min-width: 160px;
}

td select.form-control.payment-select {
	min-width: 120px;
}

td button.btn-sm {
	padding: 0.25rem 0.75rem;
	white-space: nowrap;
}

.btn-delete {
	min-width: 50px;
	height: 32px;
	line-height: 1.2;
}
</style>
<script>
  // 결재자 적용
  function applyApprovers(approver1Id, approver2Id) {
    const a1 = document.getElementById("approver1Info");
    const a2 = document.getElementById("approver2Info");
    a1.value = approver1Id;
    a2.value = approver2Id;
    document.getElementById("approver1Display").value = approver1Id;
    document.getElementById("approver2Display").value = approver2Id;
  }

  function displayApproverInfo() {
    const a1 = document.getElementById("approver1Info");
    const a2 = document.getElementById("approver2Info");
    document.getElementById("approver1Display").value = a1.value;
    document.getElementById("approver2Display").value = a2.value;
  }

  function openApproverPopup() {
	  window.open('${pageContext.request.contextPath}/department/approverPopup.do?target=both',
	              'approverPopup',
	              'width=900,height=750');
	}
</script>
</head>
<body class="bg-light">
	<div class="container mt-5">
		<div class="card shadow">
			<div class="card-header bg-primary text-white">
				<h4 class="m-0">문서 등록</h4>
			</div>
			<div class="card-body">
				<form
					action="${pageContext.request.contextPath}/inbound/new-inbound.do"
					method="post" enctype="multipart/form-data">

					<!-- 상단 필드 -->
					<div class="form-row mb-3">
						<div class="col-md-2">
							<label>문서유형</label> <label>문서유형</label> <input type="hidden"
								name="documentTypeId" value="I" /> <input type="text"
								class="form-control" value="입고서" />
						</div>
						<div class="col-md-2">
							<label>문서번호</label> <input type="text" class="form-control"
								value="자동 생성됨" readonly />
						</div>
						<div class="col-md-4">
							<label>제목</label> <input type="text" name="title"
								class="form-control" required />
						</div>
					</div>

					<div class="form-row mb-3">
						<div class="col-md-6">
							<label>작성자</label> <input type="text" class="form-control"
								value="${sessionScope.loginInfo.empId} / ${sessionScope.loginInfo.empName} / ${sessionScope.loginInfo.jobId}"
								readonly /> <input type="hidden" name="writerEmpId"
								value="${sessionScope.loginInfo.empId}" />
						</div>
						<div class="col-md-6">
							<label>담당자</label> <input type="text" class="form-control"
								value="${sessionScope.loginInfo.empId} / ${sessionScope.loginInfo.empName} / ${sessionScope.loginInfo.jobId}"
								readonly /> <input type="hidden" name="managerEmpId"
								value="${sessionScope.loginInfo.empId}" />
						</div>
					</div>

					<!-- 결재자 지정 (조직도 팝업 1개로 통합) -->
					<div class="form-row mb-3">
						<div class="col-md-6">
							<label>결재자 선택</label>
							<div class="input-group">
								<!-- 사용자 표시용 -->
								<input type="text" id="approver1Display" class="form-control"
									placeholder="1차 결재자 사번" readonly /> <input type="text"
									id="approver2Display" class="form-control"
									placeholder="2차 결재자 사번" readonly />
								<div class="input-group-append">
									<button type="button" class="btn btn-outline-secondary"
										onclick="openApproverPopup()">조직도에서 선택</button>
								</div>
							</div>

							<!-- 실제 전송용 히든 필드 -->
							<input type="hidden" name="approver1Info" id="approver1Info" />
							<input type="hidden" name="approver2Info" id="approver2Info" />
						</div>
					</div>

					<!-- <div class="form-row mb-3">
          <div class="col-md-3">
            <label>1차 결재일자</label>
            <input type="date" name="approve1Date" class="form-control" />
          </div>
          <div class="col-md-3">
            <label>2차 결재일자</label>
            <input type="date" name="approve2Date" class="form-control" />
          </div>
        </div> -->
					<br>
					<!-- 품목 리스트 테이블 -->
					<table class="table table-bordered mb-3" id="itemTable">
						<thead>
							<tr class="text-center">
								<th style="width: 30px;">#</th>
								<th style="min-width: 180px;">물품코드</th>
								<th>물품명</th>
								<th>수량</th>
								<th>단가</th>
								<th style="width: 80px;">삭제</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><input type="hidden" name="items[0].itemId" value="" />
									<input type="hidden" name="items[0].documentId" value="" /> 1
								</td>
								<td><select name="items[0].productId"
									class="form-control product-select"
									onchange="updatePrice(this)">
									<option value="" disabled selected>-- 품목 선택 --</option>
										<c:forEach var="p" items="${productList}">
											<option value="${p.productId}" data-name="${p.productName}"
												data-category="${p.categoryId}">${p.productId}-
												${p.productName}</option>
										</c:forEach>
								</select></td>
								<td><input type="text" name="items[0].productName"
									class="form-control" readonly></td>
								<td><input type="number" name="items[0].quantity"
									class="form-control"></td>
								<td><input type="number" name="items[0].unitPrice"
									class="form-control"></td>
								<td><button type="button"
										class="btn btn-danger btn-sm btn-delete"
										onclick="removeRow(this)">삭제</button></td>
							</tr>
						</tbody>
					</table>
					<button type="button" class="btn btn-outline-primary mb-3"
						onclick="addItemRow()">+ 품목 추가</button>


					<!-- 거래처 및 입고일자 -->
					<div class="form-row mb-3">
						<div class="col-md-4">
							<label>거래처</label> <select name="clientId" id="clientId"
								class="form-control" required>
								<option value="" disabled selected>-- 거래처 선택 --</option>
								<c:forEach var="c" items="${clientList}">
									<option value="${c.clientId}" data-category="${c.categoryId}">
										${c.clientId} - ${c.clientName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-4">
							<label>입고일자</label> <input type="date" name="documentDate"
								class="form-control" required />
						</div>
					</div>

					<!-- 비고 -->
					<div class="form-group">
						<label>비고</label>
						<textarea name="remarks" class="form-control" rows="3"></textarea>
					</div>

					<!-- 첨부파일 -->
					<div class="form-group">
						<label>첨부파일</label> <input type="file" name="uploadFile"
							class="form-control-file" />
					</div>

					<!-- 버튼 -->
					<div class="text-right">
						<button type="submit" class="btn btn-primary">상신</button>
					</div>
					<c:if test="${not empty msg}">
						<div class="alert alert-success">${msg}</div>
					</c:if>
				</form>
			</div>
		</div>
	</div>

	<script>
/* -------------------------------------
   품목 필터링 및 행 추가 기능 스크립트
-------------------------------------- */

let productOptionBackup = null;

$(function () {
  // 초기: 모든 product-select 드롭다운 옵션 백업
  $('.product-select').each(function () {
    $(this).data('original-options', $(this).html());
  });

  // 거래처 선택 시 → 해당 거래처 카테고리에 맞는 상품 필터링
  $('#clientId').on('change', function () {
    const selectedCategoryId = $(this).find('option:selected').data('category');

    $('#itemTable tbody tr').each(function () {
      const $select = $(this).find('.product-select');
      const backup = $select.data('original-options');

      if (!backup) return;

      const selectedValue = $select.val(); // 기존 선택값 유지
      const options = $('<select>' + backup + '</select>').find('option');

      const filtered = options.filter(function () {
        return $(this).attr('data-category') == selectedCategoryId;
      });

      $select.empty()
             .append('<option value="" disabled selected>-- 품목 선택 --</option>')
             .append(filtered)
             .val('');
    });
  });

  // 상품 선택 시 → 거래처도 해당 카테고리만 보이게 필터링
  $(document).on('change', '.product-select', function () {
    const selectedCategory = $(this).find('option:selected').data('category');
    if (!selectedCategory) return;

    $('#clientId option').each(function () {
      const catId = $(this).data('category');
      if ($(this).val() === "" || catId == selectedCategory) {
        $(this).show();
      } else {
        $(this).hide();
      }
    });
  });
});

// 품목 선택 시 → 이름 자동 입력 + 금액 계산 함수 호출
function updatePrice(select) {
  const row = select.closest('tr');
  const idx = Array.from(document.querySelectorAll('#itemTable tbody tr')).indexOf(row);

  const option = select.options[select.selectedIndex];
  row.querySelector('[name="items[' + idx + '].productName"]').value = option.getAttribute('data-name');

  calculateAmount(idx); // 총액 계산 함수 (별도로 정의돼 있어야 함)
}

// 품목 행 추가 시 실행되는 함수
function addItemRow() {
  const tbody = document.querySelector('#itemTable tbody');
  const rowCount = tbody.rows.length;
  const clone = tbody.rows[0].cloneNode(true);

  // input, select 초기화 및 인덱스 재설정
  clone.querySelectorAll('input, select').forEach(el => {
    el.name = el.name.replace(/\[\d+\]/, '[' + rowCount + ']');
    if (el.tagName === 'INPUT' && !el.name.includes('itemId') && !el.name.includes('documentId')) {
      el.value = '';
    }
  });

  // select 옵션 백업 가져오기
  const $newSelect = $(clone).find('.product-select');
  const backup = $('.product-select:first').data('original-options');
  const $options = $('<select>' + backup + '</select>').find('option');

  const selectedCategoryId = $('#clientId option:selected').data('category');

  // 거래처 선택 여부에 따라 필터링 or 전체
  let filtered;
  if (selectedCategoryId) {
    filtered = $options.filter(function () {
      return $(this).attr('data-category') == selectedCategoryId;
    });
  } else {
    filtered = $options;
  }

  $newSelect
    .empty()
    .append(filtered)
    .val("");

  // 옵션 백업도 저장
  $newSelect.data('original-options', backup);

  tbody.appendChild(clone);
}

// 행 삭제 버튼 클릭 시 호출
function removeRow(btn) {
  const table = document.querySelector('#itemTable tbody');
  if (table.rows.length > 1) btn.closest('tr').remove();
  updateTotalAmount(); // 총합 재계산 함수 (정의돼 있어야 함)
}
</script>
</body>
</html>
