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

@WebServlet("/search")
public class MemberSearchController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name= req.getParameter("name");
		MemberDTO dto = new MemberDTO();	
		dto.setName(name);
		MemberService service = new MemberService();
		List<MemberDTO> data =service.selectByName(dto);
		req.setAttribute("data", data);
		req.getRequestDispatcher("./view/MemberList.jsp").forward(req, resp);
		
		
	}

}
