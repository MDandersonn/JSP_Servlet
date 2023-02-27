package model.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.MemberDTO;
import model.service.MemberService;

@WebServlet("/memberUpdate")
public class MemberUpdateController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id= req.getParameter("id");
		MemberDTO dto= new MemberDTO();
		dto.setId(id);
		MemberService service = new MemberService();
		MemberDTO data=service.selectById(dto);
		
		req.setAttribute("data", data);
		req.getRequestDispatcher( "./view/MemberUpdate.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id= req.getParameter("id");
		String pwd= req.getParameter("pwd");
		String name= req.getParameter("name");
		String email= req.getParameter("email");
		
		MemberDTO dto =new MemberDTO();
		dto.setId(id);
		dto.setPwd(pwd);
		dto.setName(name);
		dto.setEmail(email);
		
		MemberService service = new MemberService();
		boolean result= service.updateMember(dto);
		if(result) {
			resp.sendRedirect("./memberList");
		}
		else {
			System.out.println("수정안됨");
		}
		
		
		
		
	}

}
