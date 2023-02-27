package model.dao;

import org.apache.ibatis.session.SqlSession;

import database.connect.OracleConnection;
import model.dto.UserDTO;

public class UserDAO {

	private SqlSession session = OracleConnection.getSqlSession();
	
	public void commit() {
		session.commit();
	}
	
	public void rollback() {
		session.rollback();
	}
	
	public void close() {
		session.close();
	}

	public int insert(UserDTO dto) {
		int count = session.insert("userMapper.insert", dto);
		return count;
	}

	public UserDTO selectUser(UserDTO dto) {
		UserDTO data = session.selectOne("userMapper.selectUser", dto);
		return data;
	}

	public int update(UserDTO dto) {
		int count=session.update("userMapper.update",dto);
		
		return count;
	}
}
