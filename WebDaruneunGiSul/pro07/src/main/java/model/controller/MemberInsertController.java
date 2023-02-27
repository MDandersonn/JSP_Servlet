package model.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.MemberDTO;
import model.service.MemberService;

@WebServlet("/memberInsert")
public class MemberInsertController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher( "./view/MemberInsert.jsp").forward(req, resp);
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
		int result= service.insertMember(dto);
		if(result==1) {
			resp.sendRedirect("./index.jsp");
			return;
		}
		else if (result==-1){
			req.setAttribute("msg", "아이디가 존재합니다.");
		}
		else if(result==0) {
			req.setAttribute("msg", "아이디 외 다른 문제로 다시해주세요");
		}
		req.getRequestDispatcher("./memberInsert").forward(req, resp);
		System.out.println("추가안됨");
		
		
		
	}

}
