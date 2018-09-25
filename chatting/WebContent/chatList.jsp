<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.chat.vo.UserVo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src = "https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type = "text/javascript">
	function getChatroomList_first()
	{
		$.ajax
		(
			{
				type : "POST",
				url : "./chatroomGetList.do",
				data :
				{
					type : "five"
				},
				success : function(data)
				{
					var parsed = JSON.parse(data);
					var result = parsed.result;
					for(var i = 0 ; i < result.length ; i++)
					{
						addChatroom(result[i][0].value)
					}
				}
			}		
		);
	}
	function addChatroom(chatroomName)
	{
		$("#feed").append
		(
			'<a>' +
			chatroomName +
			'</a>' +
			'<br>'
		)
	}
</script>
</head>
<body>
<%
	UserVo vo = (UserVo) session.getAttribute("c_user");
%>
<h2>환영합니다 <%=vo.getUserName()%>님</h2>
<div id = "feed">

</div>
<a href = "chatroomAdd.jsp">채팅방 만들기</a>
<script type="text/javascript">
	$(document).ready(function()
	{
		getChatroomList_first();
	});
</script>
</body>
</html>