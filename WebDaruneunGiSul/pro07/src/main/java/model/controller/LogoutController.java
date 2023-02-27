package model.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dto.MemberDTO;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			HttpSession session = req.getSession();
			MemberDTO dto = (MemberDTO)session.getAttribute("login");
			ServletContext context= getServletContext();
			List userList= (ArrayList)context.getAttribute("userList");
			userList.remove(dto);
//			context.removeAttribute("userList");
			context.setAttribute("userList", userList);
			
			session.invalidate();
//			req.getRequestDispatcher( "./index.jsp").forward(req, resp);
			resp.sendRedirect("./index.jsp");
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
	}

}
