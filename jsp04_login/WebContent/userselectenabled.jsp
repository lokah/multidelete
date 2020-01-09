<%@page import="com.login.dto.MyDto"%>
<%@page import="com.login.dao.MyDao"%>
<%@page import="com.login.biz.MyBiz"%>
<%@page import="com.login.biz.MyBizImpl"%>
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

<script>

function updateRole(myno){
	
	location.href = "logincontroller.jsp?command=updateroleform&myno="+myno;
}

</script>
</head>
<body>


<%


List<MyDto> list = (List<MyDto>)request.getAttribute("list");
%>

<h1>회원정보 조회(Enabled)</h1>

<table border="1">
<tr>

<th>번호</th>
<th>아이디</th>
<th>비밀번호</th>
<th>이름</th>
<th>주소</th>
<th>전화번호</th>
<th>이메일</th>
<th>가입여부</th>
<th>등급</th>
<th>등급변경</th>

</tr>

<%

for(MyDto dto : list){
	
%>
	
	<tr>
	
	<td><%=dto.getMyno() %></td>
	<td><%=dto.getMyid() %></td>
	<td><%=dto.getMypw() %></td>
	<td><%=dto.getMyname() %></td>
	<td><%=dto.getMyaddr() %></td>
	<td><%=dto.getMyphone() %></td>
	<td><%=dto.getMyemail() %></td>
	<td><%=dto.getMyenabled().equals("Y")?"가입":"탈퇴" %></td>
	<td><%=dto.getMyrole() %></td>
	<td><button onclick="updateRole(<%=dto.getMyno()%>)">변경</button></td>
	
	</tr>
	<%
	

}
%>

<tr>
<td colspan="10">
<button onclick="">메인</button>
</td>
</tr>
</table>
</body>
</html>