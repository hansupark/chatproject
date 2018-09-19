<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>회원가입</h2>
<form action = "userInsert.do" method = "post">
	아이디 : <input type = "text" name = "id"><br>
	비밀번호 : <input type = "password" name = "pw"><br>
	이름 : <input type = "text" name = "name"><br>
	<input type = "submit" name = "가입">
</form>
</body>
</html>