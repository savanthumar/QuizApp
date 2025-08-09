<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="java.util.List, com.quizapp.model.Subject" %>
<%
  // session check: redirect if not logged in
  if (session.getAttribute("user") == null) { response.sendRedirect("login.jsp"); return; }
  List<Subject> subjects = (List<Subject>) request.getAttribute("subjects");
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Subjects — MCQ</title>
  <link rel="stylesheet" href="css/subject.css">
	
</head>
<body>

<div class="container">
  <h1 class="page-title">Pick a Subject</h1>
  <div class="subjects-grid">
    <% if (subjects != null && !subjects.isEmpty()) {
        for (Subject s : subjects) { %>
          <form id="subjectForm_<%= s.getId() %>" action="test" method="post" style="display:none;">
              <input type="hidden" name="subjectId" value="<%= s.getId() %>" />
          </form>
          <div class="subject-card" onclick="document.getElementById('subjectForm_<%= s.getId() %>').submit();">
              <h3><%= s.getSubjectName() %></h3>
              <p>30 Questions · 30 Minutes</p>
          </div>
    <% } } else { %>
        <p class="no-subjects">No subjects available.</p>
    <% } %>
  </div>

  <div class="logout-link">
      <a href="logout">Logout</a>
  </div>

<
</body>
</html>