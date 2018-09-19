package com.chat.service;

import com.chat.dao.ChatroomDao;
import com.chat.vo.ChatroomVo;

public class ChatroomService {

	private static ChatroomService ChatroomService = new ChatroomService();
	private ChatroomService() {};
	public static ChatroomService getChatroomService()
	{
		return ChatroomService;
	}
	ChatroomDao dao = ChatroomDao.getChatroomDao();
	
	public int createChatroom(ChatroomVo cr, int userNum)
	{
		//System.out.println("chatroomnum in service : " + dao.createChatroom(cr, userNum));
		return dao.createChatroom(cr, userNum);
	}
}
