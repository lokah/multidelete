<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%@ include file="./form/header.jsp" %>

<h1>글 쓰기</h1>

<form action="myinsertres.jsp" method="post">
<table border="1">

<tr>

<th>이름</th>
<td><input type="text" name="writer"></td>

</tr>

<tr>
<th>제목</th>
<td><input type="text" name="title"></td>

</tr>

<tr>
<th>내용</th>
<td><textarea rows="10" cols="60" name="content"></textarea>
</td>
</tr>



<tr>

<td colspan="2" align="right">
<input type="submit" value ='작성'>
<input type="button" onclick="location.href='mylist.jsp'" value='취소'>
</td>
</tr>
</table>

</form>


<%@ include file="./form/footer.jsp" %>

</body>
</html>