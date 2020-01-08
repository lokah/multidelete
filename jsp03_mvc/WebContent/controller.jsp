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

String command = request.getParameter("command");
System.out.println("<"+command+">");

MVCBiz biz = new MVCBizimpl();

if(command.equals("list")){
	//1.받을 데이터 없다.
	//2.
	
	List<MVCDto> list = biz.selectList();
	request.setAttribute("list", list);
	//3.
	pageContext.forward("boardlist.jsp");
}else if(command.equals("writeform")){
	
	//1.받을 데이터가 있는지? 없고
			//2. db에서 가져올 데이터가 있는지? 없고
					//3.어디로 갈런지?
		response.sendRedirect("boardwrite.jsp");
}else if(command.equals("writeres")){
	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	MVCDto dto = new MVCDto();
	
	dto.setWriter(writer);
	dto.setTitle(title);
	dto.setContent(content);
	
	//가져올 데이터가 있는지
	int res = biz.insert(dto);
	//3. 어디로 갈건지
	if(res>0){
		%>
		<script>
		
		alert("새로운 글을 등록 완료했다");
		location.href="controller.jsp?command=list";
		</script>
		
		<%}else{ %>
		
		<script>
		
		alert("글 등록에 실패했다");
		location.href="controller.jsp?command=writeform";
		</script>
		
		
	<%
	
		
	}
	
}

%>

<h1>잘못왔다.(이게 보이면 command 확인)</h1>

</body>
</html>