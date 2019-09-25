<%@ page contentType="text/html; charset=UTF-8"%>
<%--boarddetailresult.jsp--%>
<%@include file="contextPath.jsp" %>
<style>
div.boardDetail{
 font-size: 14px;
 height: auto;
}
label:after{
 content: ": "
} 
span.detail{
/* white-space: normal;*//*기본값. 공백을 여러개 넣어도 공백1개만 표시. 글이 길어지면 자동줄바꿈됨!*/
/* white-space: nowrap; */ /*공백 여러개 넣어도 공백1개만 표시, 글이 길어져도 자동줄바꿈 안됨!*/
 /* white-space: pre-wrap; */ /*공백(줄바꿈,들여쓰기,spacebar)을 있는 그대로 표시, 글이 길어지면 자동 줄바꿈됨!*/
 white-space: pre-line; /*공백 여러개 넣어도 공백1개만 표시,  글이 길어지면 자동줄바꿈됨!*/ 
 
}
div.buttonGroup{
 height: auto;
 text-align: center !important;
 margin-top: 30px;
}
.btn{
 display: inline-block;
 text-align: center;
 vertical-align: middle;
 cursor: pointer;
 border: 1px solid transparent;
 border-radius: 4px;
 padding: 0px 18px;
 letter-spacing: -1px;
 font-size: 14px;
}
div.boardPwdChk{
 margin:10px;
 height:auto;
 display:none;
 
}
</style>
<script>

	$(function(){
	var json = ${requestScope.result};
	console.log(json);
	console.log("이상해");
	$(".prod_no").html(json.prod_no);
	$(".prod_name").html(json.prod_name);
	$(".prod_price").html(json.prod_price);
	$(".prod_detail").html(json.prod_detail);
	$(".cate_name").html(json.cate_name);
	var url = "${contextPath}/images/"+json.prod_no+".jpg";
	(".prod_img").attr("src",url);
	});
	
	
	$("button[type=submit]").click(function() {
        $.ajax({
           url : '${contextPath}/addcart',
           method : 'POST',
           data : {
              "prod_no" : $(".prod_no").html(),
              "prod_name" : $(".prod_name").html(),
              "prod_price" : $(".prod_price").html(),
              "quantity" : $(".quantity").val()
           },
          
           success : function(jsonObj) {
              var json = JSON.parse(jsonObj);
              if (json.status == 1) {
                 alert("장바구니에 성공적으로 담겼습니다.");
              } else {
                 alert("장바구니에 넣기에 실패했습니다.");
              }
           }
        });//end ajax
        console.log("quantity는" + $(".quantity").val());
        return false;
     });//end click
	

	
</script>


<div class="productDetail"> 
	<h1 style="margin-left:30px; margin-top:30px">상세정보</h1>
	<div class="prod_img"><img src=""></div><br>
	    <span class="cate_name"></span><br>
		<span class="prod_detail"></span><br>
		<span class="prod_name"></span><br>
		<label>상품번호</label><span class="prod_no"></span><br>
		<label>상품 가격</label><span class="prod_price"></span><br>
	<span><label>상품 수량</label><br><input class="quantity" type="text" name="count"></span>
	<br>


	<div class="cartdiv">
  		<button class=cartbtn type="submit">장바구니 넣기</button>
	</div> 
</div>