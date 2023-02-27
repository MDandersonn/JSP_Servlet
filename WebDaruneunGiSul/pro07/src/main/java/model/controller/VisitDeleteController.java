package model.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.VisitDTO;
import model.service.VisitService;

@WebServlet("/visit/delete")
public class VisitDeleteController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id= req.getParameter("id");
		VisitDTO dto = new VisitDTO();
		dto.setId(Integer.parseInt(id));
		VisitService service = new VisitService();
		boolean result= service.delete(dto);
		if(result) {
			resp.sendRedirect("../visit");
		}else {
			resp.sendRedirect("../error");
		}
	}
	
}
