package model.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dto.MemberDTO;
import model.service.MemberService;

@WebServlet("/memberList")
public class MemberListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session= req.getSession();
//		MemberDTO dto= new MemberDTO();
		if(session.getAttribute("login")==null) {//로그인이 안되어있을때
			resp.sendRedirect(req.getContextPath()+"/login");
			return;
		}
		
		//로그인이되어있을떄
		MemberDTO dto=(MemberDTO)session.getAttribute("login");
		if(dto.getId().equals("admin")) {
			MemberService service = new MemberService();

			List<MemberDTO> data = service.selectAll();
			req.setAttribute("data", data);
			req.getRequestDispatcher("./view/MemberList.jsp").forward(req, resp);
		}else {
			resp.sendRedirect(req.getContextPath()+"/login");
		}
		

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
