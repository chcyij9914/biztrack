<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <c:forEach var="item" items="${documentItemList}" varStatus="status">
      <tr>
        <td>
          <input type="hidden" name="items[${status.index}].itemId" value="${item.itemId}" />
          <input type="hidden" name="items[${status.index}].documentId" value="${item.documentId}" />
          ${status.index + 1}
        </td>
        <td>
          <select name="items[${status.index}].productId" class="form-control product-select" onchange="updatePrice(this)">
            <option value="" disabled selected>-- 품목 선택 --</option>
            <c:forEach var="p" items="${productList}">
              <option value="${p.productId}"
                      data-name="${p.productName}"
                      data-price="${p.salePrice}"
                      data-category="${p.categoryId}"
                      ${p.productId == item.productId ? 'selected' : ''}>
                ${p.productId} - ${p.productName}
              </option>
            </c:forEach>
          </select>
        </td>
        <td><input type="text" name="items[${status.index}].productName" class="form-control" value="${item.productName}" readonly /></td>
        <td><input type="number" name="items[${status.index}].quantity" class="form-control" value="${item.quantity}" oninput="handleQuantityInput(this)" /></td>
        <td><input type="number" name="items[${status.index}].salePrice" class="form-control" value="${item.salePrice}" readonly /></td>
        <td><input type="number" name="items[${status.index}].amount" class="form-control" readonly /></td>
        <td>
          <select name="items[${status.index}].paymentMethod" class="form-control payment-select">
            <option value="">-- 선택 --</option>
            <option value="카드" ${item.paymentMethod == '카드' ? 'selected' : ''}>신용카드</option>
            <option value="계좌이체" ${item.paymentMethod == '계좌이체' ? 'selected' : ''}>계좌이체</option>
            <option value="현금" ${item.paymentMethod == '현금' ? 'selected' : ''}>현금</option>
          </select>
        </td>
        <td><button type="button" class="btn btn-danger btn-sm btn-delete" onclick="removeRow(this)">삭제</button></td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<!-- + 버튼과 총합 -->
<button type="button" class="btn btn-outline-primary mb-3" onclick="addItemRow()">+ 품목 추가</button>
<div class="text-right mb-3">
  <strong>총합계:</strong> <span id="totalAmountDisplay">0</span>원
</div>

<script>
$(function () {
  // 옵션 백업
  $('.product-select').each(function () {
    $(this).data('original-options', $(this).html());
  });

  // 문서 로딩 시 금액 계산
  $('#itemTable tbody tr').each(function (index) {
    calculateAmount(index);
  });
  updateTotalAmount();

  // 거래처 변경 시 필드 초기화 + 상품 필터링
  $('#clientId').on('change', function () {
    const selectedCategoryId = $(this).find('option:selected').data('category');

    $('#itemTable tbody tr').each(function () {
      const $row = $(this);
      const $select = $row.find('.product-select');
      const originalOptions = $select.data('original-options');

      const filteredOptions = $('<select>' + originalOptions + '</select>').find('option').filter(function () {
        return $(this).data('category') == selectedCategoryId;
      });

      $select.empty().append('<option value="">-- 품목 선택 --</option>').append(filteredOptions);

      // 값 초기화 (itemId, documentId 제외)
      $row.find('input[type=text], input[type=number]').each(function () {
        const name = $(this).attr('name');
        if (!name.includes('itemId') && !name.includes('documentId')) {
          $(this).val('');
        }
      });

      $row.find('.payment-select').val('');
    });

    updateTotalAmount();
  });
});

// 수량 입력 시 동적 index 기반 계산
function handleQuantityInput(input) {
  const row = input.closest('tr');
  const idx = Array.from(document.querySelectorAll('#itemTable tbody tr')).indexOf(row);
  calculateAmount(idx);
}

// 상품 선택 시 단가 반영 + 금액 계산
function updatePrice(select) {
  const row = select.closest('tr');
  const idx = Array.from(document.querySelectorAll('#itemTable tbody tr')).indexOf(row);
  const selected = select.options[select.selectedIndex];
  row.querySelector('[name$=".productName"]').value = selected.getAttribute('data-name');
  row.querySelector('[name$=".salePrice"]').value = selected.getAttribute('data-price');
  calculateAmount(idx);
}

// 금액 계산 (수량 × 단가)
function calculateAmount(idx) {
  const row = document.querySelectorAll('#itemTable tbody tr')[idx];
  const qty = parseInt(row.querySelector('[name$=".quantity"]').value || 0);
  const price = parseInt(row.querySelector('[name$=".salePrice"]').value || 0);
  row.querySelector('[name$=".amount"]').value = qty * price;
  updateTotalAmount();
}

// 총합계 계산
function updateTotalAmount() {
  let total = 0;
  document.querySelectorAll('[name$=".amount"]').forEach(el => {
    const val = parseInt(el.value || 0);
    if (!isNaN(val)) total += val;
  });
  document.getElementById('totalAmountDisplay').innerText = total.toLocaleString();
}

// 행 삭제
function removeRow(btn) {
  const tbody = document.querySelector('#itemTable tbody');
  if (tbody.rows.length > 1) btn.closest('tr').remove();
  updateTotalAmount();
}

// 행 추가
function addItemRow() {
  const tbody = document.querySelector('#itemTable tbody');
  const rowCount = tbody.rows.length;
  const clone = tbody.rows[0].cloneNode(true);

  clone.querySelectorAll('input, select').forEach(el => {
    el.name = el.name.replace(/\[\d+\]/, '[' + rowCount + ']');
    if (el.tagName === 'INPUT' && !el.name.includes('itemId') && !el.name.includes('documentId')) {
      el.value = '';
    }
    if (el.classList.contains('payment-select')) {
      el.innerHTML = `
        <option value="">-- 선택 --</option>
        <option value="카드">신용카드</option>
        <option value="계좌이체">계좌이체</option>
        <option value="현금">현금</option>`;
    }
  });

  const selectedCategoryId = $('#clientId option:selected').data('category');
  const $newSelect = $(clone).find('.product-select');
  const original = $('.product-select:first').data('original-options');
  const filtered = $('<select>' + original + '</select>').find('option').filter(function () {
    return $(this).data('category') == selectedCategoryId;
  });

  $newSelect.empty().append('<option value="">-- 품목 선택 --</option>').append(filtered);
  tbody.appendChild(clone);
}
</script>
