package com.chat.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
			psmt = conn.prepareStatement("insert into chatroom (chatroomName,chatroomMadeTime,userNum) values(?,now(),?)");
			psmt.setString(1,cr.getChatroomName());
			psmt.setInt(2, userNum);
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
	public ArrayList<ChatroomVo> getChatroomList(String type) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ArrayList<ChatroomVo> list = null;
		try
		{
			System.out.println("type in dao : " + type);
			list = new ArrayList<ChatroomVo>();
			conn = getConnection();			
			if(type.equals("five"))
			{
				System.out.println("hello");
				psmt = conn.prepareStatement("select * from chatroom");
				rs = psmt.executeQuery();
				while(rs.next())
				{
					System.out.println("while 실행");
					ChatroomVo vo = new ChatroomVo();
					System.out.println("vo 생성");
					vo.setChatroomNum(rs.getInt("chatroomNum"));
					vo.setChatroomName(rs.getString("chatroomName"));
					vo.setChatroomMadeTime(rs.getString("chatroomMadeTime"));
					vo.setUserNum(rs.getInt("userNum"));
					System.out.println("chatroomName : " + vo.getChatroomName());
					list.add(vo);
				}
			}
			
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			
		}
		return list;
	}
}
