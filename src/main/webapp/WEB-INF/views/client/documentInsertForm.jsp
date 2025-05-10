<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>문서 등록</title>
  <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
  <style>
    #itemTable th,
    #itemTable td {
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
      <form action="${pageContext.request.contextPath}/client/documentInsert.do" method="post" enctype="multipart/form-data">

        <!-- 상단 필드 -->
        <div class="form-row mb-3">
          <div class="col-md-2">
            <label>문서유형</label>
            <label>문서유형</label>
		    <input type="hidden" name="documentTypeId" value="D" />
		    <input type="text" class="form-control" value="제안서" readonly />
          </div>
          <div class="col-md-2">
            <label>문서번호</label>
            <input type="text" class="form-control" value="자동 생성됨" readonly />
          </div>
          <div class="col-md-4">
            <label>제목</label>
            <input type="text" name="title" class="form-control" required />
          </div>
        </div>

        <div class="form-row mb-3">
          <div class="col-md-6">
            <label>작성자</label>
            <input type="text" class="form-control" value="${sessionScope.loginInfo.empId} / ${sessionScope.loginInfo.empName} / ${sessionScope.loginInfo.jobId}" readonly />
            <input type="hidden" name="writerEmpId" value="${sessionScope.loginInfo.empId}" />
          </div>
          <div class="col-md-6">
            <label>담당자</label>
            <input type="text" class="form-control" value="${sessionScope.loginInfo.empId} / ${sessionScope.loginInfo.empName} / ${sessionScope.loginInfo.jobId}" readonly />
            <input type="hidden" name="managerEmpId" value="${sessionScope.loginInfo.empId}" />
          </div>
        </div>

        <!-- 결재자 지정 (조직도 팝업 1개로 통합) -->
		<div class="form-row mb-3">
		  <div class="col-md-6">
		    <label>결재자 선택</label>
		    <div class="input-group">
		      <!-- 사용자 표시용 -->
		      <input type="text" id="approver1Display" class="form-control" placeholder="1차 결재자 이름/직급" readonly />
		      <input type="text" id="approver2Display" class="form-control" placeholder="2차 결재자 이름/직급" readonly />
		      <div class="input-group-append">
		        <button type="button" class="btn btn-outline-secondary" onclick="openApproverPopup()">조직도에서 선택</button>
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

        <!-- 품목 리스트 테이블 -->
        <table class="table table-bordered mb-3" id="itemTable">
          <thead>
            <tr class="text-center">
              <th style="width: 30px;">#</th>
              <th style="min-width: 180px;">물품코드</th>
              <th>물품명</th>
              <th>수량</th>
              <th>단가</th>
              <th>금액</th>
              <th>결제수단</th>
              <th style="width: 80px;">삭제</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>1</td>
              <td>
                <select name="items[0].productId" class="form-control product-select" onchange="updatePrice(this)">
				  <option value="" disabled selected>-- 품목 선택 --</option>  
				  <c:forEach var="p" items="${productList}">
					  <option value="${p.productId}"
					          data-name="${p.productName}"
					          data-price="${p.salePrice}"
					          data-category="${p.categoryId}">
					    ${p.productId} - ${p.productName}
					  </option>
					</c:forEach>
				</select>
              </td>
              <td><input type="text" name="items[0].productName" class="form-control" readonly></td>
              <td><input type="number" name="items[0].quantity" class="form-control" oninput="calculateAmount(0)"></td>
              <td><input type="number" name="items[0].salePrice" class="form-control" readonly></td>
              <td><input type="number" name="items[0].amount" class="form-control" readonly></td>
              <td>
                <select name="items[0].paymentMethod" class="form-control payment-select" required>
				  <option value="" disabled selected>-- 결제수단 선택 --</option>
				  <option value="신용카드">신용카드</option>
				  <option value="계좌이제">계좌이체</option>
				  <option value="현금">현금</option>
				</select>
              </td>
              <td><button type="button" class="btn btn-danger btn-sm btn-delete" onclick="removeRow(this)">삭제</button></td>
            </tr>
          </tbody>
        </table>
        <button type="button" class="btn btn-outline-primary mb-3" onclick="addItemRow()">+ 품목 추가</button>
        <!-- 총합계 표시 -->
		<div class="text-right mb-3">
		  <strong>총합계:</strong> <span id="totalAmountDisplay">0</span>원
		</div>

        <!-- 거래처 및 거래일자 -->
        <div class="form-row mb-3">
          <div class="col-md-4">
            <label>거래처</label>
            <select name="clientId" id="clientId" class="form-control" required>
			  <option value="" disabled selected>-- 거래처 선택 --</option>
			  <c:forEach var="c" items="${clientList}">
				  <option value="${c.clientId}" data-category="${c.categoryId}">
				    ${c.clientId} - ${c.clientName}
				  </option>
				</c:forEach>
			</select>
          </div>
          <div class="col-md-4">
            <label>거래일자</label>
            <input type="date" name="documentDate" class="form-control" required />
          </div>
        </div>

        <!-- 비고 -->
        <div class="form-group">
          <label>비고</label>
          <textarea name="remarks" class="form-control" rows="3"></textarea>
        </div>

        <!-- 첨부파일 -->
        <div class="form-group">
          <label>첨부파일</label>
          <input type="file" name="uploadFile" class="form-control-file" />
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
$(function () {
	  // 최초 로딩 시 상품 select 옵션 백업
	  $('.product-select').each(function () {
	    $(this).data('original-options', $(this).html());
	  });

	  // 거래처 선택 시: 같은 카테고리의 상품만 필터링
	  $('#clientId').on('change', function () {
	    const selectedCategoryId = $(this).find('option:selected').data('category');

	    $('#itemTable tbody tr').each(function () {
	    	  const $select = $(this).find('.product-select');
	    	  const backup = $select.data('original-options');
	    	  if (!backup) return;

	    	  const selectedValue = $select.val(); // 선택된 값 저장

	    	  const options = $('<div>' + backup + '</div>').find('option');
	    	  const filtered = options.filter(function () {
	    	    return $(this).data('category') == selectedCategoryId;
	    	  });

	    	  $select.empty().append('<option value="">-- 품목 선택 --</option>').append(filtered);

	    	  // 다시 선택 복원 (있을 경우만)
	    	  if (filtered.filter('[value="' + selectedValue + '"]').length > 0) {
	    	    $select.val(selectedValue);
	    	  }
	    	});
	  });

	  // 상품 선택 시: 같은 카테고리의 거래처만 표시
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

	// 품목 행 추가 시: 거래처 필터 유지
	function addItemRow() {
	  const table = document.querySelector('#itemTable tbody');
	  const rowCount = table.rows.length;
	  const clone = table.rows[0].cloneNode(true);
	
	  clone.querySelectorAll('input, select').forEach(el => {
	    const name = el.name.replace(/\[\d+\]/, '[' + rowCount + ']');
	    el.name = name;
	
	    if (el.tagName === 'INPUT') {
	      el.value = (el.type === 'number' || el.type === 'text') ? '' : el.value;
	    }
	
	    if (el.classList.contains('payment-select')) {
	      el.innerHTML = `
	        <option value="" disabled selected>-- 결제수단 선택 --</option>
	        <option value="신용카드">신용카드</option>
	        <option value="계좌이제">계좌이체</option>
	        <option value="현금">현금</option>
	      `;
	    }
	  });
	
	  table.appendChild(clone);

	  // 거래처 카테고리 기준 필터링
	  const selectedCategoryId = $('#clientId option:selected').data('category');
	  const $select = $(clone).find('.product-select');
	  const backup = $select.data('original-options') || $select.html();
	  $select.data('original-options', backup);

	  const options = $('<div>' + backup + '</div>').find('option');
	  const filtered = options.filter(function () {
	    return $(this).data('category') == selectedCategoryId;
	  });

	  $select.empty().append('<option value="">-- 품목 선택 --</option>').append(filtered);
	}

	// 행 삭제
	function removeRow(btn) {
	  const table = document.querySelector('#itemTable tbody');
	  if (table.rows.length > 1) btn.closest('tr').remove();
	  updateTotalAmount(); // ← 총합 업데이트 호출
	}

	// 금액 계산
	function updatePrice(select) {
	  const row = select.closest('tr');
	  const idx = Array.from(document.querySelectorAll('#itemTable tbody tr')).indexOf(row);
	
	  const option = select.options[select.selectedIndex];
	  row.querySelector('[name="items[' + idx + '].productName"]').value = option.getAttribute('data-name');
	  row.querySelector('[name="items[' + idx + '].salePrice"]').value = option.getAttribute('data-price');
	  calculateAmount(idx);
	}

	function calculateAmount(idx) {
		  const row = document.querySelectorAll('#itemTable tbody tr')[idx];
		  const qty = parseInt(row.querySelector('[name="items[' + idx + '].quantity"]').value || 0);
		  const price = parseInt(row.querySelector('[name="items[' + idx + '].salePrice"]').value || 0);
		  row.querySelector('[name="items[' + idx + '].amount"]').value = qty * price;

		  updateTotalAmount(); // ← 총합 업데이트 호출
		}
	
	document.addEventListener('input', function (e) {
		  if (e.target && e.target.name.endsWith('.quantity')) {
		    const row = e.target.closest('tr');
		    const idx = Array.from(document.querySelectorAll('#itemTable tbody tr')).indexOf(row);
		    calculateAmount(idx);
		    updateTotalAmount();
		  }
		});
	
	function updateTotalAmount() {
		  let total = 0;
		  document.querySelectorAll('[name$=".amount"]').forEach(el => {
		    const val = parseInt(el.value || 0);
		    total += isNaN(val) ? 0 : val;
		  });
		  document.getElementById('totalAmountDisplay').innerText = total.toLocaleString();
		}
	updateTotalAmount(); // 페이지 로딩 시 총합 초기 표시
</script>
</body>
</html>
