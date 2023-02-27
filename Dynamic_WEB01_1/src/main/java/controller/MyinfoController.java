package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dto.UserDTO;
import model.service.UserService;

public class MyinfoController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// 필터에서 아래과정을 해준다.
		//		HttpSession session = req.getSession();
//		
//		if(session.getAttribute("login") == null) {
//			resp.sendRedirect(req.getContextPath());
//			return;
//		}
//		
		req.getRequestDispatcher("/WEB-INF/view/myinfo.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(session.getAttribute("login") == null) {
			resp.sendRedirect(req.getContextPath());
			return;
		}
		
		UserDTO userData = (UserDTO)session.getAttribute("user");
		
		String password = req.getParameter("password");
		String changePass = req.getParameter("changePass");
		String email = req.getParameter("email");
		
		UserDTO dto = new UserDTO();
		dto.setPassword(changePass);
		dto.setEmail(email);
		
		UserService service = new UserService();
		boolean result = service.update(userData, dto, password);
		
		if(result) {
			resp.sendRedirect(req.getContextPath() + "/logout");
		} else {
			req.setAttribute("error", "개인 정보 수정에 문제가 발생하였습니다. 패스워드를 다시 확인하세요.");
			req.getRequestDispatcher("/WEB-INF/view/myinfo.jsp").forward(req, resp);
		}
	}

}
