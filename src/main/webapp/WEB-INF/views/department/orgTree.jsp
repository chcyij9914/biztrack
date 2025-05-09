<%@ page contentType="text/html; charset=UTF-8" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/jquery.fancytree/dist/skin-win8/ui.fancytree.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery.fancytree/dist/jquery.fancytree-all-deps.min.js"></script>

<input type="text" id="treeSearch" class="form-control mb-2" placeholder="부서/직원 검색">
<div id="tree" style="max-height: 500px; overflow-y: auto; border: 1px solid #ccc; padding: 10px; border-radius: 5px;"></div>

<script>
$(function() {
  $("#tree").fancytree({
    source: {
      url: "${pageContext.servletContext.contextPath}/dept/tree.do",
      cache: false
    },
    activate: function(event, data) {
      const node = data.node;
      if (!node.folder) {
        const empId = node.key.replace(/^E/, '');
        const empName = node.empName || '';
        const roleName = node.roleName || '';
        const label = '${empId} / ${empName} / ${roleName}';

        const choice = window.prompt(
          `'${label}'을(를) 어떤 결재자로 지정할까요?\n1: 1차 결재자\n2: 2차 결재자`, "1"
        );

        if (choice === "1") {
          $("#approver1").val(label).attr("data-real-id", empId);
        } else if (choice === "2") {
          $("#approver2").val(label).attr("data-real-id", empId);
        } else if (choice !== null) {
          alert("1 또는 2만 입력해주세요.");
        }
      }
    }
  });

  $("#treeSearch").on("keyup", function () {
    const match = $(this).val();
    $("#tree").fancytree("getTree").filterNodes(match, { autoExpand: true });
  });
});
</script>

<!-- ✅ applyToParent()는 approverPopup에서 정의됨 -->
