package sec04.ex01;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loginTest2")
public class LoginTest2 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id =req.getParameter("id");
		String password =req.getParameter("password");
		
		req.setAttribute("address1", "서울시 강남구1");
		req.setAttribute("address2", "서울시 강남구2");
		req.setAttribute("address3", "서울시 서초구");
		req.getRequestDispatcher("view/LoginTest5.jsp?id="+id+"&&password"+password).forward(req, resp);
		System.out.println("수행1");
		

	}

}
