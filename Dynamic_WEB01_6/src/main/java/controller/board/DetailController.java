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
import model.dto.BoardImageDTO;
import model.service.BoardService;

// @WebServlet("/board/detail")
public class DetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		List<Integer> history = (List<Integer>)session.getAttribute("boardViewHistory");
		
		String id = req.getParameter("id");
		
		BoardDTO dto = new BoardDTO();
		dto.setId(Integer.parseInt(id));
		
		BoardService service = new BoardService();
		if(!history.contains(Integer.valueOf(id))) {
			service.incViewCnt(dto);
			history.add(Integer.valueOf(id));
			session.setAttribute("boardViewHistory", history);
		}
		
		BoardDTO data = service.getData(dto);
		List<BoardImageDTO> images = service.getImages(data);
		
		if(data != null) {
			req.setAttribute("data", data);
			req.setAttribute("images", images);
			req.getRequestDispatcher("/WEB-INF/view/board/detail.jsp").forward(req, resp);
		} else {
			resp.sendRedirect(req.getContextPath() + "/error");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/////detail.jsp에서 ajax요청받음
		
		String id = req.getParameter("id");
		
		BoardDTO dto = new BoardDTO();
		dto.setId(Integer.parseInt(id));
		
		BoardService service = new BoardService();
		BoardDTO data = service.getData(dto);
		
		
		//이러면 이미지테이블을 만들필요가없다. 이미지는 이미지대로저장 이미지경로만 context컬럼 저장을 해주는것.(context칼럼에는 html태그가 들어가서 가능)
		resp.getWriter().print("{\"context\":\"" + data.getContext().replace("\"", "'") + "\"}");
		//이미지가 들어가면 쌍따옴표때문에 제대로 제이슨 구성이안되서 쌍따옴표를 홑따옴표로 바꿔줌.
		resp.getWriter().flush();
	}
}
