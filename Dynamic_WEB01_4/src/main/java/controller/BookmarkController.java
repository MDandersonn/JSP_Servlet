package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dto.BookmarkDTO;
import model.dto.UserDTO;
import model.service.BookmarkService;
import paging.Paging;

public class BookmarkController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String p = req.getParameter("p");
		
		if(p == null) {
			p = "1";
		} else {
			if(p.isEmpty()) {
				p = "1";
			}
		}
		
		Cookie cookie = null;
		Cookie[] cookies = req.getCookies();
		for(Cookie c: cookies) {
			if(c.getName().equals("cnt")) {
				cookie = c;
			}
		}
		
		int cnt = 10;
		if(cookie != null) {
			if(req.getParameter("c") != null) {
				if(!req.getParameter("c").isEmpty()) {
					cnt = Integer.parseInt(req.getParameter("c"));
					cookie = new Cookie("cnt", String.valueOf(cnt));
					cookie.setMaxAge(60 * 60 * 24 * 5);
					resp.addCookie(cookie);
				}
			} else {
				cnt = Integer.parseInt(cookie.getValue());
			}
		} else {
			if(req.getParameter("c") != null) {
				if(!req.getParameter("c").isEmpty()) {
					cnt = Integer.parseInt(req.getParameter("c"));
					cookie = new Cookie("cnt", String.valueOf(cnt));
					cookie.setMaxAge(60 * 60 * 24 * 5);
					resp.addCookie(cookie);
				}
			}
		}
		
		HttpSession session = req.getSession();
		
		UserDTO userData = (UserDTO)session.getAttribute("user");
		
		BookmarkService service = new BookmarkService();
		BookmarkDTO dto = new BookmarkDTO();
		dto.setUserId(userData.getUserId());
		
		Paging data = service.getPage(dto, Integer.parseInt(p), cnt);
		
		req.setAttribute("paging", data);
		req.getRequestDispatcher("/WEB-INF/view/bookmark.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		UserDTO userData = (UserDTO)session.getAttribute("user");
		
		String url = req.getParameter("url");
		String name = req.getParameter("name");
		
		BookmarkDTO dto = new BookmarkDTO();
		dto.setUserId(userData.getUserId());
		dto.setUrl(url);
		dto.setName(name);
		
		BookmarkService service = new BookmarkService();
		boolean result = service.add(dto);
		
		if(result) {
			resp.sendRedirect(req.getContextPath() + "/bookmark");
		} else {
			resp.sendRedirect(req.getContextPath() + "/error");
		}
	}
}
