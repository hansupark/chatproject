<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" enctype="multipart/form-data" action="uploadimage.do"> <!-- miltipart/form-data servlet에 피일을 준다는 신호줌 -->
<input type="file" name="file" size=40>
<input type="submit" value="업로드"><br><br>
</form>
</body>
</html>