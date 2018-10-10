package com.chat.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chat.service.ChatService;
import com.chat.vo.ChatVo;
import com.chat.vo.UserVo;

public class ChatGetListController implements Controller{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset = UTF-8");
		ChatService service = ChatService.getInstance();
		int chatroomNum = Integer.parseInt(req.getParameter("chatroomNum"));
		int lastNum;
		int firstNum;
		//lastChatNum액션을 위한 변수들 
		//
		ArrayList<ChatVo> list;
		if(req.getParameter("lastChatNum") != null) //최근에 봤던 내용으로 돌아가는 액션
		{
			System.out.println("lastChatNum getchatList 실행");
			System.out.println("lastNum : " + Integer.parseInt(req.getParameter("lastChatNum")));
			System.out.println("endNum : " + Integer.parseInt(req.getParameter("endChatNum")));
			list = service.getReturnChatList(chatroomNum,Integer.parseInt(req.getParameter("lastChatNum")),Integer.parseInt(req.getParameter("endChatNum")));
		}
		else if(req.getParameter("lastNum") != null) //계속 채팅 받아오는 액션
		{
			System.out.println("update getchatList 실행");
			lastNum = Integer.parseInt(req.getParameter("lastNum"));
			System.out.println("lastNum : " + lastNum);
			list = service.getChatList(chatroomNum,lastNum);
		}
		else if(req.getParameter("firstNum") != null) //과거 채팅내용 가져오는 액션
		{
			System.out.println("Past getchatList 실행");
			firstNum = Integer.parseInt(req.getParameter("firstNum"));
			list = service.getChatListPast(chatroomNum, firstNum);
		}
		else //맨처음 게시판 들어갈때 채팅가져오는 액션
		{
			System.out.println("first getchatList 실행");
			list = service.getChatList(chatroomNum);
		}
		
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		for(int x = list.size() -1  ; x >= 0; x--) //dao에서 가져올때 내림차순으로 가져오기떄문에 index를 거꾸로 접근
		{
			result.append("[{\"value\" : \"" + list.get(x).getChatContent() +"\"},");
			result.append("{\"value\" : \"" + list.get(x).getUserName() + "\"},");
			result.append("{\"value\" : \"" + list.get(x).getChatNum() + "\"}]");
			if(x != 0)
				result.append(",");
		}
		
		if(req.getParameter("firstNum") == null && req.getParameter("lastNum") == null && req.getParameter("lastChatNum") == null) //처음 가져올때만 lastChatNum이 필요
		{ 
			HttpSession session = req.getSession();
			int userNum = ((UserVo) session.getAttribute("c_user")).getUserNum();
			int lastChatNum = service.getLastChatNum(chatroomNum, userNum);
			result.append("],\"lastChatNum\" : \"" + lastChatNum + "\"}");
		}
		else
			result.append("]}");
		
		System.out.println("chatList : " + result.toString());
		res.getWriter().write(result.toString());
		
	}

}
