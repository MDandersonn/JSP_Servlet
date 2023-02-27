package model.dto;

public class Role {

	private String type;
	
	public Role(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public boolean isAdmin() {
		return type.equals("ADMIN");
	}
	
	public boolean isUser() {
		return type.equals("USER");
	}
	
	public boolean isStaff() {
		return type.equals("STAFF");
	}
	
	public boolean isGuest() {
		return type.equals("GUEST");
	}

	@Override
	public String toString() {
		return "Role [type=" + type + "]";
	}
	
}
