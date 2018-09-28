package com.chat.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chat.service.ChatService;

public class ChatAddChatController implements Controller{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset = UTF-8"); 
		ChatService service = ChatService.getInstance();
		String chatContent = req.getParameter("chatContent");
		int chatroomNum = Integer.parseInt(req.getParameter("chatroomNum"));
		int userNum = Integer.parseInt(req.getParameter("userNum"));
		res.getWriter().write(service.addChat(chatContent,userNum,chatroomNum));
	}

	
}
