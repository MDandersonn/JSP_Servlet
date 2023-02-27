package model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import database.connect.OracleConnection;
import model.dto.BoardDTO;
import model.dto.BoardImageDTO;

public class BoardDAO  {
	private SqlSession session= OracleConnection.getSqlSession();
	public void commit() {
		session.commit();
	}
	public void close() {
		
		session.close();
	}
	public void rollback() {
		session.rollback();
	}

	public List<BoardDTO> selectAll() {
		List<BoardDTO> data = session.selectList("boardMapper.selectAll");
		return data;
	}
	public int totalRowNum() {
		int row = session.selectOne("boardMapper.rowNum");
		return row;
	}
	public List<BoardDTO> selectByPage(Map<String,Integer> map) {
		List<BoardDTO> data = session.selectList("boardMapper.selectByPage",map);
		return data;
	}
	public BoardDTO selectByArticleNO(BoardDTO dto2) {
		BoardDTO data = session.selectOne("boardMapper.selectByArticleNO",dto2);
		return data;
	}
	public int recUP(BoardDTO dto) {
		int result= session.update("boardMapper.recUP",dto);
		return result;
	}
	public int recDOWN(BoardDTO dto) {
		int result= session.update("boardMapper.recDOWN",dto);
		return result;
	}
	public int removeRecDOWN(BoardDTO dto) {
		int result= session.update("boardMapper.removeRecDOWN",dto);
		return result;
	}
	public int removeRecUP(BoardDTO dto) {
		int result= session.update("boardMapper.removeRecUP",dto);
		return result;
	}
	public int getSequence() {
		int result= session.selectOne("boardMapper.getSequence");
		return result;
	}
	public int insertBoard(BoardDTO dto) {
		int result= session.insert("boardMapper.insertBoard",dto);
		return result;
	}

	public void insertImage(BoardImageDTO image) {
		int result= session.insert("boardMapper.insertImage",image);
		
	}
	public List<BoardImageDTO> getImages(BoardDTO dto) {
		List<BoardImageDTO> data = session.selectList("boardMapper.getImages",dto);
		return data;
	}
}
