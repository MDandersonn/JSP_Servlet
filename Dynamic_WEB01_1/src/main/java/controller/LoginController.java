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
		
		if((boolean)session.getAttribute("login")) {//         세션만들때부터 "login"-false 를 넣어줌 그래서 null값으로비교할필요가없음
			//로그인값이 true일때 돌려보내라는말
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
//			session.setAttribute("login",true); //리스너쓰기전 원래상태  --->바인딩리스너에서 설정해주기로함
//         세션만들때부터 "login"-false 를 넣어줌
			
			session.setAttribute("user", userData);//이게 바인드이게 userData가 DTO임.
			
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
