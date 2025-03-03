package org.zerock.controller;

import java.io.IOException;

import org.zerock.dto.TodoAddDTO;
import org.zerock.service.TodoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class TodoAddController
 */
@WebServlet("/todo/add")
public class TodoAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoAddController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		request.getRequestDispatcher("/WEB-INF/views/todo/add.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("TodoAddController doPost...............");
		
		
		//request 는 InputStream(Read) 용도 - 문자열로만 처리가 가능 (스프링에서는 해결)
		//post방식일 경우에는 payload를 확인 
		
		//한글이 깨지는 케이스 1)브라우저에서 전송 할 때 2)컨트롤러에서 수집할 때 3)DB처리할 때 
		
		//전달되는 모든 데이터 인코딩 처리 한글 처리용 
		request.setCharacterEncoding("UTF-8");
		
		String titleStr = request.getParameter("title");
		String writerStr = request.getParameter("writer");
		
		System.out.println("title: " + titleStr);
		System.out.println("writer: " + writerStr);
		
		TodoAddDTO addDTO = new TodoAddDTO();
		addDTO.setTitle(titleStr);
		addDTO.setWriter(writerStr);
		
		System.out.println(addDTO);
		
		try {
			TodoService.INSTANCE.add(addDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// /todo/list 경로로 브라우저에게 이동하라고 명령한다. 
		response.sendRedirect("/todo/list?result=add");
	
	}

}
