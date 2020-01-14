package com.hello.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
    	
    	System.out.println("Servlet 생성자 !!!");
        //super();
        // TODO Auto-generated constructor stub
    }
    
    
    private String initParam;
    private String contextParam;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		//super.init(config);
		
		System.out.println("Servelt init!!!");
		contextParam = config.getServletContext().getInitParameter("jdbcurl");
		initParam = config.getInitParameter("driver");
	}




	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		System.out.println("get 방식으로 넘어온다");
		//System.out.println("initParam:"+ initParam);
		//System.out.println("contextParam :"+ contextParam);;
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		System.out.println("post 방식으로 넘어온다");;
		System.out.println("initParam:"+ initParam);
		System.out.println("contextParam :"+ contextParam);;
		
		
		String command = request.getParameter("command");
		System.out.println("command :" + command);
		
		PrintWriter out = response.getWriter();
		
		out.println("<h1 style='background-color:skyblue'>HelloServelet</h1>");
		out.println("<h3>init-service-doGet/doPost-destroy</h3>");
		out.println("<a href='home.html'>돌아가기</a>");
		
	}
	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		
		System.out.println("servlet destroy!!!");
	}

}
