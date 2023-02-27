package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//@WebFilter({"/bookmark", "bookmark/*", "myinfo"})
public class LoginCheckFilter implements Filter{
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("로그인 체크 필터");
		
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse) resp;
		
		HttpSession session =request.getSession();
		
		if((boolean)session.getAttribute("login")) {//로그인이 되어있다면
			chain.doFilter(req, resp);
		}else {//로그인이 되어있지 않다면 로그인페이지로.
			response.sendRedirect(request.getContextPath()+"/login");
		}
		
		
		
	}
}
