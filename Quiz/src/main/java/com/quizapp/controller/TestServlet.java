package com.quizapp.controller;

import java.io.IOException;
import java.util.List;

import com.quizapp.DAO.QuestionDAO;
import com.quizapp.DAOImpl.QuestionDAOImpl;
import com.quizapp.model.Question;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
	
	
	
	private static final long serialVersionUID = 1L;
    private QuestionDAO questionDao;

    @Override
    public void init() {
        questionDao = new QuestionDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        String sid = req.getParameter("subjectId");
        if (sid == null) {
            resp.sendRedirect("subjects");
            return;
        }

        int subjectId;
        try {
            subjectId = Integer.parseInt(sid);
        } catch (NumberFormatException ex) {
            resp.sendRedirect("subjects");
            return;
        }

        // Get questions
        List<Question> questions = questionDao.getRandomQuestions(subjectId, 30);
        session.setAttribute("testQuestions", questions);
        session.setAttribute("subjectId", subjectId);

        // You will need a way to get subject name from subjectId, either from DB or hardcoded for now:
        String subjectName = getSubjectNameById(subjectId);
        session.setAttribute("subjectName", subjectName);

        req.setAttribute("questions", questions);
        req.setAttribute("subjectId", subjectId);
        req.setAttribute("subjectName", subjectName);

        req.getRequestDispatcher("test.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // For GET request, retrieve from session
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        @SuppressWarnings("unchecked")
        List<Question> questions = (List<Question>) session.getAttribute("testQuestions");
        Integer subjectId = (Integer) session.getAttribute("subjectId");
        String subjectName = (String) session.getAttribute("subjectName");

        if (questions == null || subjectId == null) {
            resp.sendRedirect("subjects");
            return;
        }

        req.setAttribute("questions", questions);
        req.setAttribute("subjectId", subjectId);
        req.setAttribute("subjectName", subjectName);
        req.getRequestDispatcher("test.jsp").forward(req, resp);
    }

    private String getSubjectNameById(int subjectId) {
        // You can replace this method with DB lookup if you want
        switch (subjectId) {
            case 1: return "Java Programming";
            case 2: return "Python Programming";
            case 3: return "Data Structures";
            case 4: return "Algorithms";
            case 5: return "Database Management System";
            // add others here
            default: return "Subject";
        }
    }
}
