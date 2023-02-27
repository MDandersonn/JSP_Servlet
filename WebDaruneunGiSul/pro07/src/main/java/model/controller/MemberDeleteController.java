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

@WebServlet("/memberDelete")
public class MemberDeleteController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id= req.getParameter("id");
		MemberDTO dto= new MemberDTO();
		dto.setId(id);
		MemberService service = new MemberService();
		boolean result=service.deleteById(dto);
		if(result==true) {
			resp.sendRedirect("./memberList");
		}else {
			System.out.println("삭제안됨");
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
