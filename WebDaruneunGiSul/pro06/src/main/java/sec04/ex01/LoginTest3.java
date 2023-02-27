package sec04.ex01;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/k1")
public class LoginTest3 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("두겟");
		req.getRequestDispatcher("./view/LoginTest6.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.getRequestDispatcher("view/LoginTest6.jsp").forward(req, resp);
		String id= req.getParameter("id");
		String password= req.getParameter("password");

		System.out.println("아이디: " + id);
		System.out.println("비밀번호: " + password);

		

	}

}
