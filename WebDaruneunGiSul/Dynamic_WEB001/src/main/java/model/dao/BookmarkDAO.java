package model.dao;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import database.connect.OracleConnection;
import model.dto.BookmarkDTO;
import model.dto.UserDTO;


public class BookmarkDAO {
	private SqlSession session= OracleConnection.getSqlSession();
	
	public int insert(BookmarkDTO dto) {
//		원래는 String context
		
//		오라클연결해서 
//		String res= session.selectOne("visitMapper.connectTest");
//		System.out.println(res);
//		return 0;
		
		
		
//		전달해주는게 context
//		int count= session.insert("visitMapper.insert",context);
//		return count;
		
		int count= session.insert("bookmarkMapper.insert",dto);
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

	public List<BookmarkDTO> selectAll(UserDTO dto) {
		List<BookmarkDTO> data = session.selectList("bookmarkMapper.selectAll",dto);
		return data;
	}

	public BookmarkDTO selectId(BookmarkDTO dto) {
		BookmarkDTO data =session.selectOne("bookmarkMapper.selectId", dto);
		
		return data;
	}

	public int update(BookmarkDTO dto) {
		int count= session.update("bookmarkMapper.update",dto);
		return count;
	}

	public int delete(BookmarkDTO dto) {
		int count= session.delete("bookmarkMapper.delete",dto);
		return count;
	}

	public int getId() {
//		if (session.selectOne("bookmarkMapper.getId")==null) {
//			return 1;
//		}
		int count =session.selectOne("bookmarkMapper.getId");
		return count;
		
		
	}


	
	

}
