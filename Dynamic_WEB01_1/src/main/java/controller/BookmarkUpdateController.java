package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dto.BookmarkDTO;
import model.dto.UserDTO;
import model.service.BookmarkService;

public class BookmarkUpdateController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(!(boolean)session.getAttribute("login")) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
		UserDTO userData = (UserDTO)session.getAttribute("user");//유저로그인정보
		
		String id = req.getParameter("id");
		
		BookmarkDTO dto = new BookmarkDTO();
		dto.setId(Integer.parseInt(id));
		dto.setUserId(userData.getUserId());//유저아이디도 dto에 넘겨줌
		
		BookmarkService service = new BookmarkService();
		BookmarkDTO data = service.getId(dto);//접속한 유저아이디에 맞고 수정클릭한 북마크 id(일련번호)에 해당하는 데이터를 끌어오기
		//(다른유저아이디에 해당하는 북마크id(일련번호)에 /update?id=21 이렇게접속하는것을 방지하기위함
		if(data == null) {//끌어온 데이터가 존재하지않으면
			resp.sendRedirect(req.getContextPath() + "/error");
		} else {
			req.setAttribute("data", data);
			req.getRequestDispatcher("/WEB-INF/view/bookmarkupdate.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(!(boolean)session.getAttribute("login")) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
		UserDTO userData = (UserDTO)session.getAttribute("user");
		
		String id = req.getParameter("id");
		String url = req.getParameter("url");
		String name = req.getParameter("name");
		
		BookmarkDTO dto = new BookmarkDTO();
		dto.setId(Integer.parseInt(id));
		dto.setUserId(userData.getUserId());
		dto.setUrl(url);
		dto.setName(name);
		
		BookmarkService service = new BookmarkService();
		boolean result = service.update(dto);
		
		if(result) {
			resp.sendRedirect("../bookmark");
		} else {
			resp.sendRedirect("../error");
		}
	}
}
