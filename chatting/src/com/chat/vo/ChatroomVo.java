package com.chat.vo;

public class ChatroomVo {

	private int chatroomNum;
	private String chatroomName;
	private String chatroomMadeTime;
	private String chatroomSubject;
	private int userNum;
	public String getChatroomSubject() {
		return chatroomSubject;
	}
	public void setChatroomSubject(String chatroomSubject) {
		this.chatroomSubject = chatroomSubject;
	}
	public int getChatroomNum() {
		return chatroomNum;
	}
	public void setChatroomNum(int chatroomNum) {
		this.chatroomNum = chatroomNum;
	}
	public String getChatroomName() {
		return chatroomName;
	}
	public void setChatroomName(String chatroomName) {
		this.chatroomName = chatroomName;
	}
	public String getChatroomMadeTime() {
		return chatroomMadeTime;
	}
	public void setChatroomMadeTime(String chatroomMadeTime) {
		this.chatroomMadeTime = chatroomMadeTime;
	}
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
}
