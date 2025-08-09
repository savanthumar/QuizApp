package com.quizapp.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.quizapp.model.Question;
import com.quizapp.model.User;
import com.quizapp.util.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/submit")
public class SubmitServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // session check
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        List<Question> questions = (List<Question>) session.getAttribute("testQuestions");
        Integer subjectId = (Integer) session.getAttribute("subjectId");

        if (questions == null) {
            resp.sendRedirect("subjects");
            return;
        }

        int score = 0;
        for (Question q : questions) {
            String param = req.getParameter("answer_" + q.getId());
            if (param != null) {
                String selected = param.trim();
                String correct = String.valueOf(q.getCorrectOption()).trim();
                if (selected.equalsIgnoreCase(correct)) score++;
            }
        }

        // save to results table
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO results(user_id, subject_id, score) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, user.getId());
                ps.setInt(2, subjectId != null ? subjectId : 0);
                ps.setInt(3, score);
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // we still forward to result page even if saving fails
        }

        // cleanup session data
        session.removeAttribute("testQuestions");
        session.removeAttribute("subjectId");

        req.setAttribute("score", score);
        req.setAttribute("total", questions.size());
        req.getRequestDispatcher("result.jsp").forward(req, resp);
    }

	
	

}
