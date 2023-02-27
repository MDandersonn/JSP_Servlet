package model.dto;

// 마이바티스 insert는 하나밖에변수못받음 그래서 멤버변수로받아야함
// 데이터베이스에저장할 멤버변수를 담아놈
public class VisitDTO {
	private int id;
	private String context;
	private String nickname;
	

	@Override
	public String toString() {
		return "VisitDTO [id=" + id + ", context=" + context + ", nickname=" + nickname + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	
}
