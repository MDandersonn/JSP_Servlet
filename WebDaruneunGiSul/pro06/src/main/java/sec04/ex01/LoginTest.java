package sec04.ex01;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loginTest")
public class LoginTest extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id =req.getParameter("id");
		String password =req.getParameter("password");
		
		req.setAttribute("id1", id);
		req.setAttribute("password1", password);
		
//		form에서 post할때 딸려온 id와 password가 req에 담겨서 밑에 디스패처에 딸려서 같이 이동한다.
//		그래서 JSP파일에서 request.getParameter("id")하면 form에서 포스트한 name="id"가 여기로 들어감
//		즉 setAttribute했던것은 getParameter로 받아올수 없음.
		req.getRequestDispatcher("view/LoginTest4.jsp").forward(req, resp);
		System.out.println("수행12");
		

	}

}
