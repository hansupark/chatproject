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
	public ArrayList<ChatVo> getChatList(int chatroomNum) { //처음 chatlist불러올때
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs= null;
		ArrayList<ChatVo> list = null;
		try
		{
			//System.out.println("chatdao 실행");
			list = new ArrayList<ChatVo>();
			conn = getConnection();
			psmt = conn.prepareStatement("select chatContent, user.userName,chatNum from chat,user where chatroomNum = ? and chat.userNum = user.userNum order by chatTime desc Limit 3");
			psmt.setInt(1, chatroomNum);
			rs = psmt.executeQuery();
			while(rs.next())
			{
				ChatVo vo = new ChatVo();
				//System.out.println("CHATDAO");
				vo.setChatContent(rs.getString(1));
				vo.setUserName(rs.getString(2));
				vo.setChatNum(rs.getInt(3));
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
	public ArrayList<ChatVo> getChatList(int chatroomNum, int lastNum) {//주기적으로 chatlist갱신할때
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs= null;
		ArrayList<ChatVo> list = null;
		try
		{
			System.out.println("chatdao Recentupdate 실행");
			list = new ArrayList<ChatVo>();
			conn = getConnection();
			psmt = conn.prepareStatement("select chatContent, user.userName,chatNum from chat,user where chatroomNum = ? and chat.userNum = user.userNum and chatNum > ? order by chatTime desc");
			psmt.setInt(1, chatroomNum);
			psmt.setInt(2, lastNum);
			rs = psmt.executeQuery();
			while(rs.next())
			{
				ChatVo vo = new ChatVo();
				//System.out.println("CHATDAO");
				vo.setChatContent(rs.getString(1));
				vo.setUserName(rs.getString(2));
				vo.setChatNum(rs.getInt(3));
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
	//past에서 오류
	public ArrayList<ChatVo> getChatListPast(int chatroomNum, int firstNum) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs= null;
		ArrayList<ChatVo> list = null;
		try
		{
			System.out.println("chatdao Pastupdate 실행");
			System.out.println("PastUpdate chatroomNum : " + chatroomNum);
			System.out.println("PastUpdate firstNum : " + firstNum);
			list = new ArrayList<ChatVo>();
			conn = getConnection();
			psmt = conn.prepareStatement("select chatContent, user.userName,chatNum from chat,user where chatroomNum = ? and chatNum < ? and chat.userNum = user.userNum  order by chatTime desc Limit 3");
			psmt.setInt(1, chatroomNum);
			psmt.setInt(2, firstNum);
			rs = psmt.executeQuery();
			while(rs.next())
			{
				ChatVo vo = new ChatVo();
				//System.out.println("CHATDAO");
				vo.setChatContent(rs.getString(1));
				vo.setUserName(rs.getString(2));
				vo.setChatNum(rs.getInt(3));
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
	public int addChat(String chatContent, int userNum, int chatroomNum) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs= null;
		try
		{
			conn = getConnection();
			psmt = conn.prepareStatement("insert into chat (chatContent,userNum,chatroomNum,chatTime) values(?,?,?,now())");
			psmt.setString(1, chatContent);
			psmt.setInt(2, userNum);
			psmt.setInt(3,chatroomNum);
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
	public ArrayList<ChatVo> getReturnChatList(int chatroomNum, int parseInt, int endNum) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs= null;
		ArrayList<ChatVo> list = null;
		try
		{
			list = new ArrayList<ChatVo>();
			conn = getConnection();
			psmt = conn.prepareStatement("select chatContent, user.userName,chatNum from chat,user where chatroomNum = ? and (chatNum between ? and ?)  and chat.userNum = user.userNum  order by chatTime desc");
			psmt.setInt(1, chatroomNum);
			psmt.setInt(2, parseInt);
			psmt.setInt(3, endNum - 1);
			rs = psmt.executeQuery();
			while(rs.next())
			{
				ChatVo vo = new ChatVo();
				//System.out.println("CHATDAO");
				vo.setChatContent(rs.getString(1));
				vo.setUserName(rs.getString(2));
				vo.setChatNum(rs.getInt(3));
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
