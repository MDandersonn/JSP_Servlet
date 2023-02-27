package model.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.dao.BoardDao;
import model.dto.Board;


public class BoardService {
	
	public boolean insertBoard(Board dto) {
		BoardDao dao = new BoardDao();

		int boardNum= dao.getBoardNum();
		dto.setBoardNum(boardNum);
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");//HH:mm:ss
		Date now = new Date();
		String nowTime1 = sdf1.format(now);
		dto.setBoardDate(nowTime1);
		System.out.println(nowTime1);
		int count= dao.insertBoard(dto); 

		if(count==1) {
			dao.commit();
			dao.close();
			return true;
		}
//		1이 아니면
		dao.rollback();
		dao.close();
		return false;
	}

	public List<Board> getAll() {
		BoardDao dao = new BoardDao();
		List<Board> data = dao.selectAll();
		dao.close();
		return data;
	}
	
	public Board getId(int num) {
		BoardDao dao =new BoardDao();
		Board data=dao.selectId(num);
		dao.close();
		return data;
	}
	
	public Board getNum(Board board) {
		BoardDao dao =new BoardDao();
		Board data=dao.selectNum(board);
		dao.close();
		return data;
	}

	public boolean updateBoard(Board dto) {
		BoardDao dao =new BoardDao();
		int count=dao.updateBoard(dto);
		if(count==1) {
			dao.commit();
			dao.close();
			return true;
		}
//		1이 아니면
		dao.rollback();
		dao.close();
		return false;
	}
	

}
