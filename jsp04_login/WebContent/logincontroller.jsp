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
</head>
<body>

<%

	String command = request.getParameter("command");
	System.out.println("["+command+"]");
	
	MyBiz biz = new MyBizImpl();
	
	if(command.equals("login")){
		
		String myid = request.getParameter("id");
		String mypw = request.getParameter("pw");
		
		MyDto dto = biz.login(myid, mypw);
		if(dto!=null){
			//session :만료되기 전까지 어플리케이션 전체에서 사용 가능
			session.setAttribute("dto", dto);
			
			//초 만큼 활동이 없으면 세션을 invalidate 한다.
			//default : 30분/ 음수이면 무제한
			session.setMaxInactiveInterval(10*60);
			
			if(dto.getMyrole().equals("ADMIN")){
				
				response.sendRedirect("adminmain.jsp");
			}else if(dto.getMyrole().equals("USER")){
				
				response.sendRedirect("usermain.jsp");
			}
		}else {
			
			%>
			
			<script>
			
			alert("id와 pw를 다시 한번 확인해 주세요")
			location.href="index.jsp";
			
			</script>
			
			
<% 
		}
	}else if(command.equals("logout")){
		//만료시킨다.세션의 값만 없어진다.
		session.invalidate();
		response.sendRedirect("index.jsp");
	}else if(command.equals("selectlist")){
		//da에서 유저 정보 모두 가져와라
		//담아라
		List<MyDto> list = biz.selectList();
		request.setAttribute("list", list);
		pageContext.forward("userselectlist.jsp");//보내라
		
	}else if(command.equals("selectenabled")){
		
		List<MyDto> list = biz.selectEnabled();
		request.setAttribute("list", list);
		//userselectenabled.jsp로 보내라
		pageContext.forward("userselectenabled.jsp");
	}else if(command.equals("updateroleform")){
		
		int myno = Integer.parseInt(request.getParameter("myno"));
		
		MyDto dto = biz.selectUser(myno);
		request.setAttribute("select", dto);
		pageContext.forward("updaterole.jsp");
	}else if(command.equals("updateroleres")){
		int myno = Integer.parseInt(request.getParameter("myno"));
		String role = request.getParameter("role");
		
		
		int res = biz.updateRole(myno, role);
		
		if(res>0){
			%>
			
			<script>
			
			alert("등급 조정 성공");
			location.href = "adminmain.jsp";
			
			</script>
			<% 
		}else{
			
			%>
			<script>
			
			alert("등급 조정 실패");
			location.href = "logincontroller.jsp?command=updateroleform&myno=<%=myno%>";
			
			</script>
			<% 
		}
	}
%>







</body>
</html>