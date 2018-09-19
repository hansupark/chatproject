<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.chat.vo.UserVo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	UserVo vo = (UserVo) session.getAttribute("c_user");
%>
<h2>환영합니다 <%=vo.getUserName()%>님</h2>

<a href = "chatroomAdd.jsp">채팅방 만들기</a>
</body>
</html>