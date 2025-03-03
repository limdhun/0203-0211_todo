package org.zerock.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.zerock.dto.TodoDTO;
import org.zerock.service.TodoService;

/**
 * Servlet implementation class TodoModifyController
 */
@WebServlet("/todo/modify/*")
public class TodoModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TodoModifyController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		String[] arr = uri.split("/");

		String tnoStr = arr[3];

		int tno = Integer.parseInt(tnoStr);

		try {
			// 데이터베이스에서 Todo레코드를 조회한 결과, 없으면 Exception이 발생. 밑에 코드는 실행 안되고 catch로 빠지겠지.
			TodoDTO dto = TodoService.INSTANCE.read(tno);
			// 세션 보관소에서 락커룸 가지고 온 후 user라고 이름이 붙은 뭔가 있는지 확인
			// 문자열로 캐스팅해서 확인하려고 함
			HttpSession session = request.getSession();
			String signedUser = (String) session.getAttribute("user");
			if (!signedUser.equals(dto.getWriter())) {
				response.sendError(403, signedUser);
				return;
			}

			request.setAttribute("dto", dto);

			request.getRequestDispatcher("/WEB-INF/views/todo/modify.jsp").forward(request, response);

		} catch (Exception e) {
			response.sendError(404, "에러가 발생했습니다! 상태 코드: " + 404);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String title = request.getParameter("title");

		String uri = request.getRequestURI();
		String[] arr = uri.split("/");

		String tnoStr = arr[3];

		int tno = Integer.parseInt(tnoStr);

		TodoDTO dto = TodoDTO.builder().title(title).tno(tno).build();

		try {
			TodoService.INSTANCE.modify(dto);

			response.sendRedirect("/todo/read/" + tno);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
