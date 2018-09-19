package com.chat.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chat.service.UserService;
import com.chat.vo.UserVo;

public class UserInsertController implements Controller{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// TODO Auto-generated method stub
		UserService s = UserService.getUserService();
		req.setCharacterEncoding("UTF-8");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String userName = req.getParameter("name");
		UserVo user = new UserVo();
		user.setId(id);
		user.setPw(pw);
		user.setUserName(userName);
		if(s.insertUser(user) != -1)
		{
			System.out.println("데이터베이스 오류 발생");
		}
		else
		{
			HttpUtil.forward(req, res,"/main.jsp");
			return;
		}
	}



}
