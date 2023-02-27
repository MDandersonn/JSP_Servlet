package model.controller.board;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.BoardDTO;
import model.dto.BoardImageDTO;
import model.service.BoardService;
@WebServlet("/board/detail")
public class BoardDetailController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String articleNO= req.getParameter("articleNO");
		BoardService service =new BoardService();
		BoardDTO dto= new BoardDTO();
		dto.setArticleNO(Integer.parseInt(articleNO));
		BoardDTO data=service.selectByArticleNO(dto);
		List<BoardImageDTO> images = service.getImages(dto);
		req.setAttribute("data", data);
		req.setAttribute("images", images);
		req.getRequestDispatcher("/view/boardDetail.jsp").forward(req, resp);
	}

}
