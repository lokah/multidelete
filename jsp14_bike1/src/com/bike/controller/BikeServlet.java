package com.bike.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bike.biz.BikeBiz;
import com.bike.dto.BikeDto;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String command = request.getParameter("command");

		BikeBiz biz = new BikeBiz();// BikeDao dao = new BikeDao();

		biz.delete();// dao.delete();
		if (command.equals("first")) {

			response.sendRedirect("bike01.jsp");
		} else if (command.equals("firstdb")) {

			String[] bike = request.getParameterValues("bike");

			List<BikeDto> bikes = new ArrayList<BikeDto>();
			for (int i = 0; i < bike.length; i++) {

				String[] tmp = bike[i].split("/");
				System.out.println(tmp[2] + " " + tmp[5] + "," + tmp[6]);
				// 1.tmp의 값들을 dto에 담고
				// 2.dto를 list(bikes)에 담고
				// 3.dao에 list를 보내서 insert하자
				// 4.저장 완료되면 sendredirect로 index로 보내자.

				BikeDto dto = new BikeDto(tmp[0], Integer.parseInt(tmp[1]), tmp[2], tmp[3], Integer.parseInt(tmp[4]),
						Double.parseDouble(tmp[5]), Double.parseDouble(tmp[6]));
				// dto.setAddr_gu(tmp[0]);
				// dto.setContent_id(Integer.parseInt(tmp[1]));....

				bikes.add(dto);
			}

			int res = biz.insert(bikes);// int res = dao.insert(bikes);
			if (res == bikes.size()) {
				System.out.println("성공");
			} else {
				System.out.println("실패");
			}

			// 4.

			response.sendRedirect("index.html");

			// String writer = request.getParameter("writer");
			// String title = request.getParameter("title");
			// String content = request.getParameter("content");

			// dto.setWriter(writer);
			// dto.setTitle(title);
			// dto.setContent(content);

			// 가져올 데이터가 있는지
			// int res = biz.insert(dto);
			// if(res>0) {
			// response.sendRedirect("mvc.do?command=list"); 이렇게 하면 정상적으로 작동한다.
			// dispatch("mvc.do?command=list", request, response); 이 경우 새로고침 하면 글 목록이 계속
			// 만들어진다.

			// PrintWriter out = response.getWriter();
			// out.println("<script>alert('새로운 글을 등록 완료했다');");
			// out.println("location.href='con.do?command=list';");
			// out.println("</script>");

			// jsResponse("글 등록 성공 ", "mvc.do?command=list", response);
			// }else {
			// dispatch("mvc.do?command=writeform", request, response);
			// PrintWriter out = response.getWriter();
			// out.println("<script>alert('새로운 글을 등록 실패했다');");
			// out.println("location.href='con.do?command=writeform';");
			// out.println("</script>");
			// jsResponse("글 등록 실패 ", "mvc.do?command=writeform", response);
			// }
			// }

		} else if (command.equals("second")) {

			response.sendRedirect("bike02.jsp");
		} else if (command.equals("second_db")) {

			String txt = request.getParameter("obj");
			//System.out.println(txt);
			//한통으로 되어 있는 문자열을 각 요소로 파싱한다.
			JsonElement element = JsonParser.parseString(txt);
			//System.out.println(element.getAsJsonObject().get("DESCRIPTION"));
			
			List<BikeDto> list = new ArrayList<BikeDto>();
			
			for(int i=0;i<element.getAsJsonObject().get("DATA").getAsJsonArray().size();i++) {
				//특정 키 Object 값 내부의 키 값 가져오기
				// 내부의 Object의 'DATA'의 키 값을 찾기 위해서는 반드시 'DATA'를 먼저 가져와 찾는다.
				JsonObject tmp = element.getAsJsonObject().get("DATA").getAsJsonArray().get(i).getAsJsonObject();
				
				//System.out.println(tmp.get("addr_gu").getAsString());
				JsonElement addr_gu_je = tmp.get("addr_gu");
				JsonElement content_id_je = tmp.get("content_id");
				JsonElement content_nm_je = tmp.get("content_nm");
				JsonElement new_addr_je = tmp.get("new_addr");
				JsonElement cradle_count_je = tmp.get("cradle_count");
				JsonElement longitude_je = tmp.get("longitude");
				JsonElement latitude_je = tmp.get("latitude");
				
				String addr_gu = addr_gu_je.getAsString();
				int content_id = content_id_je.getAsInt();
				String content_nm = content_nm_je.getAsString();
				String new_addr = new_addr_je.getAsString();
				int cradle_count = cradle_count_je.getAsInt();
				double longitude = longitude_je.getAsDouble();
				double latitude = latitude_je.getAsDouble();
				
				BikeDto dto = new BikeDto(addr_gu, content_id, content_nm,new_addr,cradle_count,longitude,latitude);
						list.add(dto);
			}
			
			int res = biz.insert(list);
			if(res==1163) {
				System.out.println("성공");
			}else {
				System.out.println("실패");
			}
			response.getWriter().append(res+"");
		}
	}

}
