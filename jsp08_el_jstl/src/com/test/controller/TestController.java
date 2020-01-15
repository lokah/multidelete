package com.test.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dto.Score;

/**
 * Servlet implementation class TestController
 */
@WebServlet("/controller.do")
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestController() {
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
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("["+command+"]");
		
		if(command.equals("el")) {
			
			response.sendRedirect("basic-arithmetic.jsp");
		}else if(command.equals("eltest")){
			
			//request에 score 객체를 "hong"이라는 이름으로 담자.
			
			
			Score score = new Score("홍길동", 100,100,98);
			request.setAttribute("hong", score);
			System.out.println(score.getAvg());
			RequestDispatcher dispatch = request.getRequestDispatcher("eltest.jsp");
			dispatch.forward(request, response);
			
			
			
		}else if(command.equals("jstltest")) {
			
			List<Score> list = new ArrayList<Score>();
			
			for(int i = 10; i<50; i+=10) {
				
				Score sc = new Score("이름"+i, 50+i, 50+i,50+i);
				list.add(sc);
			}
			
			request.setAttribute("list", list);;
			RequestDispatcher dispatch = request.getRequestDispatcher("jstltest.jsp");
			dispatch.forward(request, response);
		}else if(command.equals("usebean")) {
			
			response.sendRedirect("usebeantest.jsp");
		}
		
		//doGet(request, response);
	}

}
