package com.chat.service;

import java.util.ArrayList;

import com.chat.dao.ChatDao;
import com.chat.vo.ChatVo;

public class ChatService {

	private static ChatService service = new ChatService();
	private ChatService() {};
	public ChatDao dao = ChatDao.getInstance();
	public static ChatService getInstance()
	{
		return service;
	}
	public ArrayList<ChatVo> getChatList(int chatroomNum) {
		// TODO Auto-generated method stub
		return dao.getChatList(chatroomNum);
	}
	public ArrayList<ChatVo> getChatList(int chatroomNum, int lastNum) {
		// TODO Auto-generated method stub
		return dao.getChatList(chatroomNum,lastNum);
	}
	public ArrayList<ChatVo> getChatListPast(int chatroomNum, int firstNum) {
		// TODO Auto-generated method stub
		return dao.getChatListPast(chatroomNum,firstNum);
	}
	public int addChat(String chatContent, int userNum, int chatroomNum) {
		// TODO Auto-generated method stub
		return dao.addChat(chatContent,userNum,chatroomNum);
	}
}
