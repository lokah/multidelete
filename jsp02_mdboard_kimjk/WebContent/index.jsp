<%@ page import="com.md.dto.MDDto" %>
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
<%
MDDto dto = new MDDto();

%>

<h1><a href = "BDlist.jsp?seq=<%=dto.getSeq()%>">BDlist</a></h1>


</body>
</html>