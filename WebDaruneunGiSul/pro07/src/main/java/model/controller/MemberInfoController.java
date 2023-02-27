package model.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dto.MemberDTO;
import model.service.MemberService;

@WebServlet("/info")
public class MemberInfoController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("isLogin") !=null) {
			MemberDTO dto= (MemberDTO)session.getAttribute("login");
			session.setAttribute("data",dto );
			
			req.getRequestDispatcher("./view/MemberUpdate.jsp").forward(req, resp);
		}else {
			resp.sendRedirect(req.getContextPath()+"/login");
		}
		
	}

}
