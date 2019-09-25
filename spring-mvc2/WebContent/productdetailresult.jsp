<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../contextPath.jsp"%>
<head>
<meta charset="UTF-8">
<title>상품 상세 정보</title>
<style>
section{
  max-width: 750px;
  padding-top: 30px;
  margin: auto;
}
.detail-div{
  display: inline;
  padding-top: 30px;
}
.img-div{
  margin-right: 30px;
  float:left;
}
#cart{
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
}
#cart:hover {
  background-color: black;
  color: white;
}
/* Popup container */
.popup {
  position: relative;
  display: inline-block;
  cursor: pointer;
}

/* The actual popup (appears on top) */
.popup .popuptext {
  display: none;
  width: 160px;
  background-color: #555;
  color: #fff;
  text-align: center;
  border-radius: 6px;
  padding: 8px 0;
  position: absolute;
  z-index: 1;
  bottom: 70%;
  left: 50%;
  margin-left: -80px;
}

/* Popup arrow */
.popup .popuptext::after {
  content: "";
  position: absolute;
  top: 100%;
  left: 50%;
  margin-left: -5px;
  border-width: 5px;
  border-style: solid;
  border-color: #555 transparent transparent transparent;
}
</style>
<script>
$(function(){
	// 전달된 json parse해서 viewer에 반영
	var json = ${requestScope.result};
	console.log(json);
	console.log(json.product.prod_no);
	
	// html set
	$("#prod_no").html(json.product.prod_no);
	$("#prod_price").html(json.product.prod_price);
	$("#prod_name").html(json.product.prod_name);
	$("#prod_detail").html(json.product.prod_detail);
	$("#prod_cate_name").html(json.product.prod_cate_name);
	
	var url = "${contextPath}/images/menu/"+json.product.prod_no+".jpg";
	$("#prod_img").attr("src", url);
	
	var clicked = false;
	// 장바구니 담기 버튼 클릭 리스너
	$("#cart").click(function(){
		if(clicked == true)
			return false;
		var prod_no = $("#prod_no").html();
		var prod_name = $("#prod_name").html();
		var prod_price = $("#prod_price").html();
		var quantity = $("input[name='quantity']").val();
		
		$.ajax({
			url: '${contextPath}/addcart',
			method: 'post',
			data: 'prod_no='+prod_no
				 +'&prod_name='+prod_name
				 +'&prod_price='+prod_price
				 +'&quantity='+quantity,
			success: function(data){
				// popup
				$("#myPopup").animate({
					height: 'show'
				});
				clicked = true;
			}
		});
		return false;
	});
});
</script>
</head>
<body>
<div class="img-div">
  <strong id="prod_cate_name"></strong><br><br>
  <img id="prod_img"
  	   alt="menu img" 
       src="" 
       height="300px" width="280px"/>
</div>
<div class="detail-div">
  <br><br>
  <strong id="prod_name"></strong><br>
  <hr><br>
  <small id="prod_detail"></small><br><br>
  <span>상품번호: </span><span id="prod_no"></span><br><br>
  <span>상품가격: </span><span id="prod_price"></span><br><br>
  <span>상품수량: </span><input type="number" name="quantity" value="1" min="1" max="99"><br>
  <div class="popup">
    <span class="popuptext" id="myPopup">장바구니에<br>등록되었습니다!</span>
    <button id="cart" type="submit">장바구니 넣기</button>
  </div>
</div>
</body>