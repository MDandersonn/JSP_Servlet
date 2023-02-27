package model.dto;

import java.sql.Date;
//sql.Date로하면 년-월-일 로 표기된다.
public class BoardDTO {
	private int articleNO;
	private int parentNO;
	private String title;
	private String content;
	private String imageFileName;
	private Date writedate;
	private String id;
	private int recUP;
	private int recDOWN
	;
	public int getRecUP() {
		return recUP;
	}
	public void setRecUP(int recUP) {
		this.recUP = recUP;
	}
	public int getRecDOWN() {
		return recDOWN;
	}
	public void setRecDOWN(int recDOWN) {
		this.recDOWN = recDOWN;
	}
	public int getViewCNT() {
		return viewCNT;
	}
	public void setViewCNT(int viewCNT) {
		this.viewCNT = viewCNT;
	}
	private int viewCNT;
	
	
	public int getArticleNO() {
		return articleNO;
	}
	public void setArticleNO(int articleNO) {
		this.articleNO = articleNO;
	}
	public int getParentNO() {
		return parentNO;
	}
	public void setParentNO(int parentNO) {
		this.parentNO = parentNO;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "BoardDTO [articleNO=" + articleNO + ", parentNO=" + parentNO + ", title=" + title + ", content="
				+ content + ", imageFileName=" + imageFileName + ", writedate=" + writedate + ", id=" + id + "]";
	}
	
	

}
