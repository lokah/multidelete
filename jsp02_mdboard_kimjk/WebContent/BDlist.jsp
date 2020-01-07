<%@page import="com.md.dto.MDDto"%>
<%@page import="com.md.dao.MDDao"%>
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

<style type="text/css">

#bb{
border:2px solid orange;
color : red;
}
#bd{
text-decoration:none;
}

</style>

<script>

function writeForm(){
	
	location.href="BDinsert.jsp";
	
}

</script>

</head>
<body>

	<%
		//MDDto dto = new MDDto(); 

		MDDao dao = new MDDao();
		List<MDDto> list = dao.selectList();
	%>
	<form action="./muldel.jsp" method="post">
		<table id="bb" border="1">
			<col width="30px">
			<col width="50px">
			<col width="100px">
			<col width="300px">
			<col width="100px">


			<tr>
				<th><input type="checkbox" name="all"
					onclick="allChk(this.checked);"></th>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>작성일</th>
			</tr>

			<%
				if (list.size() == 0) {
			%>

			<tr>
				<td colspan="5">.............작성된 글이 존재하지 않습니다............</td>
			</tr>

			<%
				} else {
					for (MDDto dto : list) {
			%>

			<tr>

				<td><input type="checkbox" name="chk" value="<%=dto.getSeq()%>"></td>
				<td><%=dto.getSeq()%></td>
				<td><a id="bd" href="#"><%=dto.getWriter()%></a></td>
				<td><a id="bd" href="BDdetail.jsp?seq=<%=dto.getSeq() %>"><%=dto.getTitle()%></a></td>
				<td><%=dto.getRegdate()%></td>


			</tr>

			<%
				}
				}
			%>
		<tr>
		
		<td colspan="5">
		<input type="submit" value="선택삭제">
		<input type="button" value="글쓰기" onclick="writeForm();">
		</td>
		
		</tr>

		</table>
	</form>

</body>
</html>