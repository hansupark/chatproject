package com.chat.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class fristcontroller
 */
public class Frontcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HashMap<String,Controller> list = new HashMap<String,Controller>();
    public void init(ServletConfig config) throws ServletException
    {
    	list.put("/userInsert.do",new UserInsertController());
    	list.put("/userLogin.do",new UserLoginController());
    	list.put("/chatroomCreate.do",new ChatroomCreateController());
    }
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = uri.substring(contextPath.length());
		System.out.println("path : " + path);
		Controller controller = list.get(path);
		controller.execute(request, response);
	}



}