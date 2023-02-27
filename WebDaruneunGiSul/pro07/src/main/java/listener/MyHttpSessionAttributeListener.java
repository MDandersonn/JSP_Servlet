package listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

@WebListener
public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {
//	세션에다가 setAttribute, removeAttribute할때 아니면 변경할때,

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		HttpSessionAttributeListener.super.attributeAdded(event);
		System.out.println("session 에 속성추가: " + event.getName() + " - " + event.getValue());
	}
	
	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		HttpSessionAttributeListener.super.attributeRemoved(event);
		System.out.println("session 속성 삭제: " + event.getName() + " - " + event.getValue());
	}
	
	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		HttpSessionAttributeListener.super.attributeReplaced(event);
		System.out.println("session 속성 수정: " + event.getName() + " - " + event.getValue());
	}
}
