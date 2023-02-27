package model.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.VisitDTO;
import model.service.VisitService;

@WebServlet("/visit/update")
public class VisitUpdateController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id= req.getParameter("id");
		VisitDTO dto =new VisitDTO();
		dto.setId(Integer.parseInt(id));
		
		VisitService service = new VisitService();
		VisitDTO data = service.selectById(dto);
		
//						(속성명  ,  속성값)
		req.setAttribute("data", data);
//		담아서 req객체에서 보내면 visitupdate.jsp에서 request.getAttribute로 받아서 사이트에 표현가능.
//		req.getRequestDispatcher("../view/visitupdate.jsp").forward(req, resp);//됨
		
//		req.getRequestDispatcher("./view/visitupdate.jsp").forward(req, resp);//오류 /visit/view/visitupdate.jsp
//		req.getRequestDispatcher("./visitupdate.jsp").forward(req, resp); //오류  visit/visitupdate.jsp
		req.getRequestDispatcher("../visitupdate.jsp").forward(req, resp); //오류 /visitupdate.jsp
//		req.getRequestDispatcher("/view/visitupdate.jsp").forward(req, resp); //됨
		
//		결론:  pro07==webapp이라는얘기고 visit에 들어오면 현재경로는 pro06/visit/이고
//		pro06/view~ 경로로 들어가야되기때문에  이전경로인 ../로 pro06으로 간후 view부터 넣는게 맞다.
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("id");
		String nickname=req.getParameter("nickname");
		String context= req.getParameter("context");
		
		VisitDTO dto = new VisitDTO();
		dto.setId(Integer.parseInt(id));
		dto.setNickname(nickname);
		dto.setContext(context);
		VisitService service = new VisitService();
		boolean result= service.update(dto);
		if(result) {
			resp.sendRedirect("../visit");
		}else {
			resp.sendRedirect("../error");
		}
	}

}
