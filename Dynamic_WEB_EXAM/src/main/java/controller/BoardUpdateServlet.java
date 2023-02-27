package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.Board;
import model.service.BoardService;


@WebServlet("/update")
public class BoardUpdateServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		String num= req.getParameter("boardnum");
		String num="46";
		Board dto =new Board();
		dto.setBoardNum(Integer.parseInt(num));
		
		BoardService service = new BoardService();
		Board data = service.getNum(dto);
		
//						(속성명  ,  속성값)
		req.setAttribute("board", data);
//		담아서 req객체에서 보내면 visitupdate.jsp에서 request.getAttribute로 받아서 사이트에 표현가능.
		req.getRequestDispatcher("./view/boardUpdateForm.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String boardNum = req.getParameter("boardnum");
		String boardTitle = req.getParameter("boardtitle");
		String boardWriter = req.getParameter("boardwriter");
		String boardContent = req.getParameter("content");
		
		boolean result=false;
		if(boardTitle !=null && boardWriter !=null && boardContent !=null) {
			Board dto = new Board();
			
			dto.setBoardNum(Integer.parseInt(boardNum));
			dto.setBoardWriter(boardWriter);
			dto.setBoardTitle(boardTitle);
			dto.setBoardContent(boardContent);
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");//HH:mm:ss
			Date now = new Date();
			String nowTime1 = sdf1.format(now);
			dto.setBoardDate(nowTime1);
			
			BoardService service= new BoardService();
			result= service.updateBoard(dto);
		}

		if(result) {
			resp.sendRedirect("./boardDetail");
		} else {
			req.getRequestDispatcher("/views/common/error.jsp?message=게시글 등록 실패!").forward(req, resp);

		}
		
	}


}
