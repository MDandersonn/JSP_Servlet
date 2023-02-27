package filter;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// @WebFilter({"/bookmark", "/bookmark/*", "/myinfo", "/board/add", "/board/rec", "/board/nrec", "/ajax/recommend"})
public class LoginCheckFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		HttpSession session = request.getSession();
		if((boolean)session.getAttribute("login")) {//로그인이 되어있을때
			chain.doFilter(req, resp);
		} else {//로그인이 안되어있을때
			if(request.getRequestURI().contains("ajax")) {//맵핑경로에 ajax가있으면 , (추천비추천기능)
				response.getWriter().print("{\"redirect\": \"" + request.getContextPath() + "/login\"}");
				response.getWriter().flush();
				//redirect:경로  로 응답한다. 
				
			} else {//에이잭스안쓴경우
				response.sendRedirect(request.getContextPath() + "/login");
			}
		}
		
	}

}
