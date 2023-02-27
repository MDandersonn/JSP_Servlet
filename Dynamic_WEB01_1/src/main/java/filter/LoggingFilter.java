package filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebFilter("/*")
// /*은 모든 URL주소에 이 필터가 동작이 되게 하겠다는 의미
public class LoggingFilter implements Filter{
	//로그형식의 메시지를 출력하려고 만든것
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		//ServletRequest 이게 HttpServletRequest의 상위객체.
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse) resp;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd a KK:mm:ss.SSS");
		//KK : 01~12 SSS:밀리초
		
		String path= request.getRequestURI();
		String method = request.getMethod();
		String query = request.getQueryString();
		
		System.out.printf("df.format(new Date()):[%s], request.getMethod():%s,  request.getQueryString(): %s",df.format(new Date()),method,path);
		if(query!=null) {
			System.out.println("?"+query);
		}
		
		//임포트안하기떄문에 new Date() 가 아니라 new java.util.Date()이라 한거.
		System.out.println(df.format(new java.util.Date()));
		System.out.println("request.getRequestURI():"+path);
		System.out.println("request.getMethod():"+method);
		System.out.println("request.getQueryString()"+query);
		
		
		chain.doFilter(req, resp); //다음필터를 호출함 계속연계됨
		//가장 마지막 필터도 doFilter를 한다. 
		// 필터-> 필터-> 서블릿
		//최종적으로 서블릿이 동작 서블릿에서는 모델or뷰로 감 
		//모델,뷰에서 서블릿으로 서블릿에서 이전필터로.... 최종요청했던 사용자로 전달
		//이 과정은 응답이다.
		
		
//		요청에 대한 응답과정
		
		//http상태코드이다 200:정상 302:redirect 404:잘못된요청(페이지찾을수없음)
		//500: 서버오류(코드가잘못되었다생각)
		int code=response.getStatus();
		
		String redirect = response.getHeader("Location");
		
		System.out.printf("df.format(new Date()): [%s],  response.getStatus(): %s", df.format(new Date()),code);
		
		switch(code/100) {
			case 2:
				System.out.println("정상");
				break;
			case 3:
				System.out.printf("리다이렉트(%s)",redirect);
				break;
			case 4:
				System.out.println("요청 오류");
				break;
			case 5:
				System.out.println("서버오류");
				break;
		}
		System.out.println();
		
		System.out.println(df.format(new Date()));
		System.out.println("response.getStatus() :"+code);
		System.out.println("response.getHeader(\"Location\") :"+redirect);
		
		
		
		
		
		
	}

}
