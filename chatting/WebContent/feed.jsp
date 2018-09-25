<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.chat.vo.UserVo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href = "css/feed.css" rel = "stylesheet">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
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
						addChatroom(result[i][0].value,[result[i][1].value,result[i][2].value,result[i][3].value,result[i][4].value,result[i][5].value]);
					}
				}
			}		
		);
	}
	function addChatroom(chatroomName,array)
	{
		var hashList = returnHash(array);
		$("#feed").append
		(
			' <div class="col-xs-12 col-md-offset-2 col-lg-offset-2 col-md-8 col-lg-8">'+
			'<div class="panel panel-default">' +
			'<div class="panel-heading">' +
			'<i>해쉬태그</i> - '+
			hashList +
			' </div>'+
			'<div class="panel-image">' +
			'<a href = "#" onclick = "enterChatroom()"><img src="http://666a658c624a3c03a6b2-25cda059d975d2f318c03e90bcf17c40.r92.cf1.rackcdn.com/unsplash_52d09387ae003_1.JPG" class="panel-image-preview" /></a>' +
			'<h4>' + chatroomName + '</h4>' +
			'<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed in lobortis nisl, vitae iaculis sapien. Phasellus ultrices gravida massa luctus ornare. Suspendisse blandit quam elit, eu imperdiet neque semper et.</p>' +
			'</div>' +
			'</div>' +
			'<hr>' +
			'</div>'
		)
	}
	function returnHash(array)
	{
		var str = "";
		for(var x = 0 ; x < array.length ;x++)
		{
			str = str + '<span>#' + array[x] + '</span>';
		}
		return str;
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	UserVo vo = (UserVo) session.getAttribute("c_user");
%>
<h2>환영합니다 <%=vo.getUserName()%>님</h2>
<a href = "chatroomAdd.jsp">채팅방 만들기</a>
<div class="container" style="margin-top: 30px;" id = "feed">
	<!-- <div class="row form-group">
        <div class="col-xs-12 col-md-offset-2 col-lg-offset-2 col-md-8 col-lg-8">
            <div class="panel panel-default">
                <div class="panel-heading">해쉬태그쪽
                    <i>Mashable</i> - <span>#Social-Media</span><span>#Web</span> 
                </div>
                <div class="panel-image">이미지쪽
                    <img src="http://666a658c624a3c03a6b2-25cda059d975d2f318c03e90bcf17c40.r92.cf1.rackcdn.com/unsplash_52d09387ae003_1.JPG" class="panel-image-preview" />
                    <label for="toggle-1"></label>
                    <h4 id = "feed">Summary</h4>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed in lobortis nisl, vitae iaculis sapien. Phasellus ultrices gravida massa luctus ornare. Suspendisse blandit quam elit, eu imperdiet neque semper et.</p>
                </div>
                <input type="checkbox" id="toggle-1" class="panel-image-toggle">       
        	</div>
        	<hr>
	</div>
</div> -->
<script type="text/javascript">
	$(document).ready(function()
	{
		getChatroomList_first();
	})
</script>
</body>
</html>