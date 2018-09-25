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
}
