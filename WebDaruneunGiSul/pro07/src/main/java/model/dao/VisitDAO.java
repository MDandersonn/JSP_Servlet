package model.dao;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import database.connect.OracleConnection;
import model.dto.VisitDTO;

public class VisitDAO {
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
	public int insert(VisitDTO dto) {
		int count= session.insert("visitMapper.insert",dto);
		return count;
	}
	public List<VisitDTO> selectAll() {
		List<VisitDTO> data = session.selectList("visitMapper.selectAll");
		return data;
	}
	public VisitDTO selectById(VisitDTO dto) {
		
		VisitDTO data=session.selectOne("visitMapper.selectById",dto);
		return data;
	}

	public int getIdSequence() {
		int count=session.selectOne("visitMapper.getIdSequence");
		return count;
	}

	public int delete(VisitDTO dto) {
		int count = session.delete("visitMapper.delete",dto);
		return count;
	}

	public int update(VisitDTO dto) {
		int count = session.update("visitMapper.update",dto);
		return count;
	}

}
