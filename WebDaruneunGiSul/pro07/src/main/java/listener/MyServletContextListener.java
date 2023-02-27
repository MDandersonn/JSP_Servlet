package listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
//		서버가시작될때
//		컨텍스트객체가 어플리케이션을 자체를 말함. 어플리케이션이 실행되면 컨텍스트객체가 생성되는 이벤트가 발생.
		ServletContextListener.super.contextInitialized(sce);
		System.out.println("ServletContext 객체가 생성되었습니다.");
		
		
		ServletContext context =sce.getServletContext();
		context.setAttribute("data", "서블릿콘텍스트속성입니다.");
//		이것을 아래처럼 한번에 쓰면  된다.
//		sce.getServletContext().setAttribute("hello", "안녕하세요.");
		

	}
	
	@Override
//	서버(어플리케이션)가 종료될때 해야할것 작성
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContextListener.super.contextDestroyed(sce);
		System.out.println("ServletContext  객체가 소멸되었습니다.");
	}
}
