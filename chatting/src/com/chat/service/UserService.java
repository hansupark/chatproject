package com.chat.service;

import com.chat.dao.UserDao;
import com.chat.vo.UserVo;

public class UserService {

	private static UserService UserService = new UserService();
	private UserService() {};
	public static UserService getUserService()
	{
		return UserService;
	}
	
	UserDao UDao = UserDao.getUserDao();
	
	public int insertUser(UserVo user)
	{
		return UDao.insertUser(user);
	}
	
	public UserVo SearchUser(String id)
	{
		return UDao.SearchUser(id);
	}
	public String loginUser(String id) {
		// TODO Auto-generated method stub
		return UDao.loginUser(id);
	}
	
}
