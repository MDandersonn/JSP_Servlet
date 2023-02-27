package model.dto;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

public class UserDTO implements HttpSessionBindingListener {

	private String userId;
	private String password;
	private String email;
	private String pImg;
	
	public String getpImg() {
		return pImg;
	}

	public void setpImg(String pImg) {
		this.pImg = pImg;
	}

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
		HttpSessionBindingListener.super.valueBound(event);
		HttpSession session = event.getSession();
		session.setAttribute("login", true);
	}
	
}
