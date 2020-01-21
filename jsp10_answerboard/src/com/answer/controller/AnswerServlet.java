package com.answer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.answer.biz.AnswerBiz;
import com.answer.biz.AnswerBizImpl;

import com.answer.dto.AnswerDto;






/**
 * Servlet implementation class AnswerServlet
 */
@WebServlet("/answer.do")
public class AnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnswerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		String command = request.getParameter("command");
		System.out.println("<"+command+">");
		 AnswerBiz biz = new AnswerBizImpl();
		
		if(command.equals("list")) {
			
			List<AnswerDto> list = biz.selectList();
			request.setAttribute("list", list);
			dispatch("answerlist.jsp",request, response);
	}else if(command.equals("detail")) {
		int boardno = Integer.parseInt(request.getParameter("boardno"));
		AnswerDto dto = biz.selectOne(boardno);
		request.setAttribute("dto",dto);
		dispatch("answerdetail.jsp", request, response);
		
		response.getWriter().append("<h1><a href='answer.do?command=list'>잘못왔다</a></h1>");
		
		
	}else if(command.equals("writeform")) {
		
		response.sendRedirect("answerwrite.jsp");
	}else if(command.equals("writeres")) {
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");
		
		AnswerDto dto = new AnswerDto();
		
		dto.setTitle(title);
		dto.setContent(content);
		dto.setWriter(writer);
		
		
		//가져올 데이터가 있는지
		int res = biz.insert(dto);
		if(res>0) {
			//response.sendRedirect("mvc.do?command=list"); 이렇게 하면 정상적으로 작동한다.
			//dispatch("mvc.do?command=list", request, response); 이 경우 새로고침 하면 글 목록이 계속 만들어진다.
			
			PrintWriter out = response.getWriter();
			out.println("<script>alert('새로운 글을 등록 완료했다');");
			out.println("location.href='answer.do?command=list';");
			out.println("</script>");
					
			//jsResponse("글 등록 성공 ", "mvc.do?command=list", response);
		}else {
			//dispatch("mvc.do?command=writeform", request, response);
			PrintWriter out = response.getWriter();
			out.println("<script>alert('새로운 글을 등록 실패했다');");
			out.println("location.href='answer.do?command=writeform';");
			out.println("</script>");
			//jsResponse("글 등록 실패 ", "mvc.do?command=writeform", response);
		}
		
	}else if(command.equals("update")) {
		int boardno = Integer.parseInt(request.getParameter("boardno"));
		
		AnswerDto dto = biz.selectOne(boardno);
		request.setAttribute("dto", dto);
		dispatch("answerupdate.jsp", request, response);
		
	}else if(command.equals("updateres")) {
		
		String title = request.getParameter("title");
		String content= request.getParameter("content");
		int boardno= Integer.parseInt(request.getParameter("boardno"));
		AnswerDto dto = new AnswerDto();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setBoardno(boardno);
		
		int res = biz.update(dto);
		
		if(res>0) {
			//response.sendRedirect("mvc.do?command=list"); 이렇게 하면 정상적으로 작동한다.
			//dispatch("mvc.do?command=list", request, response); 이 경우 새로고침 하면 글 목록이 계속 만들어진다.
			
			PrintWriter out = response.getWriter();
			out.println("<script>alert('수정 완료했다');");
			out.println("location.href='answer.do?command=list';");
			out.println("</script>");
					
			//jsResponse("글 수정 성공 ", "con.do?command=list", response);
		}else {
			//dispatch("mvc.do?command=writeform", request, response);
			PrintWriter out = response.getWriter();
			out.println("<script>alert('수정 실패했다');");
			out.println("location.href='answer.do?command=list';");
			out.println("</script>");
			//jsResponse("글 수정 실패 ", "con.do?command=writeform", response);
		}
		
	}else if(command.equals("rewrite")) {
		
		
		int boardno = Integer.parseInt(request.getParameter("boardno"));
		
		AnswerDto dto = biz.selectOne(boardno);
		request.setAttribute("dto", dto);
		dispatch("answerrewrite.jsp", request, response);
	
	
	
	}else if(command.equals("rewriteres")) {
		
	int parentboardno = Integer.parseInt(request.getParameter("parentboardno"));
	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	AnswerDto dto = new AnswerDto();
	dto.setBoardno(parentboardno);
	dto.setWriter(writer);
	dto.setTitle(title);
	dto.setContent(content);
	int res = biz.answerProc(dto);
	if(res>0) {
		
		jsResponse("답변 작성 성공","answer.do?command=list",response);
	}else {
		jsResponse("답변 작성 실패","answer.do?command=answer&boardno="+parentboardno,response);
	}
	
		
		
	}else if(command.equals("delete")) {
		
		int boardno = Integer.parseInt(request.getParameter("boardno"));
		AnswerDto dto = new AnswerDto();
		int res = biz.delete(boardno);
		if(res>0){
			/*
			PrintWriter out = response.getWriter();
			out.println("<script>alert('삭제 완료했다');");
			out.println("location.href='mvc.do?command=list';");
			out.println("</script>");
			*/
			jsResponse("글 삭제 성공 ", "answer.do?command=list", response);
			
		}else {
			
			PrintWriter out = response.getWriter();
			out.println("<script>alert('삭제 실패했다');");
			out.println("location.href='boardno.do?command=detail';");
			out.println("</script>");
			
			//jsResponse("글 삭제 실패 ", "mvc.do?command=detail", response);
		}
		
	}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		doGet(request, response);
	}
	
private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"')");
		out.println("location.href='"+url+"'");
		out.println("</script>");
	}
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
		
	}

}
