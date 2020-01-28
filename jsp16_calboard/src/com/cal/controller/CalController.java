package com.cal.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cal.dao.Util;

/**
 * Servlet implementation class CalController
 */
@WebServlet("/calendar.do")
public class CalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalController() {
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
		System.out.println(command);
		if(command.equals("calendar")) {
			
			
			response.sendRedirect("calendar.jsp");
		}else if(command.equals("insertcal")) {
			
			String year =request.getParameter("year");
			String month=Util.isTwo(request.getParameter("month"));
			String date=Util.isTwo(request.getParameter("date"));
			String hour=Util.isTwo(request.getParameter("hour"));
			String min=Util.isTwo(request.getParameter("min"));
			String mDate = year+ month+date+hour+min;
			
			String id=request.getParameter("id");
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			
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
	
	
	

}
