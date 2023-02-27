package sec01.ex01;

import java.io.IOException;
import java.util.Enumeration;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/input")
public class InputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id= request.getParameter("id");
		String password= request.getParameter("password");
		System.out.println("아이디: " + id);
		System.out.println("비밀번호: " + password);
		
//		여러개의 값을 얻어올때
		String[] arr = request.getParameterValues("subject");
		for( String sub : arr) {
			System.out.println("선택한 과목 : "+sub);
		}
		
		Enumeration enu =request.getParameterNames();
		while(enu.hasMoreElements()) {
			String name1 =(String) enu.nextElement();
			String[] values1 =request.getParameterValues(name1);
			for( String sub : values1) {
				System.out.println(name1+" : "+sub);
			}
			
		}
		
	}

}
