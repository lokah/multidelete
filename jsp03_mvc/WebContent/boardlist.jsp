<%@page import="com.mvc.dto.MVCDto"%>
<%@page import="com.mvc.dao.MVCDao"%>
<%@page import="com.mvc.biz.MVCBiz"%>
<%@page import="com.mvc.biz.MVCBizimpl"%>
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


<%

List<MVCDto> list = (List<MVCDto>)request.getAttribute("list");


%>


<h1>글 목록</h1>

<form action="controller.jsp" method="post">
<input type="hidden" name="command" value="muldel">
<table border="1">
<col width ="30">
<col width ="50">
<col width ="100">
<col width ="300">
<col width ="100">


<tr>
<th><input type="checkbox" name="all" onclick="allChk(this.checked);"></th>
<th>번호</th>
<th>작성자</th>
<th>제목</th>
<th>작성일</th>
</tr>

<%

	if(list.size()==0){
		
		
%>


<tr>

<th colspan="5">......작성된 글이 없다....</th>
</tr>

 <%
	}else{
		
		for(MVCDto dto:list){
 %>

<tr>

<td><input type="checkbox" name="chk" value="<%=dto.getSeq()%>"> </td>
<td><%=dto.getSeq()%></td>
<td><%=dto.getWriter()%></td>
<td><a href="controller.jsp?command=detail&seq=<%=dto.getSeq()%>"><%=dto.getTitle()%></a></td>
<td><%=dto.getRegdate()%></td>
</tr>

<%

		}
	}
%>

<tr>

<td colspan="5">
<input type="submit" value="선택삭제">
<input type="button" value="글쓰기" onclick="location.href='controller.jsp?command=writeform';">
</td>

</tr>


</table>




</form>


</body>
</html>