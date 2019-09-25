<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="../contextPath.jsp"%>
<style>
div{
  background-color: #F7F7F7;
  border: 1px solid;
  padding: 20px;
  width: auto;
  height: auto;
}
div>select{
  cursor: pointer;	/*커서를 올렸을 때 손가락 버튼으로*/
  margin: 10px;
}
button{
  color: #fff;
  background-color: #1553A3;
  border-radius: 4px;	/*둥근 모서리*/
  display: inline-block;
  margin-bottom: 0;
  font-weight: normal;
  text-align: center;
  vertical-align: middle;	/*수직정렬*/
  touch-action: manipulation;
  cursor: pointer;
  border: 1px solid transparent;
  height: 44px;
  letter-spacing: -1px;
  font-weight: 700;
  white-space: nowrap;
  padding: 0px 18px;
  font-size: 14px;
  line-height: 42px;
}
.icon-search {
  display: inline-block;
  width: 16px;
  height: 16px;
  background-image: url(../images/icon-search.png);	/*외부 이미지를 배경 이미지로 사용*/
  background-size: 16px;
  background-repeat: no-repeat;
  vertical-align: middle;
  margin-top: -1px;
}
.mr-3 {
  /*!important: 절대 다른 값에 의해서 변경되지 않는다. 덮어쓰기 불가.*/
  margin-right: 3px !important;
}

/*처음에는 세부옵션이 보이지 않게 CSS 설정*/
input[type=checkbox] ~ div {
	display: none;
}
</style>
<script>
$(function(){
	// dom 에서 id 속성이 sido인 객체 찾기
	var $sidoObj = $("#sido");
	var $gunguObj = $("#gungu");
	
	// sido 객체의 event 종류: click, change
	$sidoObj.change(function(){
		$gunguObj.empty();
		$gunguObj.append('<option>시/군/구 선택</option>');
		
		var options = '';
		// 다양한 방법으로 html 추가
		switch($sidoObj.val()){
		case 's':	// JS 함수로 추가하는 것보다 훨씬 간편
			options += '<option>강남구</option>';
			options += '<option>강북구</option>';
			options += '<option>강서구</option>';
			options += '<option>강동구</option>';
			$gunguObj.html($gunguObj.html() + options);
			break;
		case 'k':
			$gunguObj.append('<option>수원시</option>');
			$gunguObj.append('<option>시흥시</option>');
			$gunguObj.append('<option>안산시</option>');
			break;
		case 'j':
			// $(꺽새없음): 요소를 찾는다
			// $(<꺽새로 둘러쌈>): 새로운 요소를 만든다 
			var o = $('<option>');
			o.html('서귀포시');
			$gunguObj.append(o);
			
			o = $('<option>제주시</option>');
			$gunguObj.append(o);
			break;
		}
	});
	
	// button, 객체 찾기
	var $btObj = $("button");
	
	$btObj.click(function(){
		// 시도값이 k이고, 군구값이 시흥시인 경우
		var $resultObj = $(".result");
		if($sidoObj.val() == 'k' && $gunguObj.val() == '시흥시') {
			// id 속성값이 result인 div 객체 영역에
			// 그린웨이 자전거길을 출력하시오
			$resultObj.html("그린웨이 자전거길");
		}
	});
	
	// each 함수로 수정
	// 1. '전체' checkbox 찾기
	var $ckAllObj;
	var $ckArr = $("input[type=checkbox]");
	
	// $ckArr.each(function(ik, ckObj){ 와 동일
	$ckArr.each(function(i, ckObj){	
	    console.log("$.each() i=" + i); //0
		var $sibling = $(ckObj).next();
		if($sibling.html().trim()=='전체'){
			$ckAllObj = $(ckObj);
			return false;	// 반복문이 아니기 때문에 break가 아닌 콜백함수를 빠져나오는 return
		}
	});
	
	// 전체 체크박스 이벤트 핸들러
	$ckAllObj.click(function(){
		for(var i=0; i<$ckArr.length; i++){
			if($($ckArr[i]).prop("checked") != $ckAllObj.prop("checked")) {
				$($ckArr[i]).click();
			}
		}
	});
	
	// 나머지 체크박스 이벤트 핸들러
	$ckArr.click(function(){
		// dom에서 옵션창 객체 가져오기
		var $option = $(this).next().next("div");
		// 체크 되었을 시에는 옵션 창  표시
		if($(this).prop("checked")){ 
			$option.show();
		}
		else{
			$option.hide();
		}
	});
});
</script>

<div>
  <div>
    지역 :
  <select id="sido">
    <option value="0">도/시 선택</option>
    <option value="s">서울</option>
    <option value="k">경기</option>
    <option value="j">제주</option>
  </select>
  <!-- 첫번째 select의 선택값에 따라 두번째 select 박스의 선택값이 동적으로 바뀌도록 -->
  <select id="gungu">
    <option>시/군/구 선택</option>
  </select>
</div>
<div>
     조건:
  <!-- </input> 닫힘 태그가 없어서 '전체'를 찾기 위해서는 innerHTML도 .value도 아닌 -->
  <!-- <span></span>태그를 문자열에 추가하고 span태그를 통해 찾아간다 -->
  <input type="checkbox"><span>전체</span>  <!-- 모두 선택 -->
  <hr>
  <input type="checkbox"><span>거리</span>&nbsp;&nbsp;
  <div>
    <input type="radio" name="opt1">5km미만&nbsp;&nbsp;
    <input type="radio" name="opt1">5~10km&nbsp;&nbsp;
    <input type="radio" name="opt1">10km초과
  </div>
  <input type="checkbox"><span>난이도</span>&nbsp;&nbsp;
  <div>
    <input type="radio" name="opt1">쉬움&nbsp;&nbsp;
    <input type="radio" name="opt1">보통&nbsp;&nbsp;
    <input type="radio" name="opt1">어려움
  </div>
  <input type="checkbox"><span>소요시간</span>&nbsp;&nbsp;
  <div>
    <input type="radio" name="opt1">1시간 미만&nbsp;&nbsp;
    <input type="radio" name="opt1">3~5시간&nbsp;&nbsp;
    <input type="radio" name="opt1">5시간 이상
  </div>
</div>
<button>
<!-- 공백을 두어서 여러 선택자의 스타일을 적용할 수 있다 -->
<!-- icon-search, mr-3 스타일이 모두 적용 -->
<i class="icon-search mr-3" style="margin-right: 10px;"></i> 
<!-- icon을 보여주기 좋은 semantic tag: 원래 의미는 italic체로 보여주기 -->
검색하기</button>
</div>
<div class="result">
</div>