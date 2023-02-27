package controller.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dto.BoardDTO;
import model.service.BoardService;

// @WebServlet("/board/detail")
public class DetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		List<Integer> history = (List<Integer>)session.getAttribute("boardViewHistory");//세션리스너에서 바인딩
		
		String id = req.getParameter("id");
		
		BoardDTO dto = new BoardDTO();
		dto.setId(Integer.parseInt(id));
		
		BoardService service = new BoardService();
		if(!history.contains(Integer.valueOf(id))) {//히스토리리스트에 id값이 없으면
			service.incViewCnt(dto);//조회수를 1증가시킨다.
			history.add(Integer.valueOf(id));//히스토리리스트에 id값 추가.
			session.setAttribute("boardViewHistory", history);
		}
		
		BoardDTO data = service.getData(dto);
		
		if(data != null) {
			req.setAttribute("data", data); 
			req.getRequestDispatcher("/WEB-INF/view/board/detail.jsp").forward(req, resp);
		} else {
			resp.sendRedirect(req.getContextPath() + "/error");
		}
	}
}
