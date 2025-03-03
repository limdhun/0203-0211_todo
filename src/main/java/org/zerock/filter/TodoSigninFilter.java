package org.zerock.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class TodoSigninFilter
 */
@WebFilter({"/todo/*"})
public class TodoSigninFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public TodoSigninFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("------doFilter............");
		
		//HTTP를 이용하기 위해서. 다운캐스팅?
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		String path = req.getRequestURI();
		System.out.println("path: " + path);
		
		if(path.startsWith("/todo/list")) {
			//다음 필터 혹은 컨트롤러, 응답으로 처리하는 
			chain.doFilter(request, response);
			return;
		}
		System.out.println("-------------doFilter...........1");
		HttpSession session = req.getSession(false);
		
		if (session == null || session.getAttribute("user") == null) {
			System.out.println("-------------doFilter...........2");
			res.sendRedirect("/signin");
			
			return;
			
		}
		
		//다음 필터 혹은 컨트롤러, 응답으로 처리하는 것
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
