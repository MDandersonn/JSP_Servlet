package model.dto;

import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

public class MemberDTO implements HttpSessionBindingListener{
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String joinDate;
	private static int TOTAL_USER = 0 ;
	
	public static int getTOTAL_USER() {
		return TOTAL_USER;
	}
	public static void setTOTAL_USER(int tOTAL_USER) {
		TOTAL_USER = tOTAL_USER;
	}
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("dto가 세션에 바인딩되었다@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		TOTAL_USER++;
	}
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		TOTAL_USER--;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", pwd=" + pwd + ", name=" + name + ", email=" + email + ", joinDate=" + joinDate
				+ "]";
	}
	
	
	

}
