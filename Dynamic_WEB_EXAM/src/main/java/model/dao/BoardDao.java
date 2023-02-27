package model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import database.connect.OracleConnection;
import model.dto.Board;


public class BoardDao {
private SqlSession session= OracleConnection.getSqlSession();
	
	public int insertBoard(Board dto) {
		int count= session.insert("boardMapper.insert",dto);
		return count;
	}
	public int getBoardNum() {
		int count=session.selectOne("boardMapper.getBoardNum");
		return count;
	}

	public void commit() {
		session.commit();
	}

	public void close() {
		
		session.close();
	}

	public void rollback() {
		session.rollback();
	}
	public List<Board> selectAll() {
		List<Board> data = session.selectList("boardMapper.selectAll");
		return data;
	}
	public Board selectId(int num) {
		
		Board data=session.selectOne("boardMapper.selectId",num);
		return data;
	}
	public Board selectNum(Board board) {
		
		Board data=session.selectOne("boardMapper.selectNum",board);
		return data;
	}
	public int updateBoard(Board dto) {
		int count = session.update("boardMapper.update",dto);
		return count;
	}
	



}
