package com.chat.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chat.service.UserService;
import com.chat.vo.UserVo;

public class UserLoginController implements Controller{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset = UTF-8");
		UserService s = UserService.getUserService();
		String id = req.getParameter("id");
		//System.out.println(" con id : " + id);
		String pw = req.getParameter("pw");
		//System.out.println(" con pw : " + pw);
		String c_pw = s.loginUser(id);
		//System.out.println(" con c_pw : " + c_pw);
		if(c_pw == null)
		{
			System.out.println("로그인 실패");
			res.getWriter().write("0");
		}
		else if(c_pw.equals(pw))
		{
			HttpSession session = req.getSession();
			UserVo vo = s.SearchUser(id);
			session.setAttribute("c_user",vo);
			res.getWriter().write("2");
		}
		else if(!(c_pw.equals(pw)))
		{
			res.getWriter().write("1");
		}
	}

}
