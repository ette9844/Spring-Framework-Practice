<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="contextPath.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #eeeeee;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #eeeeee;
}
.cartapp{
  max-width: 500px;
  margin: auto;
  padding-top: 30px;
  padding-bottom: 30px;
}
#order{
  display: block;
  width: 150px;
  height: auto;
  padding: 30px;
  padding-top: 15px;
  padding-bottom: 15px;
  background-color: white;
  border: 1px solid;
  margin-top: 30px;
  -webkit-transition-duration: 0.4s; /* Safari */
  transition-duration: 0.4s;
  cursor: pointer;
  margin: auto;
  margin-top: 30px;
  margin-bottom: 30px;
}
#order:hover {
  background-color: black;
  color: white;
}
</style>
<script>
$(function(){
	// 주문하기 클릭 리스너
	$("#order").click(function(){
		$.ajax({
			url: '${contextPath}/addorder',
			method: 'post',
			success: function(data){
				var jsonObj = JSON.parse(data);
				if(jsonObj.status == 0){
					// 로그인 되지않은 사용자 라면 로그인 화면으로 이동
					$("header>nav>ul>li>a[href='${contextPath}/jq/login.jsp']").trigger("click");	
				} else if(jsonObj.status == -1){
					// 주문 접수 실패
					alert("주문을 접수할 수 없습니다.");					
				} else {
					// 주문 접수 성공
					alert("해당 주문이 성공적으로 등록되었습니다.");
				}
				// 상품 목록 화면으로 이동
				$("header>nav>ul>li>a[href='${contextPath}/productlist").trigger("click");	
			}
		});
	});
});
</script>
</head>
<body>
<div class="cartapp">
<h2>장바구니</h2>
<c:choose>
<c:when test="${sessionScope.cart == null}">
  <span>장바구니에 상품이 없습니다.</span>
</c:when>
<c:otherwise>
  <table>
    <tr>
      <th>상품번호</th>
      <th>상품명</th>
      <th>가격</th>
      <th>수량</th>
    </tr>
    <c:forEach var="item" items="${sessionScope.cart}">
      <tr>
        <td>${item.key.prod_no}</td>
        <td>${item.key.prod_name}</td>
        <td>${item.key.prod_price}</td>
        <td>${item.value}</td>
      </tr>
    </c:forEach>
  </table>
<button id="order">주문하기</button>
</c:otherwise>
</c:choose>
</div>
</body>
