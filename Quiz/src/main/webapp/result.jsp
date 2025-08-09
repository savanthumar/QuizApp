<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
  Integer score = (Integer) request.getAttribute("score");
  Integer total = (Integer) request.getAttribute("total");
  if (score == null) score = 0;
  if (total == null) total = 30;

  int percentage = (score * 100) / total;
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Result — MCQ</title>
<link rel="stylesheet" href="css/result.css">
</head>
<body>

<div class="card">
  <div class="header">
    <h1>Test Result</h1>
  </div>
  <div class="inner">
    <div class="left">
      <div class="result-box">
        <div class="score">
          <span class="score-number"><%= score %></span>
          <span class="divider">/</span>
          <span class="total-number"><%= total %></span>
        </div>
        <p class="percentage">Percentage: <%= percentage %>%</p>

        <div class="buttons">
          <a class="btn btn-primary" href="subjects">Take Another Test</a>
          <a class="btn btn-secondary" href="logout">Logout</a>
        </div>
      </div>
    </div>
    <div class="right">
      <h3>Keep Practicing</h3>
      <p>Review topics you missed and try again to improve your score.</p>
    </div>
  </div>
</div>

</body>
</html>
