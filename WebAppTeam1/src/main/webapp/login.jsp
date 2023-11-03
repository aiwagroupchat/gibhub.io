<%@page import="java.util.List"%>
<%@page import="aiwa.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AiwaGroupChatIogIn</title>
<%
//ユーザーの一覧をget
List<User> users = (List<User>) request.getAttribute("users");
String userid = (String) request.getAttribute("userid");

String msg = (String) request.getAttribute("errormessage");
if (msg == null) {
	msg = "";
}
%>
<link href="css/login.css" rel="stylesheet">
</head>
<body>


		
		<div class="container">
			<div class="screen">
				<div class="screen__content">
					<form action="LoginController" method="post" class="login">
					<h4>Welcome to Aiwa Group Chat</h4>
						<div class="login__field">
							<i class="login__icon fas fa-user"></i> <select name="userid" class="login__input">
								<option value="">Name</option>
								<%
								for (User u : users) {
								%>
								<option value="<%=u.getUserid()%>" <%= u.getUserid().equals(userid)? "selected" : "" %>><%=u.getUsername()%></option>

								<%
								}
								%>

							</select>
						</div>
						<div class="login__field">
							<i class="login__icon fas fa-lock"></i> <input type="password" class="login__input" name="password" placeholder="Password">
						</div>
						<p class="errormsg"><%= msg %></p>
						<button class="button login__submit">
							<span class="button__text">Log In Now</span> <i class="button__icon fas fa-chevron-right"></i>
						</button>
					</form>

				</div>
				<div class="screen__background">
					<span class="screen__background__shape screen__background__shape4"></span> <span class="screen__background__shape screen__background__shape3"></span> <span class="screen__background__shape screen__background__shape2"></span> <span class="screen__background__shape screen__background__shape1"></span>
				</div>
			</div>
		</div>
</body>
</html>