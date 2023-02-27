package controller;

import java.io.IOException;
import java.util.Arrays;

import org.apache.ibatis.session.SqlSession;

import database.connect.OracleConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dto.Board;
import model.service.BoardService;


@WebServlet("/boardInsert")
public class BoardInsertController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		resp.sendRedirect("./index.jsp");
		System.out.println("dd");
		req.getRequestDispatcher("./index.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("dopost에 접근");
		
		String boardTitle = req.getParameter("boardTitle");
		String boardWriter = req.getParameter("boardWriter");
		String boardContent = req.getParameter("boardContent");
		boolean result=false;
		if(boardTitle !=null && boardWriter !=null && boardContent !=null) {
			System.out.println(boardTitle);
			System.out.println(boardWriter);
			System.out.println(boardContent);
			Board dto = new Board();
			dto.setBoardWriter(boardWriter);
			dto.setBoardTitle(boardTitle);
			dto.setBoardContent(boardContent);
			BoardService service= new BoardService();
			result= service.insertBoard(dto);
		}

		if(result) {
			resp.sendRedirect("./boardList");
		} else {
			req.getRequestDispatcher("/views/common/error.jsp?message=게시글 등록 실패!").forward(req, resp);
			System.out.println("추가 안됨");
		}
	
	}


}
