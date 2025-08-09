<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="java.util.List, com.quizapp.model.Question" %>
<%
  if (session.getAttribute("user") == null) { 
      response.sendRedirect("login.jsp"); 
      return; 
  }
  List<Question> questions = (List<Question>) request.getAttribute("questions");
  String subjectName = (String) request.getAttribute("subjectName");
  Integer subjectId = (Integer) request.getAttribute("subjectId");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Test — MCQ</title>
  <link rel="stylesheet" href="css/test.css">
  <script src="js/test.js" defer></script>
  

</head>
<body>
<div class="test-container">
    <div class="test-header">
        <h2 class="subject-name"><%= subjectName != null ? subjectName : "Subject" %></h2>
        <div class="timer">⏳ <span id="time">30:00</span></div>
    </div>

    <form id="testForm" action="submit" method="post">
        <input type="hidden" name="subjectId" value="<%= subjectId %>">

        <% if (questions != null && !questions.isEmpty()) {
             int no = 1;
             for (Question q : questions) { %>
                <div class="question-box">
                    <h4>Q<%= no++ %>. <%= q.getQuestionText() %></h4>
                    <div class="options">
                        <label><input type="radio" name="answer_<%=q.getId()%>" value="A"> <%=q.getOptionA()%></label>
                        <label><input type="radio" name="answer_<%=q.getId()%>" value="B"> <%=q.getOptionB()%></label>
                        <label><input type="radio" name="answer_<%=q.getId()%>" value="C"> <%=q.getOptionC()%></label>
                        <label><input type="radio" name="answer_<%=q.getId()%>" value="D"> <%=q.getOptionD()%></label>
                    </div>
                </div>
        <%   } 
           } else { %>
            <p class="no-questions">No questions available for this subject.</p>
        <% } %>

        <div class="form-actions">
            <button type="submit" class="btn-primary">Submit Test</button>
        </div>
    </form>

    <div class="instructions">
        <h3>📜 Instructions</h3>
        <ul>
            <li>30 questions, 1 mark each.</li>
            <li>Time limit: 30 minutes — will auto-submit on timeout.</li>
            <li>Do not refresh or switch tabs (auto-submit after 3 switches).</li>
        </ul>
    </div>
</div>

</body>
</html>