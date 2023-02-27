package listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class MyHttpSessionListener implements HttpSessionListener {

	@Override
	
	public void sessionCreated(HttpSessionEvent se) {
		//세션이 생성될때
		HttpSessionListener.super.sessionCreated(se);
		System.out.println("세션이 생성되었습니다.");
		HttpSession session = se.getSession();//생성된세션을 가져옴
		session.setAttribute("login", false);//세션attribute리스너 작동  //세션생성할때 로그인false로 넣어줌
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		//세션이 소멸될때
		HttpSessionListener.super.sessionDestroyed(se);
		System.out.println("세션이 소멸되었습니다.");
	}
}
