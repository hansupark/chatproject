<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.chat.vo.UserVo" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href = "css/feed.css" rel = "stylesheet">
<link href = "css/box.css" rel = "stylesheet">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script src = "https://code.jquery.com/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
<%
UserVo vo = (UserVo) session.getAttribute("c_user"); //현재 로그인 유저 상태 세션에 저장
%>
</script>
<script type = "text/javascript">
	var lastNum;
	var firstNum;
	var chat; //timeinterval 닫기위한 변수
	function getChatroomList_first() //맨처음 게시판 리스트 가져오기
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
						addChatroom(result[i][0].value,result[i][1].value,[result[i][2].value,result[i][3].value,result[i][4].value,result[i][5].value,result[i][6].value]);
					}
				}
			}		
		);
	}
	function enterChatroom(chatroomNum) /* 게시판 클릭 컨트롤러  처음 게시판 클릭시 채팅들을 불러옴*/
	{
		$.ajax
		(
			{
				type : "POST",
				url : "./chatGetList.do",
				data :
				{
					chatroomNum : chatroomNum
				},
				success : function(data)
				{
					var parsed = JSON.parse(data);
					var result = parsed.result;
					$("#chatList").append
					(
						'<input type = "hidden" id = "chatroomNum" value = "' + //채팅 입력할떄 필요
						chatroomNum + 
						'">'
					)
					for(var x = 0 ; x < result.length ; x++)
					{
						if(x == 0)
							firstNum = Number(result[x][2].value);
						addChatList(result[x][0].value,result[x][1].value);
						lastNum = Number(result[x][2].value);
					}
					//alert(chatroomNum);
					chat = setInterval(function(){updateChatList(chatroomNum,lastNum);},2000);
					$('#chatList').scroll(function(){
				        var scrollT = $(this).scrollTop(); //스크롤바의 상단위치
				        //var scrollH = $(this).height(); //스크롤바를 갖는 div의 높이
				        //var contentH = $('#chatList').height(); //문서 전체 내용을 갖는 div의 높이
				        if(scrollT == 0) { // 스크롤바가 맨 아래에 위치할 때
				            getChatListPast(chatroomNum,firstNum);
				        }
				    });
				}
			}		
		);
	}
	function addChatroom(chatroomName,chatroomNum,array) /* 게시판 리스트 가져오기 */
	{
		var hashList = returnHash(array);
		$("#feed").append
		(
			'<div class="col-xs-12 col-md-offset-2 col-lg-offset-2 col-md-8 col-lg-8">'+
			'<div class="panel panel-default">' +
			'<div class="panel-heading">' +
			'<i>해쉬태그</i> - '+
			hashList +
			' </div>'+
			'<div class="panel-image">' +
			'<form action = "#">' +
			'<button onclick = "enterChatroom(' +
			chatroomNum +
			')" data-toggle="modal" data-target="#myModal"><img src="http://666a658c624a3c03a6b2-25cda059d975d2f318c03e90bcf17c40.r92.cf1.rackcdn.com/unsplash_52d09387ae003_1.JPG" class="panel-image-preview"></button>' +
			'</form>' +
			'<h4>' + chatroomName + '</h4>' +
			'<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed in lobortis nisl, vitae iaculis sapien. Phasellus ultrices gravida massa luctus ornare. Suspendisse blandit quam elit, eu imperdiet neque semper et.</p>' +
			'</div>' +
			'</div>' +
			'<hr>' +
			'</div>'
		)
	}
	
	//채팅 자바스크립트 함수들 
	function addChat()
	{
		var chatContent = document.getElementById("chatContent").value;
		var chatroomNum = document.getElementById("chatroomNum").value;
		var userNum = "<%=vo.getUserNum()%>";
		$.ajax
		(
		{
			type : "POST",
			url : "./chataddChat.do",
			data :
			{
				chatroomNum : chatroomNum,
				chatContent : chatContent,
				userNum : userNum
			}
		}		
		)
	}
	
	function updateChatList(chatroomNum,LastNum)
	{
		$.ajax
		(
		{
			type : "POST",
			url : "./chatGetList.do",
			data :
			{
				chatroomNum : chatroomNum,
				lastNum : LastNum
			},
			success : function(data)
			{
				var parsed = JSON.parse(data);
				var result = parsed.result;
				for(var x = 0 ; x < result.length ; x++)
				{
					addChatList(result[x][0].value,result[x][1].value);
					lastNum = Number(result[x][2].value);
				}
			}
		}		
		)
	}
	
	function getChatListPast(chatroomNum,FirstNum)
	{
		$.ajax
		(
		{
			type : "POST",
			url : "./chatGetList.do",
			data :
			{
				chatroomNum : chatroomNum,
				firstNum : FirstNum				
			},
			success : function(data)
			{
				var parsed = JSON.parse(data);
				var result = parsed.result;
				for(var x = 0 ; x < result.length ; x++)
				{
					addChatListPast(result[x][0].value,result[x][1].value);
					if(x == 0)
						firstNum = Number(result[x][2].value);
				}
			}
		}		
		)
	}
	function addChatList(chatContent,userName) //채팅들 가져오기
	{
		$("#chatList").append
		(
			'<p>' +
			userName +
			'</p><br>' +
			'<p>' +
			chatContent +
			'</p>' +
			'<hr>'
		)
	}
	function addChatListPast(chatContent,userName) //채팅들 가져오기
	{
		$("#chatList").prepend
		(
			'<p>' +
			userName +
			'</p><br>' +
			'<p>' +
			chatContent +
			'</p>' +
			'<hr>'
		)
	}
	function returnHash(array) //각 게시판에 대한 해쉬태그들 추가
	{
		var str = "";
		for(var x = 0 ; x < array.length ;x++)
		{
			str = str + '<span>#' + array[x] + '</span>';
		}
		return str;
	}
	function closeChatroom()
	{
		$('#chatList').empty();
		clearInterval(chat);
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>환영합니다 <%=vo.getUserName()%>님</h2>
<a href = "chatroomAdd.jsp">채팅방 만들기</a>
<div class="container" style="margin-top: 30px;" id = "feed">
	<!-- 채팅창 부분 --> 
	<div class="modal fade" id="myModal" role="dialog">
	    <div class="modal-dialog">	    
	      <!-- Modal content-->
	      <div class="modal-content">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title">Modal Header</h4>
	        </div>
	        <div class="modal-body" id = "chatList">
	          <p>hello world</p>
	        </div>
	        <div class="modal-footer">
	          <input type = "text" id = "chatContent"><button type = "button" onclick = "addChat();">전송</button>
	          <button type="button" class="btn btn-default" data-dismiss="modal" onclick = "closeChatroom();">Close</button>
	        </div>
	      </div>
	      
	    </div>
	  </div>
	  
</div>
<script type="text/javascript">
	$(document).ready(function()
	{
		getChatroomList_first();
	})
</script>
</body>
</html>