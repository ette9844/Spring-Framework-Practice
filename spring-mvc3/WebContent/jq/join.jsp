<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../contextPath.jsp"%>
<style>
input {
	margin-top: 10px;
	width: 99%;
	min-width: 200px;
	height: 30px;
	padding-left: 5px;
}
input[type=number]{
	width: 10%;
}
input[type=submit]{
	display: none;
}
input[readonly]{
	background-color: #efefef;
}
button, input[type=reset], input[type=submit]{
	cursor: pointer;
	height: 40px;
	color: #0033cc;
    background-color: #fff;
    border-color: #0033cc;
    min-width: 100px;
    margin: 0 0 0 0;
    text-align: center;
    vertical-align: middle;
    touch-action: manipulation;
    white-space: nowrap;
    font-size: 14px;
    border-radius: 4px;
}
.final input{
	max-width: 40%;
}
.final {
	text-align: center;
	width: 100%;
	padding-top: 30px;
	
}
span+span{
	float:right; 
	font-size: 12px;
}
#passChk{
	color: red;
	display: none;
}
/* 우편번호 창 */
#divSearchZip{
	width: 500px; 
	height: 400px; 
	position: absolute;
	top: 100px; 
	left: 150px; 
	border: 1px solid;
	background-color: #f5f5f5;
	display: none;
}
#divSearchZip>div{
	width: 100%; 
	height: 400px; 
	overflow: auto;
}
#divSearchZip>div>table>tbody>tr>td:nth-child(3) {
	display: none;
}
.search_pop input[type=text] {
    width: 80%;
    height: 36px;
    font-size: 14px;
    line-height: 16px;
    padding: 8px;
    margin: 0px;
    border: 2px solid #0033cc;
    box-sizing: border-box;
    float: left;
}
.search_pop button {
    width: 20%;
    height: 36px;
    background-color: #0033cc;
    font-size: 14px;
    color: #fff;
    font-weight: bold;
    text-align: center;
    line-height: 32px;
    display: block;
    padding: 0px;
    margin: 0px;
    border: 0px;
    border: 2px solid #0033cc;
    box-sizing: border-box;
    float: left;
}
table{
    width: 100%;
    padding: 0px;
    margin: 0px 0px 20px 0px;
    border-top: 2px solid #888;
    border-collapse: separate;
    border-spacing: 0;
}
table tr{
    background-color: #fbfbfb;
    margin: 0px;
    border-bottom: 1px solid #e0e0e0;
}
table tr th, td{
    background-position: right top;
    background-repeat: no-repeat;
    font-size: 12px;
    color: #656565;
    line-height: 24px;
    padding: 8px 0px 8px 0px;
    margin: 0px;
    border-bottom: 1px solid #e0e0e0;
    text-align: center;
}
.full{
	max-width: 800px;
	margin: 30px 30px 30px 30px;
	padding-right: 30px;
}
</style>
<script>
$(function(){
	var $idObj = $("input[name=id]");
	var $submitObj = $("input[type=submit]");
	
	// id 입력란에 focus이벤트 감시: focus되엇을 시 submit 버튼 숨기기
	$idObj.focus(function(){
		$submitObj.hide();
	});	
	
	var $dupChkObj = $("#dupChkId");
	// 중복확인 버튼 클릭리스너
	$dupChkObj.click(function(){
		var status = "-1";
		var id = $idObj.val();
		
		if(id.trim().length == 0){
			alert("ID를 입력해주세요.");
			// focus 이동
			$idObj.focus();
		} else if(id.trim().length < 4){
			alert("4자 이상의 영문 소문자로 id를 구성해주세요.")
		} else {
			$.ajax({
				url: '${contextPath}/dupchk',
				method: 'POST',
				data: 'id='+id,
				success: function(data){
					//console.log(data);
					var jsonObj = JSON.parse(data);
					//console.log(jsonObj.status);
					status = jsonObj.status;
					
					if(status == "1"){
						//중복된 id가 있을 때
						alert("이미 사용중인 ID 입니다.");
					} else {
						// 중복된 id가 없을 때
						var $submitObj = $("input[type=submit]");
						//$submitObj.css('display', 'inline');
						$submitObj.show();
					}
				}
			});
		}
		
	});
	
	var $postNumObj = $("#searchZip");
	var $postDiv = $("#divSearchZip");
	// 우편 번호 찾기 버튼 클릭리스너
	$postNumObj.click(function(){
		// 우편 번호창 보여주기
		$postDiv.show();
	});
	
	// 우편 번호창에서 행(tr) 클릭리스너 첫 행 제외=tr:not(:nth-child(1))
	var $trObj = $("#divSearchZip>div>table tr:not(:first-child)");
	var $postObj = $("input[name=postNum]");
	var $addr1Obj = $("input[name=addr1]");
	var $buildingno = $("input[type=hidden]");
	
	//#divSearchZip의 하위요소로 form객체 찾기
	$("#divSearchZip form").submit(function(){
		$.ajax({
			url: '${contextPath}/searchzip',
			method: 'POST',
			data: $("#divSearchZip form").serialize(),
			success: function(data){
				var trs = "";
				//console.log(data);
				
				var jsonObjArr = JSON.parse(data);
				for(var i=0; i<jsonObjArr.length; i++){
					//console.log(jsonObjArr[i].zipcode + ", " + jsonObjArr[i].addr1 + ", " + jsonObjArr[i].addr2);
					trs += "<tr><td>"+jsonObjArr[i].zipcode
						+ "</td><td>"+jsonObjArr[i].addr1
						+ "<br>"+jsonObjArr[i].addr2
						+ "</td><td>"+jsonObjArr[i].buildingno+"</td></tr>";
					$("#divSearchZip>div>table>tbody").html(trs);
				}
			}
		});
		return false;
	});
	
	// 동적으로 추가 된 tr에도 클릭 이벤트를 등록하기 위해 on함수 사용
	$("#divSearchZip>div>table>tbody").on("click", "tr", function(){
		var children = $(this).children();
		//console.log(children);
		var postnum = children.eq(0).html();
		var addrs = children.eq(1).html();
		var addrArr = addrs.split('<br>');
		var addr1 = addrArr[0];
		var buildingno = children.eq(2).html();
		
		// 클릭한 요소의 값을 메인 페이지에 반영
		//console.log(postnum + " : " + addr1);
		$postObj.val(postnum);
		$addr1Obj.val(addr1);
		$buildingno.val(buildingno);
		
		// 우편 번호창 안보이게 하기
		$postDiv.hide();
	});
	
	
	// 취소 버튼 클릭리스너
	var $resetObj = $("input[type=reset]");
	$resetObj.click(function(){
		// input value 모두 클리어
		var $inputObj = $("input");
		if($inputObj.val() != "취소" && $inputObj.val() != "가입완료") {
			$inputObj.val("");
		}
	});
	
	//console.log($("form:nth-child(1)").serialize());
	// 가입완료 버튼 클릭리스너
	$submitObj.click(function(){
		$.ajax({
			url: '${contextPath}/join',
			method: 'POST',
			data: $("form:nth-child(1)").serialize(),
			success: function(data){
				var msg = "";
				//console.log(data);
				
				var jsonObj = JSON.parse(data);
				if(jsonObj.status == 1) {
					msg = "가입 성공";
					alert(msg);
					location.href = "layout.jsp";
				} else {
					msg = "가입 실패";
					alert(msg);
				}
			}
		});
		return false;
	});
});
</script>
<div class="full">
<form>
<div>
  <span>아이디 입력</span>
  <span>4자 이상의 영문 소문자 기준 0/15자</span><br>
  <input type="text" name="id" placeholder="아이디를 입력하세요." style="width:77%;">
  <button type="button" id="dupChkId" style="width:20%; float: right;margin-top: 8px;">중복확인</button><br><br>
