<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="contextPath.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
<meta charset="UTF-8">
<title>주문 목록</title>
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
.orderlistapp{
  max-width: 500px;
  margin: auto;
  padding-top: 30px;
  padding-bottom: 30px;
}
</style>
<script>
</script>
</head>
<body>
<div class="orderlistapp">
<h2>주문 목록</h2>
<c:choose>
<c:when test="${requestScope.status == -1}">
  <span>접수된 주문이 없습니다.</span>
</c:when>
<c:otherwise>
  <br>
  <c:forEach var="order" items="${requestScope.orders}">
    <strong>주문번호: </strong><span>${order.order_no}</span><br>
    <strong>주문시간: </strong><fmt:formatDate value="${order.order_time}" pattern="yy-MM-dd hh:mm:ss"/><br><br>
    <strong>주문목록</strong>
    <table>
      <tr>
        <th>상품번호</th>
        <th>상품명</th>
        <th>가격</th>
        <th>수량</th>
      </tr>
      <c:forEach var="detail" items="${order.orderDetails}">
        <tr>
          <td>${detail.product.prod_no}</td>
          <td>${detail.product.prod_name}</td>
          <td>${detail.product.prod_price}</td>
          <td>${detail.order_quantity}</td>
        </tr>
      </c:forEach>
    </table><br><hr><br>
  </c:forEach>
</c:otherwise>
</c:choose>
</div>
</body>