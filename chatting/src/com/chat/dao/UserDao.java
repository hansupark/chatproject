package com.chat.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.chat.vo.UserVo;

public class UserDao {

	private static UserDao UserDao = new UserDao();
	private UserDao() {};
	public static UserDao getUserDao()
	{
		return UserDao;
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
	public int insertUser(UserVo user)
	{
		Connection conn = null;
		PreparedStatement psmt = null;
		try
		{
			conn = getConnection();
			psmt = conn.prepareStatement("insert into user (userId,userPw,userName) values(?,?,?)");
			psmt.setString(1, user.getId());
			psmt.setString(2, user.getPw());
			psmt.setString(3, user.getUserName());
			return psmt.executeUpdate();
			 
		}
		catch(Exception e)
		{
			System.out.println("insertUser 오류 발생 " + e);
		}
		finally
		{
			close(psmt,conn);
			return -1;
		}
	}
	public UserVo SearchUser(String id)
	{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		UserVo vo = null;
		System.out.println("id : " + id);
		try
		{
			conn = getConnection();
			psmt = conn.prepareStatement("select * from user where userId = ?");
			psmt.setString(1,id);
			rs = psmt.executeQuery();
			while(rs.next())
			{
				vo = new UserVo();
				vo.setUserNum(rs.getInt("userNum"));
				vo.setUserName(rs.getString("userName"));
				vo.setId(rs.getString("userId"));
			}
			 
		}
		catch(Exception e)
		{
			System.out.println("loginUser 오류 발생 " + e);
		}
		return vo;
	}
	public String loginUser(String id) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String pw = null;
		System.out.println("id : " + id);
		try
		{
			conn = getConnection();
			psmt = conn.prepareStatement("select userPw from user where userId = ?");
			psmt.setString(1,id);
			rs = psmt.executeQuery();
			while(rs.next())
			{
				pw = rs.getString("userPw");
			}
			 
		}
		catch(Exception e)
		{
			System.out.println("loginUser 오류 발생 " + e);
		}
		return pw;
	}
}
