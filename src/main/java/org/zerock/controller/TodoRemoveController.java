package org.zerock.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.zerock.service.TodoService;

/**
 * Servlet implementation class TodoRemoveController
 */
@WebServlet("/todo/remove/*")
public class TodoRemoveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoRemoveController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String[] arr = uri.split("/");

		String tnoStr = arr[3];

		int tno = Integer.parseInt(tnoStr);
		
		try {
			TodoService.INSTANCE.remove(tno);
			
			response.sendRedirect("/todo/list?result=remove");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
