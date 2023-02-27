package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class FilterTest implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("filter init메서드 실행");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("dofilter호출");
		req.setCharacterEncoding("utf-8");
		String context = ((HttpServletRequest) req).getContextPath();
		String pathinfo = ((HttpServletRequest) req).getRequestURL().toString();
		String mesg= "getContextPath: "+context + " getRequestURL: "+pathinfo;
		System.out.println(mesg);
		
		long begin = System.currentTimeMillis();
		chain.doFilter(req, resp);
		long end = System.currentTimeMillis();
		System.out.println("작업시간:" + (end-begin)+"ms 응답필터기능입니다.");

		
		
	}
	@Override
	public void destroy() {
		
		System.out.println("destroy호출");
	}

}
