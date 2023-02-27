package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/login")
public class LoginServlet extends HttpServlet {
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
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String data="<html>";
		data +="<body>";
		data +="아이디: "+id;
		data +="<br>";
		data +="비밀번호: "+password;
		data +="</body>";
		data +="</html>";
		out.print(data);
		
		String id2= request.getParameter("id");
		String password2= request.getParameter("password");
		System.out.println("aa"+id2+password2);
		
		
	}

}
