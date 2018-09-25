package com.chat.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chat.service.ChatroomService;
import com.chat.service.HashService;
import com.chat.vo.ChatroomVo;
import com.chat.vo.HashVo;

public class ChatroomGetList implements Controller{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("getChatroomList 실행");
		ArrayList<ChatroomVo> list = null;
		ArrayList<HashVo> hashList = null;
		ChatroomService service = ChatroomService.getChatroomService();
		HashService service_2 = HashService.getInstance();
		res.setContentType("text/html; charset = UTF-8");
		req.setCharacterEncoding("UTF-8");
		String type = req.getParameter("type");
		list = service.getChatroomList(type);
		StringBuffer result = new StringBuffer("");
		result.append("{\"result\":[");
		for(int x = 0 ; x < list.size(); x++)
		{
			//System.out.println("chatname : " + list.get(x).getChatroomName());
			hashList = service_2.getHashList(list.get(x).getChatroomNum());			
			result.append("[{\"value\" : \"" + list.get(x).getChatroomName() + "\"},");
			for(int y = 0 ; y < hashList.size() ; y++)
			{
				if(y == hashList.size() - 1)
				{
					result.append("{\"value\" : \"" + hashList.get(y).getHashName() + "\"}]");
				}
				else
					result.append("{\"value\" : \"" + hashList.get(y).getHashName() + "\"},");
			}
			if(x != list.size() - 1) result.append(",");
		}
		result.append("]}");
		System.out.println(result.toString());
		res.getWriter().write(result.toString());
	}

}
