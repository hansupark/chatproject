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
		System.out.println("chatroomNum = " + chatroomNum);
		ArrayList<ChatVo> list = service.getChatList(chatroomNum);
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		for(int x = 0 ; x < list.size(); x++)
		{
			result.append("[{\"value\" : \"" + list.get(x).getChatContent() +"\"},");
			result.append("{\"value\" : \"" + list.get(x).getUserName() + "\"}]");
			if(x != list.size() - 1)
				result.append(",");
		}
		result.append("]}");
		System.out.println("chatList : " + result.toString());
		res.getWriter().write(result.toString());
		
	}

}
