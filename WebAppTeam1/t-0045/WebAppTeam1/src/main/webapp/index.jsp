<%@page import="aiwa.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AiwaGroupChat</title>
<link href="css/mystyle.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<%
	User user = (User) session.getAttribute("user");
%>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<script>
	let userid = "<%= user.getUserid() %>";
</script>
<script src="js/my.js"></script>
</head>
<style>
	.btn{background-color: #a683d4;}
</style>
<body>
<div class="container mt-5 col-8">

	<div class="row  kaiwa line">
	
	
		<div class="col-md-12 " >
			<h3><img src="images/logo.png" style="height:60px">Aiwa Group Chat</h3>
		</div>
		<div class="text-end">
			<%= user.getUsername()%><img class="loginicon" src="images/<%= user.getUserid()%>.jpg" style="height:60px; width:60px;border-radius: 30px;">
		</div>
		
	</div>
	
	
	<div class="row kaiwa line overflow-auto" style="padding-top:0">
		<div id="msg" class="border-top border-dark"></div>
	</div>
	<div class="row mb-3 kaiwa line">
		<div class="mb-3 col-md-10">
			<input class="form-control" type="text" id="txt">
		</div>
		<div class="mb-3 col-md-2">
			<button class="btn" onclick="send()"><i class="bi bi-send-fill" style="height:10px; width:10px;"></i></button>
		</div>
	</div>
	
	
</div>
</body>
</html>