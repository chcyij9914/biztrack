<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty sessionScope.loginInfo}">
    <c:redirect url="/login.do" />
</c:if>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>거래처 수정</title>
  <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body id="page-top">
<div id="wrapper">

  <c:import url="/WEB-INF/views/common/menubar.jsp" />
  
  <div id="content-wrapper" class="d-flex flex-column">
    <div id="content">
    
      <c:import url="/WEB-INF/views/common/topbar.jsp" />

      <div class="container-fluid">
        <h1 class="h3 mb-4 text-gray-800">거래처 정보 수정</h1>
        <p class="mb-4 small text-muted">등록된 <strong>${client.clientName}</strong> 거래처 수정 중입니다.</p>
        
        <form action="cupdate.do" method="post" enctype="multipart/form-data">
          <input type="hidden" name="clientId" value="${client.clientId}"/>
          
          <div class="card shadow mb-4">
            <div class="card-body">
              <table class="table table-bordered">
                <tbody>
                  <tr>
                    <th>거래처명</th><td>${client.clientName}</td>
                    <th>대표자명</th><td><input type="text" class="form-control" name="ceoName" value="${client.ceoName}"/></td>
                  </tr>
                  <tr>
                    <th>사업자번호</th><td><input type="text" class="form-control" name="businessNumber" value="${client.businessNumber}"/></td>
                    <th>회사 전화번호</th><td><input type="text" class="form-control" name="companyPhone" value="${client.companyPhone}"/></td>
                  </tr>
                  <tr>
                    <th>팩스번호</th><td><input type="text" class="form-control" name="fax" value="${client.fax}"/></td>
                    <th>홈페이지</th><td><input type="text" class="form-control" name="url" value="${client.url}"/></td>
                  </tr>
                  <tr>
                    <th>주소</th>
                    <td colspan="3">
                      <div class="input-group">
                        <input type="text" class="form-control" id="address" name="address" value="${client.address}" readonly />
                        <div class="input-group-append">
                          <button type="button" class="btn btn-primary" onclick="execDaumPostcode()">주소 검색</button>
                        </div>
                      </div>
                    </td>
                  </tr>
                  <tr>
                    <th>계약 시작일</th><td><input type="date" class="form-control" name="contractStartDate" value="${client.contractStartDate}"/></td>
                    <th>계약 종료일</th><td><input type="date" class="form-control" name="contractEndDate" value="${client.contractEndDate}"/></td>
                  </tr>
                  <tr>
                    <th>최초 담당자</th><td colspan="3">${client.firstManagerId} / ${client.firstManagerName} / ${client.firstManagerJob}</td>
                  </tr>
                  <tr>
                    <th>현재 담당자</th>
                    <td colspan="3">
					  <div class="input-group mb-2">
					    <input type="text" class="form-control" id="currentManagerId" name="currentManagerId"
					           value="${client.currentManagerId}" placeholder="사번 입력" />
					    <div class="input-group-append">
					      <button type="button" class="btn btn-outline-primary" id="fetchManagerBtn">조회</button>
					    </div>
					  </div>
					  <input type="text" class="form-control mb-2" id="currentManagerName"
					         value="${client.currentManagerName}" readonly placeholder="이름" />
					  <input type="text" class="form-control" id="currentManagerJob"
					         value="${client.currentManagerJob}" readonly placeholder="직급" />
					</td>
					<script>
					  $("#fetchManagerBtn").on("click", function () {
					    let empId = $("#currentManagerId").val().trim();
					    if (empId === "") {
					      alert("사번을 입력해주세요.");
					      return;
					    }
					
					    $.ajax({
					      url: "${pageContext.request.contextPath}/client/fetchEmpInfo.do",
					      type: "GET",
					      data: { empId: empId },
					      success: function (res) {
					        if (res && res.empName) {
					          $("#currentManagerName").val(res.empName);
					          $("#currentManagerJob").val(res.jobName); // 서버에서 jobId → jobName으로 변환되어 와야 함
					        } else {
					          alert("존재하지 않는 사원입니다.");
					          $("#currentManagerName").val("");
					          $("#currentManagerJob").val("");
					        }
					      },
					      error: function () {
					        alert("직원 정보를 불러오는 데 실패했습니다.");
					      }
					    });
					  });
					</script>
                  </tr>
                </tbody>
              </table>

              <h5 class="mt-4">담당자 정보</h5>
              <table class="table table-bordered">
                <tbody>
                  <tr>
                    <th>담당자명</th>
                    <td><input type="text" class="form-control" name="directorName" value="${client.directorName}"/></td>
                    <th>전화번호</th>
                    <td><input type="text" class="form-control" name="directorPhone" value="${client.directorPhone}"/></td>
                  </tr>
                  <tr>
                    <th>이메일</th>
                    <td colspan="3"><input type="email" class="form-control" name="email" value="${client.email}"/></td>
                  </tr>
                </tbody>
              </table>

              <h5 class="mt-4">명함 이미지 수정</h5>
              <div class="form-group">
                <c:if test="${not empty businessCardFilePath}">
                  <p>현재 등록된 명함:</p>
                  <img src="${pageContext.request.contextPath}${businessCardFilePath}" style="max-width:200px;"><br><br>
                </c:if>
                <input type="file" name="businessCardFile" class="form-control-file">
              </div>

              <div class="text-center mt-4">
                <button type="submit" class="btn btn-success">수정 저장</button>
                <button type="button" class="btn btn-secondary" onclick="history.back();">취소</button>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<!-- 주소 API -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            document.getElementById("address").value = data.address;
        }
    }).open();
}
</script>

<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>
</body>
</html>
