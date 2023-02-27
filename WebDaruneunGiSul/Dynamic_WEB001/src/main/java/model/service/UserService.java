package model.service;

import model.dao.UserDAO;
import model.dto.UserDTO;

public class UserService {

	public int add(UserDTO dto) {
		UserDAO dao = new UserDAO();
		
		UserDTO data = dao.selectUser(dto);
		
		if(data == null) {//해당 아이디가 존재하지 않을때 
			int count = dao.insert(dto); //
			if(count == 1) {
				dao.commit(); dao.close();
				return 1; //잘 입력되었을때 회원가입 성공
			}
			dao.rollback(); dao.close();
			return 0;//아이디는 존재하지않으나 .다른이유로 회원가입 실패
		}
		dao.close();
		return -1;//해당 아이디가 있을때
	}

	public UserDTO login(UserDTO dto) {
		UserDAO dao = new UserDAO();
		UserDTO data =dao.selectUser(dto);
		if(data==null) {
			//유저id가 존재하지않을때
			dao.close();
			return null;
			
		}else {//유저id가 존재할떄
			if(data.getPassword().equals(dto.getPassword())) {
				//비번일치할때
				dao.close();
				return data;
			}
		}
//		유저id가 존재는하지만 비번이다를떄
		dao.close();
		return null;
	}

	public boolean update(UserDTO userData, UserDTO dto, String password) {
		UserDAO dao = new UserDAO();
		
		UserDTO data= dao.selectUser(userData);
		if(data==null) {//id가 없는경우
			dao.close();
			return false;
			
		}else {//id가 존재하는경우
			if(data.getPassword().equals(password)) {//id와 비번도 일치하는경우
				dto.setUserId(data.getUserId());
				if(dto.getPassword().isEmpty()) {//
					dto.setPassword(data.getPassword());//기존패스워드그대로
				}
				int count= dao.update(dto);
				if(count==1) {
					dao.commit(); dao.close();
					return true;
				}
			}
			
		}
		dao.rollback();
		dao.close();
		return false;
	}

}
