package com.safefood.dto;

public class CommentDTO {

	int cnum;
	int bid;
	String ccontent;
	String user_id;
	String user_name;
	
	public int getCnum() {
		return cnum;
	}
	public void setCnum(int cnum) {
		this.cnum = cnum;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getCcontent() {
		return ccontent;
	}
	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	@Override
	public String toString() {
		return "CommentDTO [cnum=" + cnum + ", bid=" + bid + ", ccontent=" + ccontent + ", user_id=" + user_id
				+ ", user_name=" + user_name + "]";
	}
	public CommentDTO(int cnum, int bid, String ccontent, String user_id, String user_name) {
		super();
		this.cnum = cnum;
		this.bid = bid;
		this.ccontent = ccontent;
		this.user_id = user_id;
		this.user_name = user_name;
	}
	public CommentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
