package model.controller.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.BoardDTO;
import model.service.BoardService;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int p;
		String pp= req.getParameter("p");
		if (pp==null) {
			p=1;
		}else {
			p=Integer.parseInt(pp);
		}
		
		int cnt = 10;// 쿠키없을떄적용
		String c = req.getParameter("c");// 20
		
		
		if (c == null) {// c변경없이 그냥 접속
			Cookie[] allCookie = req.getCookies();
			for (Cookie cookie : allCookie) {
				if (cookie.getName().equals("cnt")) {// 쿠키있을떄
					cnt = Integer.parseInt(cookie.getValue());
				} // 쿠키없으면 cnt=10으로적용됨
			}
		} else {// c변경이 있었을때
			Cookie cookie = new Cookie("cnt", c);// 쿠키로 저장해놓고
			cnt = Integer.parseInt(c);
			resp.addCookie(cookie);
		}

		BoardService service = new BoardService();
		// 총row수
		int row = service.totalRowNum();
		int totalPageNum = row % cnt == 0 ? row / cnt : (row / cnt) + 1;

		int start = 1 + cnt * (p - 1);
		int last = start + cnt - 1;
		
		last = row < last ? row : last;
		Map<String, Integer> map = new HashMap();
		System.out.println(start);
		System.out.println(last);
		map.put("start", start);
		map.put("last", last);

		List<BoardDTO> data = service.selectByPage(map);
		int pageLimit=10;
		int bigPage;
		int next;
		if(p%pageLimit==0) {
			bigPage=(p/pageLimit)*pageLimit;
			next=(p/pageLimit)*pageLimit +1;
		}else {
			bigPage=(p/pageLimit+1)*pageLimit;
			next=(p/pageLimit +1)*pageLimit +1;
		}
		int lastPage = bigPage > totalPageNum ? totalPageNum:bigPage;
		int prev= (p/pageLimit -1)*pageLimit +1;
		
		req.setAttribute("p", p);
		req.setAttribute("cnt", cnt);
		req.setAttribute("totalPageNum", totalPageNum);
		req.setAttribute("data", data);
		req.setAttribute("pageLimit", pageLimit);
		req.setAttribute("bigPage", bigPage);
		req.setAttribute("lastPage", lastPage);
		req.setAttribute("prev", prev);
		req.setAttribute("next", next);
		
		req.getRequestDispatcher("./view/board.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
