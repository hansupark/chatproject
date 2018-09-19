package com.chat.vo;

import java.util.Date;

public class ChatroomVo {

	private int chatroomNum;
	private String chatroomName;
	private Date chatroomMadeTime;
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
	public Date getChatroomMadeTime() {
		return chatroomMadeTime;
	}
	public void setChatroomMadeTime(Date chatroomMadeTime) {
		this.chatroomMadeTime = chatroomMadeTime;
	}
	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
}
