package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.biz.MyBiz;
import com.mvc.biz.MyBizImpl;
import com.mvc.dto.MyDto;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/con.do")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String command = request.getParameter("command");
		System.out.println("<"+command+">");
		MyBiz biz = new MyBizImpl();
		
		if(command.equals("list")) {
			
			List<MyDto> list = biz.selectList();
			request.setAttribute("list", list);
			dispatch("mylist.jsp",request, response);
		}else if(command.equals("detail")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			MyDto dto = biz.selectOne(seq);
			request.setAttribute("dto",dto);
			dispatch("mydetail.jsp", request, response);
			
			response.getWriter().append("<h1><a href='con.do?command=list'>잘못왔다</a></h1>");
			
		}else if(command.equals("writeform")) {
			
			response.sendRedirect("mywrite.jsp");
		}else if(command.equals("mywriteres")) {
			
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			MyDto dto = new MyDto();
			
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			
			//가져올 데이터가 있는지
			int res = biz.insert(dto);
			if(res>0) {
				//response.sendRedirect("mvc.do?command=list"); 이렇게 하면 정상적으로 작동한다.
				//dispatch("mvc.do?command=list", request, response); 이 경우 새로고침 하면 글 목록이 계속 만들어진다.
				
				PrintWriter out = response.getWriter();
				out.println("<script>alert('새로운 글을 등록 완료했다');");
				out.println("location.href='con.do?command=list';");
				out.println("</script>");
						
				//jsResponse("글 등록 성공 ", "mvc.do?command=list", response);
			}else {
				//dispatch("mvc.do?command=writeform", request, response);
				PrintWriter out = response.getWriter();
				out.println("<script>alert('새로운 글을 등록 실패했다');");
				out.println("location.href='con.do?command=writeform';");
				out.println("</script>");
				//jsResponse("글 등록 실패 ", "mvc.do?command=writeform", response);
			}
			
		}else if(command.equals("myupdate")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			MyDto dto = biz.selectOne(seq);
			request.setAttribute("dto", dto);
			dispatch("myupdate.jsp", request, response);
			
		}else if(command.equals("myupdateres")) {
			
			String title = request.getParameter("title");
			String content= request.getParameter("content");
			int seq= Integer.parseInt(request.getParameter("seq"));
			MyDto dto = new MyDto();
			dto.setTitle(title);
			dto.setContent(content);
			dto.setSeq(seq);
			
			int res = biz.update(dto);
			
			if(res>0) {
				//response.sendRedirect("mvc.do?command=list"); 이렇게 하면 정상적으로 작동한다.
				//dispatch("mvc.do?command=list", request, response); 이 경우 새로고침 하면 글 목록이 계속 만들어진다.
				
				PrintWriter out = response.getWriter();
				out.println("<script>alert('수정 완료했다');");
				out.println("location.href='con.do?command=detail&seq="+dto.getSeq()+"';");
				out.println("</script>");
						
				//jsResponse("글 수정 성공 ", "con.do?command=list", response);
			}else {
				//dispatch("mvc.do?command=writeform", request, response);
				PrintWriter out = response.getWriter();
				out.println("<script>alert('수정 실패했다');");
				out.println("location.href='con.do?command=list';");
				out.println("</script>");
				//jsResponse("글 수정 실패 ", "con.do?command=writeform", response);
			}
			
		}else if(command.equals("delete")) {
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			MyDto dto = new MyDto();
			int res = biz.delete(seq);
			if(res>0){
				/*
				PrintWriter out = response.getWriter();
				out.println("<script>alert('삭제 완료했다');");
				out.println("location.href='mvc.do?command=list';");
				out.println("</script>");
				*/
				jsResponse("글 삭제 성공 ", "con.do?command=list", response);
				
			}else {
				
				PrintWriter out = response.getWriter();
				out.println("<script>alert('삭제 실패했다');");
				out.println("location.href='con.do?command=detail';");
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
