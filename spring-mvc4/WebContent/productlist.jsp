<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="contextPath.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
div.productlist {
  margin: 30px;
  border: 1px solid #ccc;
  float: left;
  width: 300px;
}

div.productlist:hover {
  border: 1px solid #777;
}

div.productlist img {
  width: 100%;
  height: auto;
}

div.desc {
  padding: 15px;
  text-align: center;
}

.list{
text-align: left;
font-size: 15px;
}
.name{
letter-spacing: 1px;
font-size: 18px;
}
ul.a {
  list-style-type: circle;
}


</style>

<script>
$(".item").click(function(){
	var prod_no = $(this).find(".list_no").html();
	console.log(prod_no);
	$.ajax({
		url:"${contextPath}/productdetail",
		data:'prod_no='+prod_no,
		success: function(data){
			$("section").empty();
			$("section").html(data);
		}    		
	});
	return false;
});
</script>
</head>
<body>

<h1 style="margin-left:30px; margin-top:30px">주문목록</h1>
<section>
<div class = "productlist">
<c:if test="${requestScope.list != null}">
<c:forEach var ="p" items = "${requestScope.list}">
<div class="item">
<a target="_blank" href="${p.prod_no}">
<img class= "image" src ="${contextPath}/images/${p.prod_no}.jpg" alt="메뉴사진" width="600" height="400">
  </a>
  <div class="desc">
  <ul class="a">
  	<li class="list_name">${p.prod_name}</li>
  	<li class="list_no" style="display:none;">${p.prod_no}</li>
  	<%-- <li class="list">${p.cate_name}</li>
 	<li class="list">${p.prod_price}원</li> --%>
  </ul>
 </div>
 </div>
</c:forEach>
</c:if>
</div>
</section>
</body>
</html>