package com.chat.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import com.chat.vo.HashVo;

public class HashDao {

	private static HashDao dao = new HashDao();
	private HashDao() {};
	public static HashDao getInstance()
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
	
	public void setHash(ArrayList<HashVo> list,int chatroomNum) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement psmt = null;
		System.out.println("chatroomNum : " + chatroomNum);
		try
		{
			conn = getConnection();
			for(HashVo vo : list)
			{
				System.out.println("setHash 시작");
				psmt = conn.prepareStatement("insert into hash (hashName,chatroomNum) values(?,?)");
				psmt.setString(1,vo.getHashName());
				psmt.setInt(2, chatroomNum);
				psmt.executeUpdate();				
			}
			
		}
		catch(Exception e)
		{
			System.out.println("hashdao sethash 오류" + e);
		}
		finally
		{
			close(psmt, conn);
		}
		
	}

}
