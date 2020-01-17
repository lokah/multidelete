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
@WebServlet(urlPatterns = { "/mylist", "/detail","/writeform","/mywriteres","/myupdate","/myupdateres","/mydelete" })
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MyBiz biz;

	private void getRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		biz = new MyBizImpl();
		String command = request.getRequestURI();
		System.out.printf("[%s]\n", command);
		if (command.endsWith("/mylist")) {

			doMyList(request, response);
		} else if (command.endsWith("/detail")) {

			doDetail(request, response);
		}else if(command.endsWith("/writeform")) {
			
			doMywriteform(request, response);
		}else if(command.endsWith("/mywriteres")) {
			
			doMywriteres(request, response);
		}else if(command.endsWith("/myupdate")) {
			
			doMyUpdate(request, response);
		}else if(command.endsWith("/myupdateres")) {
			
			doMyUpdateRes(request, response);
		}else if(command.endsWith("/mydelete")) {
			doMyDelete(request, response);
		}

	}

	private void doMyDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		MyDto dto = new MyDto();
		int res = biz.delete(seq);
		if(res>0) {
			jsResponse("글 삭제 성공", "mylist", response);
		}else {
			jsResponse("글 삭제 실패", "detail", response);
		}
			
		}
		
	

	private void doMyUpdateRes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		String title = request.getParameter("title");
		String content= request.getParameter("content");
		int seq= Integer.parseInt(request.getParameter("seq"));
		MyDto dto = new MyDto();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setSeq(seq);
		
		int res = biz.update(dto);
		
		if(res>0) {
			
			jsResponse("글 수정 성공", "mylist", response);
		}else {
			jsResponse("글 수정 실패", "myupdate", response);
		}
		
	}
	

	private void doMyUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		MyDto dto = biz.selectOne(seq);
		request.setAttribute("dto", dto);
		dispatch("myupdate.jsp", request, response);
		
	}

	private void doMywriteres(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		//값을 가져오고
		String writer =request.getParameter("writer");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		//db에서 가져오고
		MyDto dto = new MyDto(writer,title,content);
		
		int res = biz.insert(dto);
		//보낸다.
		if(res>0) {
			
			jsResponse("글 작성 성공", "mylist", response);
		}else {
			jsResponse("글 작성 실패", "writeform", response);
		}
		
	}

	private void doMywriteform(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("mywrite.jsp");
	}

	private void doDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int seq = Integer.parseInt(request.getParameter("seq"));
		MyDto dto = biz.selectOne(seq);
		request.setAttribute("dto", dto);
		dispatch("detail.jsp", request, response);
	}

	private void doMyList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<MyDto> list = biz.selectList();
		request.setAttribute("list", list);
		dispatch("mylist.jsp", request, response);
	}

	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);

	}

	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {

		PrintWriter out = response.getWriter();
		out.println("<script type='text/javascript'>");
		out.println("alert('"+msg+"')");
		out.println("location.href='"+url+"'");
		out.println("</script>");
	}

	/**
	 * 
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public MyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		getRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		getRequest(request, response);
	}

}
