package com.chat.service;

import java.util.ArrayList;

import com.chat.dao.ChatDao;
import com.chat.dao.LastChatDao;
import com.chat.vo.ChatVo;

public class ChatService {

	private static ChatService service = new ChatService();
	private ChatService() {};
	public ChatDao dao = ChatDao.getInstance();
	public LastChatDao dao_2 = LastChatDao.getInstance();
	public static ChatService getInstance()
	{
		return service;
	}
	//채팅 액션들
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
	
	//lastChatNum 액션들
	public int saveLastChatNum(int status,int chatroomNum, int userNum, int chatNum)
	{
		return dao_2.saveLastChatNum(status,chatroomNum, userNum, chatNum);
	}
	public int getLastChatNum(int chatroomNum,int userNum)
	{
		return dao_2.getLastChatNum(chatroomNum, userNum);
	}
	public int checkLastChatNum(int chatroomNum, int userNum)
	{
		return dao_2.checkLastChat(chatroomNum, userNum);
	}
	
	public ArrayList<ChatVo> getReturnChatList(int chatroomNum, int parseInt, int endNum) { //최근 읽었던 채팅부터 시작
		// TODO Auto-generated method stub
		return dao.getReturnChatList(chatroomNum,parseInt,endNum);
	}
}
