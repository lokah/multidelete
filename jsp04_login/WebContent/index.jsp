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

<form action="logincontroller.jsp" method="post">

<input type="hidden" name="command" value="login">
<table border="1">

<col width ="100">
<col width ="100">
<tr>

<th>ID</th>
<td><input type="text" name="id"></td>

</tr>
<tr>
<th>pw</th>
<td><input type="password" name="pw"></td>

</tr>

<tr>

<td colspan="2">
<input type="submit" value="login">
<input type="button" value="regist" onclick="">
</td>

</tr>

</table>

</form>

</body>
</html>