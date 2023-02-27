package listener;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class MyServletRequestListener implements ServletRequestListener {

/*
 * sre.getServletRequest();의 반환타입인 ServletRequest는 HttpServletRequest의 상위 부모객체이다.
 * 그래서 HttpServletRequest req= (HttpServletRequest)sre.getServletRequest(); 이렇게 캐스팅해서 쓰면된다. 
 * 
 * 알게모르게 redirect가이뤄지는게있어서 생성소멸이 많이일어날수있다.
 * 
 * 
 * 리퀘스트요청이 들어왔을때, 처리가다되서 소멸되기전에 수행해야할 작업이 있을경우 리스너등록해서 리퀘스트발생할때마다 추가적인작업을진행하게 만듦.
 */
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		ServletRequestListener.super.requestInitialized(sre);
		System.out.println("Request 객체가 생성되었습니다.");
	}
	
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		ServletRequestListener.super.requestDestroyed(sre);
		System.out.println("Request 객체가 소멸되었습니다.");
	}
}
