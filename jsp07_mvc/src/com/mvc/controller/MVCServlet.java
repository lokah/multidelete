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

import com.mvc.biz.MVCBiz;
import com.mvc.biz.MVCBizImpl;
import com.mvc.dto.MVCDto;

/**
 * Servlet implementation class MVCServlet
 */
@WebServlet("/MVCServlet")
public class MVCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MVCServlet() {
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
		System.out.printf("<%s>\n", command);
		
		MVCBiz biz = new MVCBizImpl();
		
		if(command.equals("list")) {
			
			List<MVCDto> list = biz.selectList();
			request.setAttribute("list", list);
			dispatch("mvclist.jsp", request, response);
			//response.sendRedirect("mvclist.jsp");
		}else if(command.equals("writeform")) {
			
			//dispatch("mvcwrite.jsp", request, response);
			
			response.sendRedirect("mvcwrite.jsp");
			
			
		}else if(command.equals("mvcwriteres")) {
			
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			MVCDto dto = new MVCDto();
			
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
				out.println("location.href='mvc.do?command=list';");
				out.println("</script>");
						
				//jsResponse("글 등록 성공 ", "mvc.do?command=list", response);
			}else {
				//dispatch("mvc.do?command=writeform", request, response);
				PrintWriter out = response.getWriter();
				out.println("<script>alert('새로운 글을 등록 실패했다');");
				out.println("location.href='mvc.do?command=writeform';");
				out.println("</script>");
				//jsResponse("글 등록 실패 ", "mvc.do?command=writeform", response);
			}
			
	}else if(command.equals("detail")) {
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		MVCDto dto = biz.selectOne(seq);
		request.setAttribute("dto",dto);
		dispatch("mvcdetail.jsp", request, response);
		
	}else if(command.equals("updateform")) {
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		MVCDto dto = biz.selectOne(seq);
		request.setAttribute("dto", dto);
		dispatch("mvcupdate.jsp", request, response);
		
		
	}else if(command.equals("mvcupdateres")) {
		String title = request.getParameter("title");
		String content= request.getParameter("content");
		int seq= Integer.parseInt(request.getParameter("seq"));
		MVCDto dto = new MVCDto();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setSeq(seq);
		
		int res = biz.update(dto);
		
		if(res>0) {
			//response.sendRedirect("mvc.do?command=list"); 이렇게 하면 정상적으로 작동한다.
			//dispatch("mvc.do?command=list", request, response); 이 경우 새로고침 하면 글 목록이 계속 만들어진다.
			
			PrintWriter out = response.getWriter();
			out.println("<script>alert('수정 완료했다');");
			out.println("location.href='mvc.do?command=detail&seq="+dto.getSeq()+"';");
			out.println("</script>");
					
			//jsResponse("글 등록 성공 ", "mvc.do?command=list", response);
		}else {
			//dispatch("mvc.do?command=writeform", request, response);
			PrintWriter out = response.getWriter();
			out.println("<script>alert('수정 실패했다');");
			out.println("location.href='mvc.do?command=list';");
			out.println("</script>");
			//jsResponse("글 등록 실패 ", "mvc.do?command=writeform", response);
		}
		
	}else if(command.equals("delete")) {
		int seq = Integer.parseInt(request.getParameter("seq"));
		MVCDto dto = new MVCDto();
		int res = biz.delete(seq);
		if(res>0){
			/*
			PrintWriter out = response.getWriter();
			out.println("<script>alert('삭제 완료했다');");
			out.println("location.href='mvc.do?command=list';");
			out.println("</script>");
			*/
			jsResponse("글 삭제 성공 ", "mvc.do?command=list", response);
			
		}else {
			
			PrintWriter out = response.getWriter();
			out.println("<script>alert('삭제 실패했다');");
			out.println("location.href='mvc.do?command=detail';");
			out.println("</script>");
			
			//jsResponse("글 삭제 실패 ", "mvc.do?command=detail", response);
		}
		
	}
	}
	
	private void jsResponse(String msg,String url,HttpServletResponse response) throws IOException{
		
		String res = "<script>" + " alert('"+msg+"'); " + " location.href='"+url+"';"
				+ "</script>";
		
		PrintWriter out = response.getWriter();
		out.println(res);
		
	}
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
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

}
