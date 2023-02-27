package model.dao;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import database.connect.OracleConnection;
import model.dto.VisitDTO;



public class VisitDAO {
	private SqlSession session= OracleConnection.getSqlSession();
	
	public int insert(VisitDTO dto) {
		int count= session.insert("visitMapper.insert",dto);
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

	public List<VisitDTO> selectAll() {
		List<VisitDTO> data = session.selectList("visitMapper.selectAll");
		return data;
	}
	
	public VisitDTO selectId(VisitDTO dto) {
		
		VisitDTO data=session.selectOne("visitMapper.selectId",dto);
		return data;
	}

	public int getId() {
		int count=session.selectOne("visitMapper.getId");
		return count;
	}

	public int delete(VisitDTO dto) {
		int count = session.delete("visitMapper.delete",dto);
		System.out.println(count);
		return count;
	}

	public int update(VisitDTO dto) {
		int count = session.update("visitMapper.update",dto);
		System.out.println(count);
		return count;
	}

}
