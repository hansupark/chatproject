package com.chat.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.chat.vo.ChatVo;

public class ChatDao {

	private static ChatDao dao = new ChatDao();
	private ChatDao() {};
	public static ChatDao getInstance()
	{
		return dao;
	}
	private Connection getConnection()
	{
		Connection conn = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/chatting";
			String user = "root";
			String pwd = "cs1234";
			conn = DriverManager.getConnection(url,user,pwd);
		}
		catch(Exception e)
		{
			System.out.println("Connenction 오류발생 : " + e);
		}
		return conn;
	}
	
	public void close(PreparedStatement psmt, Connection conn)
	{
		
		if(psmt != null)
		{
			try
			{
				psmt.close();
			}
			catch(Exception e)
			{
				System.out.println("close : psmt 오류 발생 : " + e);
			}
		}
		
		if(conn != null)
		{
			try
			{
				conn.close();
			}
			catch(Exception e)
			{
				System.out.println("close : conn 오류 발생 : " + e);
			}
		}
	}
	public ArrayList<ChatVo> getChatList(int chatroomNum) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs= null;
		ArrayList<ChatVo> list = null;
		try
		{
			System.out.println("chatdao 실행");
			list = new ArrayList<ChatVo>();
			conn = getConnection();
			psmt = conn.prepareStatement("select chatContent, user.userName from chat,user where chatroomNum = ? and chat.userNum = user.userNum");
			psmt.setInt(1, chatroomNum);
			rs = psmt.executeQuery();
			while(rs.next())
			{
				ChatVo vo = new ChatVo();
				System.out.println("CHATDAO");
				vo.setChatContent(rs.getString(1));
				vo.setUserName(rs.getString(2));
				list.add(vo);
			}
			rs.close();
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			close(psmt, conn);
		}
		return list;
	}
	
}
