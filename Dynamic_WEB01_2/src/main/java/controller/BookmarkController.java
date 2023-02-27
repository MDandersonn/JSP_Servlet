package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dto.BookmarkDTO;
import model.dto.UserDTO;
import model.service.BookmarkService;

public class BookmarkController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

//		if(session.getAttribute("login")==null)로 해도됨.   //로그인을 안했을경우
		if(!(boolean)session.getAttribute("login")) {//로그인을 안했을 경우
			resp.sendRedirect(req.getContextPath() + "/login");//로그인페이지로 돌려보냄
			return;//밑에코드 진행할 필요 없어서 종료. 
		}
		//위를 통과하여 로그인이 되었을 경우
		
		//user정보 :로그인을한 사용자의 정보를 받아옴
		UserDTO userData = (UserDTO)session.getAttribute("user");
		
		BookmarkService service = new BookmarkService();
		BookmarkDTO dto = new BookmarkDTO();
		dto.setUserId(userData.getUserId());
//		유저아이디에 해당하는 dto를 저장
		List<BookmarkDTO> data = service.getAll(dto);//유저정보를 매개변수로 넣어줌
//		유저아이디에 해당하는 즐겨찾기목록만 selectAll조회해서 가져옴.
		req.setAttribute("data", data);
		req.getRequestDispatcher("/WEB-INF/view/bookmark.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(!(boolean)session.getAttribute("login")) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
		UserDTO userData = (UserDTO)session.getAttribute("user");
		
		String url = req.getParameter("url");
		String name = req.getParameter("name");
		
		BookmarkDTO dto = new BookmarkDTO();
		dto.setUserId(userData.getUserId());//dto에 유저id를 담아줌
		dto.setUrl(url);
		dto.setName(name);
		
		BookmarkService service = new BookmarkService();
		boolean result = service.add(dto);
		
		if(result) {
//			"./bookmkark"상대경로에서 아래로 바꿔줌
			resp.sendRedirect(req.getContextPath() + "/bookmark");
		} else {
			resp.sendRedirect(req.getContextPath() + "/error");
		}
	}
}
