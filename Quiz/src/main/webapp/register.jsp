<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Register - MCQ Test App</title>
<link rel="stylesheet" href="css/styless.css"></link>
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">

</head>
<body>
	
	<div class="card">
    <div class="header"><h1>MCQ Test Platform</h1></div>
    <div class="inner">
      <div class="left">
        <div class="auth-card">
          <h2>Create account</h2>
          <form action="register" method="post">
            <div class="form-field"><input type="text" name="username" placeholder="Username" required></div>
            <div class="form-field"><input type="email" name="email" placeholder="Email" required></div>
            <div class="form-field"><input type="password" name="password" placeholder="Password" required></div>
            <input type="submit" class="btn-primary" value="Register">
          </form>
          <p class="small-link">Already registered? <a href="login.jsp">Login</a></p>
          <% if (request.getAttribute("error") != null) { %>
              <p style="color:#ffcccc; text-align:center; margin-top:8px;"><%=request.getAttribute("error")%></p>
          <% } %>
        </div>
      </div>
      <div class="right">
        <h3 style="color:#fff;">Join & Practice</h3>
        <p style="color:#f0f0f0;">Register to take timed 30-question tests across multiple subjects. Each test is 30 minutes.</p>
      </div>
    </div>
  </div>
	
</body>
</html>