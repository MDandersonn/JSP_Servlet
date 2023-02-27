package listener;

import java.util.ArrayList;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import model.dto.Role;

@WebListener
public class MyHttpSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSessionListener.super.sessionCreated(se);
		HttpSession session = se.getSession();
		session.setAttribute("login", false);
		session.setAttribute("role", new Role("GUEST"));
		session.setAttribute("boardViewHistory", new ArrayList<Integer>());//조회수
		session.setAttribute("boardRecCntHistory", new ArrayList<Integer>());//추천수
		session.setAttribute("boardNRecCntHistory", new ArrayList<Integer>());//비추수
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSessionListener.super.sessionDestroyed(se);
	}
}
