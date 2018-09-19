package com.chat.service;

import java.util.ArrayList;

import com.chat.dao.HashDao;
import com.chat.vo.HashVo;

public class HashService {
	
	private static HashService service = new HashService();
	private HashService() {};
	
	HashDao dao = HashDao.getInstance();
	public static HashService getInstance()
	{
		return service;
	}
	
	public void setHash(ArrayList<HashVo> list,int chatroomNum)
	{
		dao.setHash(list,chatroomNum);
	}

}
