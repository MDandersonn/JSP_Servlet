package model.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.VisitDTO;
import model.service.VisitService;

@WebServlet("/visit")
public class VisitController extends HttpServlet{


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		데이터베이스에저장된 내용을 조회하기위함
		VisitService service = new VisitService();
		List <VisitDTO> data =service.selectAll();
		
		req.setAttribute("data", data);
		req.getRequestDispatcher("./view/visit.jsp").forward(req, resp);

	}
	/*
	 * 사용자가 방명록을 작성후 저장 요청을 하면 해당 데이터를
	 * 저장하기 위한 처리를 수행한다.
	 */
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String context = req.getParameter("context");
			String nickname=req.getParameter("nickname");
			
			VisitDTO dto= new VisitDTO();
			
			dto.setContext(context);
			dto.setNickname(nickname);
			
			VisitService service= new VisitService();
			boolean result= service.insert(dto);//서비스는 추가되서 추가된결과를 알려줌
			
			if(result) {//추가가되었으면
				resp.sendRedirect("./visit");//응답에 재접속주소를 담아줌 그래서 재요청될수있게.
//				다시 get요청함
				
			}else {//추가가안되었으면
				System.out.println("추가 안됨");
			}
			
		
		}
}
