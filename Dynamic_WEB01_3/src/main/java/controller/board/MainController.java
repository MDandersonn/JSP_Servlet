package controller.board;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.service.BoardService;
import paging.Paging;

// @WebServlet("/board")
public class MainController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String p = req.getParameter("p") == null ? "1" : req.getParameter("p"); // web01/board 인경우는 p는 null
		p = p.isEmpty() ? "1" : p;// web01/board?p= 인경우를 제외하는과정  이는 p의 값이"" 로 인식된다.
		
		String c = req.getParameter("c");
		
		Cookie boardCookie = null;
		Cookie[] cookies = req.getCookies();
		for(Cookie cookie: cookies) {
			if(cookie.getName().equals("boardListLimit")) {
				boardCookie = cookie;
			}
		}
		
		int pageLimit = 10;
		if(boardCookie != null) {//두번째 접속해서 쿠키가있을때 ,c값을 변경해주지않았을때
			pageLimit = Integer.parseInt(boardCookie.getValue());
		}
		
		if(c != null) {
			if(!c.isEmpty()) {// c값을 변경해주었을때
				pageLimit = Integer.parseInt(c);
				boardCookie = new Cookie("boardListLimit", c);
				boardCookie.setMaxAge(60 * 60 * 24 * 5);
				resp.addCookie(boardCookie);
			}
		}
		//최초접속하여 c가 null이고 쿠키도없을때
		int pageNumber = Integer.parseInt(p);
		BoardService service = new BoardService();
		Paging data = service.getPage(pageNumber, pageLimit);
		
		req.setAttribute("paging", data);
		req.getRequestDispatcher("/WEB-INF/view/board/main.jsp").forward(req, resp);
	}
	
}
