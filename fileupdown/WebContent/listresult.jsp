<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"
		  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listresult.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
/* $(function(){
	$("a").click(function(){
		var url = "./down";
		var data = "fileName=" + $(this).html().trim();
		// 파일 다운로드 할때에는 ajax 요청을 하면 안된다.
		// 다운로드 한 내용을 무시해버리기 때문
		/* $.ajax({
			url: url,
			data: data,
			success: function(data){
				alert("download OK");
			}
		}); */
	});
}); */
</script>
</head>
<body>
<h2>File List</h2>
<c:forEach var="i" items="${requestScope.list}">
  <a href="./down?fileName=${i}">${i}</a><br>
</c:forEach>
</body>
</html>