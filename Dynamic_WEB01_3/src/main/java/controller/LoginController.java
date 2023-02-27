package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dto.UserDTO;
import model.service.UserService;

public class LoginController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if((boolean)session.getAttribute("login")) {
			resp.sendRedirect(req.getContextPath());
			return;
		}
		
		Cookie[] cookies = req.getCookies();
		for(Cookie c: cookies) {
			if(c.getName().equals("remember")) {
				req.setAttribute("remember", c.getValue());
			}
		}
		
		req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");
		
		UserDTO dto = new UserDTO();
		dto.setUserId(userId);
		dto.setPassword(password);
		
		UserService service = new UserService();
		UserDTO userData = service.login(dto);
		
		Cookie cookie = new Cookie("remember", userId);
		
		if(userData != null) {
			HttpSession session = req.getSession();
			session.setAttribute("user", userData);
			
			if(remember != null) {
				cookie.setMaxAge(60 * 60 * 24 * 5);
			} else {
				cookie.setMaxAge(0);
			}
			resp.addCookie(cookie);
			resp.sendRedirect(req.getContextPath());
		} else {
			req.setAttribute("error", "로그인 실패 다시 시도하세요.");
			req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
		}
	}
}
