<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="contextPath.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<meta charset="UTF-8">
<style>
.productlistapp{
  max-width: 800px;
  margin:auto;
}
.productlistapp h2{
  padding-left: 20px;
}
.grid-container {
  display: grid;
  grid-template-columns: auto auto auto;
  padding: 10px;
  text-align: center;
}
.grid-item {
  padding: 20px;
  font-size: 30px;
  text-align: center;
  max-width: 220px;
}
.text-box{
  margin: auto;
  margin-top: 20px;
  margin-bottom: 20px;
  text-align:center;
  width: auto;
}
.hidden{
  display: none;
}
</style>
<script>
$(function(){
	$(".gird-item").click(function(){
		var prod_no = $(this).find(".hidden").html();
		console.log(prod_no);
		$.ajax({
			url: '${contextPath}/productdetail',
			method: 'get',
			data: 'prod_no=' + prod_no,
			success: function(data){
				$("section").empty();
				$("section").html(data);
			}
		});
	});
});
</script>
</head>
<body>
<div class="productlistapp">
<h2>상품목록</h2>
<c:choose>
  <c:when test="${requestScope.status == -1}">
    등록된 상품이 없습니다.
  </c:when>
  <c:otherwise>
    <div class="grid-container">
    <c:forEach var="product" items="${requestScope.products}">
      <div class="gird-item">
        <img alt="메뉴 이미지" 
      		src="${pageContext.request.contextPath}/images/menu/${product.prod_no}.jpg" 
      		height="250px" 
      		width="220px"><br>
        <div class="text-box">
          <span>${product.prod_name}</span>
          <span class="hidden">${product.prod_no}</span>
        </div>
      </div>
    </c:forEach>
    </div>
  </c:otherwise>
</c:choose>
</div>
</body>