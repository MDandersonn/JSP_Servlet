package model.dto;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

public class UserDTO implements HttpSessionBindingListener {
/*
 * 바인딩은 유저DTO가 세션에 바인딩될때만 동작
 * 세션에 데이터를 붙였을때
 * 	로그인컨트롤러에 session.setAttribute("user", userData);//이게 바인드이게 userData가 DTO임.
 */
	
	private String userId;
	private String password;
	private String email;
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", password=" + password + ", email=" + email + "]";
	}
	
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
//		셋으로 설정할때
		HttpSessionBindingListener.super.valueBound(event);
		System.out.println("session binding: "+event.getName()+ "-"+ event.getValue());
		HttpSession session = event.getSession();
		session.setAttribute("login", true);
	}
	
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
//		제거할때, 세션무효화시킬떄
		HttpSessionBindingListener.super.valueUnbound(event);
		System.out.println("session unbinding: "+event.getName()+ "-"+ event.getValue());
	}
}
