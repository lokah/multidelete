<%@page import="com.md.dto.MDDto"%>
<%@page import="com.md.dao.MDDao"%>
<%@page import="java.util.*"%>
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

<h1>글쓰기</h1>
<form action="BDinsertres.jsp" method="post">
<table>

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
<td><input type="text" name="content"></td>

</tr>



</table>
</form>

</body>
</html>