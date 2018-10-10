package com.chat.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chat.service.ChatService;

public class ChatroomExitController implements Controller{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("exit controller 실행");
		req.setCharacterEncoding("UTF-8");
		ChatService service = ChatService.getInstance();
		int lastChatNum = Integer.parseInt(req.getParameter("lastChatNum"));
		int userNum = Integer.parseInt(req.getParameter("userNum"));
		int chatroomNum = Integer.parseInt(req.getParameter("chatroomNum"));
		int status = service.checkLastChatNum(chatroomNum, userNum); // 저장을 할지 갱신을 할지 정함
		service.saveLastChatNum(status,chatroomNum, userNum, lastChatNum);
		
	}

}
