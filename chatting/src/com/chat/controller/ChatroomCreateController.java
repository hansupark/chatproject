package com.chat.controller;

import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chat.service.ChatroomService;
import com.chat.service.HashService;
import com.chat.vo.ChatroomVo;
import com.chat.vo.HashVo;
import com.chat.vo.UserVo;

public class ChatroomCreateController implements Controller{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset = UTF-8");
		ChatroomService service = ChatroomService.getChatroomService();
		HashService service_2 = HashService.getInstance();
		HttpSession session = req.getSession();
		UserVo uservo = (UserVo) session.getAttribute("c_user");
		int userNum = uservo.getUserNum();
		String chatroomName = req.getParameter("chatroomName");
		String chatroomSubject = req.getParameter("chatroomSubject");
		//게시판 생성
		ChatroomVo cv = new ChatroomVo();
		cv.setChatroomName(chatroomName);
		cv.setUserNum(userNum);
		cv.setChatroomSubject(chatroomSubject);
		//해쉬태그 리스트를 생성한다.
		ArrayList<HashVo> list = new ArrayList<HashVo>();
		for(int x = 0 ; x < 5 ; x++)
		{
			HashVo vo = new HashVo();
			vo.setHashName(req.getParameter("hash" + (x+1)));
			System.out.println("setHashName : " + vo.getHashName());
			list.add(vo);
		}
		//System.out.println("chatroomNum : " + service.createChatroom(cv, userNum));
		//
		int chatroomNum = service.createChatroom(cv, userNum);
		System.out.println("chatroomNum : " + chatroomNum);
		if(chatroomNum != -1)
		{
			service_2.setHash(list,chatroomNum);
			HttpUtil.forward(req, res, "/feed.jsp");
			return;
		}
		else
		{
		/*
		 	HttpUtil.forward(req, res, "chatList.jsp");
			return;
		 */
		}
		
		
	}
	
}