</div>
<div>
  <span>비밀번호</span>
  <span>영문소문자, 숫자 포함 6자리-12자리</span><br>
  <input type="password" name="pwd" placeholder="사용할 비밀번호를 입력하세요."><br><br>
  <span>비밀번호 확인</span><span id="passChk">비밀번호가 일치하지 않습니다</span><br>
  <input type="password" name="passChk" placeholder="사용할 비밀번호를 입력하세요."><br><br>
</div>
<div>
  <span>이름</span><br>
  <input type="text" name="name" placeholder="이름을 입력하세요."><br><br>
</div>
<div class="address">
    주소<br>
  <input type="number" name="postNum" readonly>
  <!-- form태그 내 submit기능을 하지 않는 button을 만들기 위해서는 type="button"으로 선언해줘야 함 -->
  <button type="button" id="searchZip">우편번호 검색</button><br>
  <input type="text" name="addr1" readonly><br>
  <input type="text" name="addr2" placeholder="상세 주소를 입력하세요."><br><br>
  <input type="hidden" name="buildingno">
</div>
<div class="final">
<input type="reset" value="취소">
<input type="submit" value="가입완료">
</div>
</form>
<div id="divSearchZip">
  <form>
    <div class="search_pop">
      <input name="doro" type="text" placeholder="도로명 + 건물번호">
      <button type="submit">검색</button>
    </div>
  </form>
  <div style="width: 100%; height: 90%; overflow: auto;">
  	<table>
  	<thead>
  	<tr><th>우편번호</th><th>주소</th></tr>
  	</thead>
  	<tbody>
  	<tr><td>30114</td><td>세종특별자치시 도움5로 19 (어진동, 우정사업본부)</td></tr>
  	<tr><td>30115</td><td>대전광역시 유성구 유성온천역 유성12로 29</td></tr>
  	<tr><td>30114</td><td>세종특별자치시 도움5로 19 (어진동, 우정사업본부)</td></tr>
  	<tr><td>30115</td><td>대전광역시 유성구 유성온천역 유성12로 29</td></tr>
  	<tr><td>30114</td><td>세종특별자치시 도움5로 19 (어진동, 우정사업본부)</td></tr>
  	<tr><td>30115</td><td>대전광역시 유성구 유성온천역 유성12로 29</td></tr>
  	<tr><td>30114</td><td>세종특별자치시 도움5로 19 (어진동, 우정사업본부)</td></tr>
  	<tr><td>30115</td><td>대전광역시 유성구 유성온천역 유성12로 29</td></tr>
  	<tr><td>30114</td><td>세종특별자치시 도움5로 19 (어진동, 우정사업본부)</td></tr>
  	<tr><td>30115</td><td>대전광역시 유성구 유성온천역 유성12로 29</td></tr>
  	<tr><td>30114</td><td>세종특별자치시 도움5로 19 (어진동, 우정사업본부)</td></tr>
  	<tr><td>30115</td><td>대전광역시 유성구 유성온천역 유성12로 29</td></tr>
  	<tr><td>30114</td><td>세종특별자치시 도움5로 19 (어진동, 우정사업본부)</td></tr>
  	<tr><td>30115</td><td>대전광역시 유성구 유성온천역 유성12로 29</td></tr>
  	</tbody>
  	</table>
  </div>
</div>
</div>
