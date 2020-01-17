<%@page import="com.mvc.dto.MVCDto"%>
<%@page import="com.mvc.dao.MVCDao"%>
<%@page import="com.mvc.dao.MVCDaoimpl"%>
<%@page import="com.mvc.biz.MVCBiz"%>
<%@page import="com.mvc.biz.MVCBizimpl"%>
<%@page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	//int seq = Integer.parseInt(request.getParameter("seq"));
//MVCDao dao = new MVCDaoimpl();
MVCDto dto = (MVCDto) request.getAttribute("dto");
//dao.selectOne(seq);
%>

<h1>Detail</h1>
<table border="1">
<tr>
<th>작성자</th>
<td><%=dto.getWriter() %></td>
</tr>
<tr>
<th>제목</th>
<td><%=dto.getTitle() %></td>
</tr>
<tr>
<th>내용</th>
<td><textarea rows="10" cols="60" readonly="readonly"><%=dto.getContent() %></textarea>
</td>
</tr>

<tr>
<td colspan="2" align="right">
<input type="button" value='수정' onclick="location.href='controller.jsp?command=updateform&seq=<%=dto.getSeq()%>'"> 
<input type="button" value='삭제' onclick="location.href='controller.jsp?command=delete&seq=<%=dto.getSeq()%>'">
 <!-- 쿼리 스트링 -->
<input type="button" value='목록' onclick="location.href='controller.jsp?command=list'">
</td>
</tr>
</table>


</body>
</html>