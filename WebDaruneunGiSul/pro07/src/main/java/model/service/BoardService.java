package model.service;

import java.util.List;
import java.util.Map;

import model.dao.BoardDAO;
import model.dto.BoardDTO;
import model.dto.BoardImageDTO;

public class BoardService {

	public List<BoardDTO> selectAll() {
		BoardDAO dao = new BoardDAO();
		List<BoardDTO> data=dao.selectAll();
		dao.commit();
		dao.close();
		return data;
	}

	public int totalRowNum() {
		BoardDAO dao = new BoardDAO();
		int row=dao.totalRowNum();
		dao.commit();
		dao.close();
		return row;
	}

	public List<BoardDTO> selectByPage(Map<String,Integer> map) {
		BoardDAO dao = new BoardDAO();
		List<BoardDTO> data =dao.selectByPage (map);
		dao.commit();
		dao.close();
		return data;
	}

	public BoardDTO selectByArticleNO(BoardDTO dto2) {
		BoardDAO dao = new BoardDAO();
		BoardDTO data = dao.selectByArticleNO(dto2);
		dao.commit();
		dao.close();
		return data;
	}

	public void recUP(BoardDTO dto) {
		BoardDAO dao = new BoardDAO();
		int result= dao.recUP(dto);
		dao.commit();
		dao.close();
		
	}
	public void recDOWN(BoardDTO dto) {
		BoardDAO dao = new BoardDAO();
		int result= dao.recDOWN(dto);
		dao.commit();
		dao.close();
		
	}

	public void removeRecDOWN(BoardDTO dto) {
		BoardDAO dao = new BoardDAO();
		int result= dao.removeRecDOWN(dto);
		dao.commit();
		dao.close();
		
	}

	public void removeRecUP(BoardDTO dto) {
		BoardDAO dao = new BoardDAO();
		int result= dao.removeRecUP(dto);
		dao.commit();
		dao.close();
		
	}

	public int getSequence() {
		BoardDAO dao = new BoardDAO();
		int result= dao.getSequence();
		dao.commit();
		dao.close();
		return result;
		
	}

	public int insertBoard(BoardDTO dto) {
		BoardDAO dao = new BoardDAO();
		int result= dao.insertBoard(dto);
		dao.commit();
		dao.close();
		return result;
		
	}

	public void insertImage(int boardNO, List<BoardImageDTO> boardImageList) {
			BoardDAO dao = new BoardDAO();
			if(boardImageList != null) {
				for(BoardImageDTO image: boardImageList) {
					image.setBoardId(boardNO);
					dao.insertImage(image);
				}
			}
			dao.commit(); dao.close();

		
	}

	public List<BoardImageDTO> getImages(BoardDTO dto) {
		BoardDAO dao = new BoardDAO();
		List<BoardImageDTO> data=dao.getImages(dto);
		return data;
	}



}
