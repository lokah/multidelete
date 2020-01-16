<%@page import="com.mvc.dto.MyDto"%>
<%@page import="com.mvc.dao.MyDao"%>
<%@page import="com.mvc.dao.MyDaoImpl"%>
<%@page import="com.mvc.biz.MyBiz"%>
<%@page import="com.mvc.biz.MyBizImpl"%>
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

	<jsp:useBean id="dto" class="com.mvc.dto.MyDto" scope="request"></jsp:useBean>
	<h1>상세 글 보기</h1>
	<table border="1">

		<tr>
			<th>번호</th>
			<td><jsp:getProperty property="seq" name="dto"/></td>


		</tr>
		<tr>
			<th>작성자</th>
			<td><jsp:getProperty property="writer" name="dto"/></td>


		</tr>

		<tr>
			<th>제목</th>
			<td><jsp:getProperty property="title" name="dto"/></td>


		</tr>

		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60" readonly="readonly"><jsp:getProperty
						property="content" name="dto"/></textarea></td>


		</tr>

		<tr>

			<td colspan="2"><input type="button" value="수정" onclick="location.href='command=myupdate&seq=${dto.seq}'">
				<input type="button" value="삭제" onclick=""> <input
				type="button" value="목록" onclick=""></td>
		</tr>
	</table>

</body>
</html>