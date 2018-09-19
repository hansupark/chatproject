package com.chat.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpUtil {

	public static void forward(HttpServletRequest req, HttpServletResponse res, String path) {
	      try {
	         RequestDispatcher dispatcher = req.getRequestDispatcher(path);
	         dispatcher.forward(req, res);
	      } catch (Exception e) {
	         System.out.println("forward 오류: " + e);
	      }
	   }
	
	
}
