package com.chat.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chat.service.ChatService;
import com.chat.vo.ChatVo;

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
		ArrayList<ChatVo> list;
		if(req.getParameter("lastNum") != null)
		{
			System.out.println("update getchatList 실행");
			lastNum = Integer.parseInt(req.getParameter("lastNum"));
			list = service.getChatList(chatroomNum,lastNum);
		}
		else if(req.getParameter("firstNum") != null)
		{
			System.out.println("Past getchatList 실행");
			firstNum = Integer.parseInt(req.getParameter("firstNum"));
			list = service.getChatListPast(chatroomNum, firstNum);
		}
		else
		{
			System.out.println("first getchatList 실행");
			list = service.getChatList(chatroomNum);
		}
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		for(int x = list.size() -1  ; x >= 0; x--)
		{
			result.append("[{\"value\" : \"" + list.get(x).getChatContent() +"\"},");
			result.append("{\"value\" : \"" + list.get(x).getUserName() + "\"},");
			result.append("{\"value\" : \"" + list.get(x).getChatNum() + "\"}]");
			if(x != 0)
				result.append(",");
		}
		result.append("]}");
		System.out.println("chatList : " + result.toString());
		res.getWriter().write(result.toString());
		
	}

}
