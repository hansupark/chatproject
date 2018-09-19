<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src = "https://code.jquery.com/jquery-3.1.1.min.js"></script>
<title>Insert title here</title>
<script type = "text/javascript">
	function loginFunction()
	{
		var userId = $("#id").val();
		var userPw = $("#pw").val();
		$.ajax
		(
		{
			type : "POST",
			url : "userLogin.do",
			data :
				{
				id : userId,
				pw : userPw
				},
			success : function(result)
			{
				if(result == 1)
					{
						$("#errorM").append('<p>비밀번호틀림</p>')
					}
				else if(result == 0)
					{
						$("#errorM").append('<p>아이디 존재x</p>')
					}
				 else if(result == 2)
					{
						window.location.replace("chatList.jsp");
					} 
			}
			
		}		
		);
	}
</script>
</head>
<body>
<div id = "errorM">

</div>
	아이디 : <input type = "text" name = "id" id = "id"><br>
	비밀번호 : <input type = "password" name = "pw" id = "pw"><br>
	<button type = "button" onclick = "loginFunction();">로그인</button>
<a href = "userAdd.jsp">회원가입</a>
</body>
</html>