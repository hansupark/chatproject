package com.chat.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class LastChatDao {

	private static LastChatDao dao = new LastChatDao();
	private LastChatDao() {};
	public static LastChatDao getInstance()
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
	
	public int checkLastChat(int chatroomNum, int userNum) //lastChat에 게시판에 대한 lastchat의 기록이 있는지 검사 있으면 update 없으면 insert
	{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs= null;
		try
		{
			conn = getConnection();
			psmt = conn.prepareStatement("select * from lastChat where chatroomNum = ? and userNum = ?");
			psmt.setInt(1, chatroomNum);
			psmt.setInt(2, userNum);
			rs = psmt.executeQuery();
			while(rs.next())
			{
				return 1;
			}
		}
		catch(Exception e)
		{
			System.out.println("error : " + e);
		}
		finally
		{
			close(psmt, conn);
		}
		return -1;
		
	}
	public int saveLastChatNum(int status, int chatroomNum, int userNum,int chatNum) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs= null;
		try
		{
			conn = getConnection();
			String sql;
			if(status == 1)
				sql = "update lastchat set chatroomNum = ? , userNum = ?, chatNum = ?";
			else 
				sql = "insert into lastchat (chatroomNum,userNum,chatNum) values(?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, chatroomNum);
			psmt.setInt(2, userNum);
			psmt.setInt(3,chatNum);
			return psmt.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("error : " + e);
		}
		finally
		{
			close(psmt, conn);
		}
		return -1;
	}
	
	public int getLastChatNum(int chatroomNum, int userNum) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs= null;
		int lastNum = -1;
		try
		{
			conn = getConnection();
			psmt = conn.prepareStatement("select chatNum from lastchat where chatroomNum = ? and userNum = ?");
			psmt.setInt(1, chatroomNum);
			psmt.setInt(2, userNum);
			rs = psmt.executeQuery();			
			while(rs.next())
			{
				lastNum = rs.getInt(1);
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
		return lastNum;
	}
}
