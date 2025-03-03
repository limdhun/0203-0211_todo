package org.zerock.controller;

import java.io.IOException;
import java.util.List;

import org.zerock.dto.PagingDTO;
import org.zerock.dto.TodoListDTO;
import org.zerock.service.TodoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TodoListController
 */
@WebServlet("/todo/list")
public class TodoListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// /WEB-INF/views/todo/list.jsp 로 포워딩 
		
		// /todo/list?page=12
		String pageStr = request.getParameter("page");
		
		int page = pageStr == null ? 1 : Integer.parseInt(pageStr);
		
		if(page <= 0) {
			page = 1;
		}
		
		try {
			List<TodoListDTO> dtoList = TodoService.INSTANCE.getList(page);
			
			int total = TodoService.INSTANCE.getTotal();
			
			PagingDTO pagingDTO = new PagingDTO(page, total);
			
			request.setAttribute("dtoList", dtoList);
			request.setAttribute("pagingDTO", pagingDTO);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(request.getRemoteAddr());
		
		request.getRequestDispatcher("/WEB-INF/views/todo/list.jsp").forward(request, response);
		
	}

}



