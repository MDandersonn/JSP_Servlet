package controller.board;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dto.Role;
import model.service.BoardService;
import paging.Paging;

// @WebServlet("/board")
public class MainController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		String p = req.getParameter("p") == null ? "1" : req.getParameter("p");
		p = p.isEmpty() ? "1" : p;
		
		String c = req.getParameter("c");
		
		Cookie boardCookie = null;
		Cookie[] cookies = req.getCookies();
		for(Cookie cookie: cookies) {
			if(cookie.getName().equals("boardListLimit")) {
				boardCookie = cookie;
			}
		}
		
		int pageLimit = 10;
		if(boardCookie != null) {
			pageLimit = Integer.parseInt(boardCookie.getValue());
		}
		
		if(c != null) {
			if(!c.isEmpty()) {
				pageLimit = Integer.parseInt(c);
				boardCookie = new Cookie("boardListLimit", c);
				boardCookie.setMaxAge(60 * 60 * 24 * 5);
				resp.addCookie(boardCookie);
			}
		}
		
		int pageNumber = Integer.parseInt(p);
		BoardService service = new BoardService();
		Paging data = service.getPage(pageNumber, pageLimit);
		
		req.setAttribute("paging", data);
		
		if(((Role)session.getAttribute("role")) !=null && ((Role)session.getAttribute("role")).isAdmin()) {//관리자접속이라면
			req.getRequestDispatcher("/WEB-INF/view/admin/board/main.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("/WEB-INF/view/board/main.jsp").forward(req, resp);
		}
	}
	
}
