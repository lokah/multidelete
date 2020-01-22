package com.bike.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bike.dto.BikeDto;

/**
 * Servlet implementation class BikeServlet
 */
@WebServlet("/BikeServlet")
public class BikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BikeServlet() {
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
		if(command.equals("first")) {
			
			response.sendRedirect("bike01.jsp");
		}else if(command.equals("firstdb")) {
			
			String[] bike = request.getParameterValues("bike");
			
			List<BikeDto> bikes = new ArrayList<BikeDto>();
			for(int i =0; i<bike.length;i++) {
				
				String[] tmp = bike[i].split("/");
				//System.out.println(tmp[2]+" "+tmp[5]+","+tmp[6]);
				//1.tmp의 값들을 dto에 담고
				//2.dto를 list(bikes)에 담고
				//3.dao에 list를 보내서 insert하자
				//4.저장 완료되면 sendredirect로 index로 보내자.
			}
		}
	}

}
