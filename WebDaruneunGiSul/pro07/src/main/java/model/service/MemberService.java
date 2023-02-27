package model.service;

import java.util.List;

import model.dao.MemberDAO;
import model.dto.MemberDTO;

public class MemberService {

	public List<MemberDTO> selectAll() {
		MemberDAO dao= new MemberDAO();
		List<MemberDTO> data= dao.selectAll();
		dao.close();
		return data;
	}

	public int insertMember(MemberDTO dto) {
		MemberDAO dao= new MemberDAO();
		MemberDTO dtoCheck = dao.selectById(dto);
		if(dtoCheck==null) {//아이디가 존재하지않음
			int count=dao.insertMember(dto);
			if(count==1) {
				dao.commit();
				dao.close();
				return 1;
			}
			dao.rollback();
			dao.close();
			return 0;//아이디는 존재하지않는데 다른문제로 안됨
		}
		
		//아이디가 존재함
		dao.rollback();
		dao.close();
		return -1;
		
	}

	public MemberDTO selectById(MemberDTO dto) {
		MemberDAO dao= new MemberDAO();
		MemberDTO data= dao.selectById(dto);
		dao.close();
		return data;
	}

	public boolean updateMember(MemberDTO dto) {
		MemberDAO dao= new MemberDAO();
		int count=dao.updateMember(dto);
		
		if(count==1) {
			dao.commit();
			dao.close();
			
			return true;
		}
		dao.rollback();
		dao.close();
		return false;
	}

	public boolean deleteById(MemberDTO dto) {
		MemberDAO dao= new MemberDAO();
		int count=dao.deleteMember(dto);
		
		if(count==1) {
			dao.commit();
			dao.close();
			
			return true;
		}
		dao.rollback();
		dao.close();
		return false;
	}

	public List<MemberDTO> selectByName(MemberDTO dto) {
		MemberDAO dao= new MemberDAO();
		List<MemberDTO> data= dao.selectByName(dto); 
		return data;
	}


}
