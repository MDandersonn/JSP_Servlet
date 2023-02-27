package listener;

import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class MyServletContextAttributeListener implements ServletContextAttributeListener {
/* ServletContext의 속성이
 * setattribute할때(속성이 설정될때)
 * 변경될때(다시설정할때)
 * remove할때 마다 작동함.
 */

	@Override
	public void attributeAdded(ServletContextAttributeEvent event) {
		ServletContextAttributeListener.super.attributeAdded(event);
		System.out.println("ServletContext 에 데이터 추가됨 - " + event.getName() + ":" + event.getValue());
	}
	
	@Override
	public void attributeRemoved(ServletContextAttributeEvent event) {
		ServletContextAttributeListener.super.attributeRemoved(event);
		System.out.println("ServletContext 의 데이터 삭제됨 - " + event.getName() + ":" + event.getValue());
	}
	
	@Override
	public void attributeReplaced(ServletContextAttributeEvent event) {
		ServletContextAttributeListener.super.attributeReplaced(event);
		System.out.println("ServletContext 의 데이터 수정됨 - " + event.getName() + ":" + event.getValue());
	}
	
}
