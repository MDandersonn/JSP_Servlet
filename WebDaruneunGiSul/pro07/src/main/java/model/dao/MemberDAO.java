package model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import database.connect.OracleConnection;
import model.dto.MemberDTO;

public class MemberDAO {

	private SqlSession session = OracleConnection.getSqlSession();

	public void close() {
		session.close();

	}

	public void rollback() {
		session.rollback();
	}

	public void commit() {
		session.commit();
	}

	public List<MemberDTO> selectAll() {
		List<MemberDTO> data = session.selectList("memberMapper.selectAll");
		return data;
	}

	public int insertMember(MemberDTO dto) {
		int count = session.insert("memberMapper.insertMember", dto);

		return count;
	}

	public MemberDTO selectById(MemberDTO dto) {
		MemberDTO data = session.selectOne("memberMapper.selectById", dto);
		return data;
	}

	public int updateMember(MemberDTO dto) {
		int count = session.insert("memberMapper.updateMember", dto);

		return count;
	}

	public int deleteMember(MemberDTO dto) {
		int count = session.delete("memberMapper.deleteById", dto);
		return count;
	}

	public List<MemberDTO> selectByName(MemberDTO dto) {
		List<MemberDTO> data = session.selectList("memberMapper.selectByName",dto);
		return data;
		
	}

}
