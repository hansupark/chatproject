package com.chat.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.chat.vo.ChatroomVo;

public class ChatroomDao {
	
	private static ChatroomDao ChatroomDao = new ChatroomDao();
	private ChatroomDao() {};
	public static ChatroomDao getChatroomDao()
	{
		return ChatroomDao;
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
	
	public int createChatroom(ChatroomVo cr,int userNum)
	{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int chatroomNum = -1;
		try
		{
			
			conn = getConnection();
			psmt = conn.prepareStatement("insert into chatroom (chatroomName,chatroomMadeTime,userNum) values(?,?,?)");
			psmt.setString(1,cr.getChatroomName());
			psmt.setDate(2,new java.sql.Date((cr.getChatroomMadeTime()).getTime()));
			psmt.setInt(3, userNum);
			psmt.executeUpdate();
			psmt = conn.prepareStatement("select LAST_INSERT_ID()"); //가장 마지막에 들어간 채팅방의 id를 갖고옴 
			rs = psmt.executeQuery();
			while(rs.next())
			{
				chatroomNum = rs.getInt(1);
				System.out.println("chatroomnum in dao : " + chatroomNum);
			}
			
		}
		catch(Exception e)
		{
			System.out.println("chatroom create error : " + e);
		}
		
		finally {
			close(psmt, conn);
			
		}
		return chatroomNum;
	}
}
