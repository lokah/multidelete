<%@page import="com.mvc.dto.MVCDto"%>
<%@page import="com.mvc.dao.MVCDao"%>
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
		
		<%
					}else{
				%>
		
		<script>
		
		alert("글 등록에 실패했다");
		location.href="controller.jsp?command=writeform";
		</script>
		
		
	<%
						}
						
					}else if(command.equals("muldel")){
						
						//1.
						
						String[] seqs = request.getParameterValues("chk");
						
						//2.
						boolean res = biz.multiDelete(seqs);
						
						//3.
						if(res){
					%>
		
		<script>
		alert("선택된 글들을 모두 삭제 완성");
		location.href="controller.jsp?command=list";
		
		</script>
		<%
			}else{
		%>
		<script>
		alert("선택된 글들을 삭제 실패했다");
		location.href="controller.jsp?command=list";
		
		</script>
		<%
			}
			
			
			
		}else if(command.equals("detail")){
			//int seq = Integer.parseInt(request.getParameter("seq"));
			int seq = Integer.parseInt(request.getParameter("seq"));
			MVCDto dto = biz.selectOne(seq);
			request.setAttribute("dto",dto);
			pageContext.forward("boarddetail.jsp");
			//response.sendRedirect("boarddetail.jsp?seq=dto.getSeq()");
			
			

			
		}else if(command.equals("delete")){
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			MVCDto dto = new MVCDto();
			int res = biz.delete(seq);
			if(res>0){
		%>
		<script>
		
		alert("글 삭제 성공")
		location.href =  "controller.jsp?command=list";
		</script>
		<%
			}else{
		%>
		
		<script>
		
		alert("글 삭제 실패")
		location.href = "controller.jsp?command=detaill&seq=<%=dto.getSeq()%>";
		</script>
		
		<%
					}
					
				}else if(command.equals("updateform")){
					
					int seq = Integer.parseInt(request.getParameter("seq"));
					
					MVCDto dto = biz.selectOne(seq);
					request.setAttribute("dto", dto);
					pageContext.forward("boarddetail.jsp");
						
						
						
					}else if(command.equals("updateres")){
						
						String title = request.getParameter("title");
						String content= request.getParameter("content");
						int seq= Integer.parseInt(request.getParameter("seq"));
						MVCDto dto = new MVCDto();
						dto.setTitle(title);
						dto.setContent(content);
						dto.setSeq(seq);
						
						int res = biz.update(dto);
						
						if(res>0){
				%>
		
	<script>
		
		alert("글 수정 성공")
		location.href = "controller.jsp?command=detaill&seq=<%=dto.getSeq()%>";
		</script>
		
	<%
		
	}else {
		%>
		
	<script>
		
		alert("글 삭제 실패")
		location.href = "controller.jsp?comand=updateform&seq=<%=dto.getSeq()%>";
	</script>
		
<%
	}
}

%>


//서버에 요청할 때 리퀘스트 객체에 담아 보낸다.
//a-b-c ...b가 c로 넘겨주면서 추가로 데이터를 넣어 보낸다.(attribute)
//mvc리스트가 콘트롤러에 요청
//sendredirect 는 서버 밖에서 데이터의 교환이 이루어진다.
//포워드는 서버가 모른다.
//sendredirect는 서버가 안다.



<h1>잘못왔다.(이게 보이면 command 확인)</h1>

</body>
</html>