package model.dto;

public class BookmarkDTO {
	private int id;
	private String url;
	private String name;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "bookmarkDTO [id=" + id + ", url=" + url + ", name=" + name + "]";
	}

	
	

	
}
