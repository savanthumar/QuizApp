package com.quizapp.controller;

import java.io.IOException;
import java.util.List;

import com.quizapp.DAO.SubjectDAO;
import com.quizapp.DAOImpl.SubjectDAOImpl;
import com.quizapp.model.Subject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/subjects")
public class SubjectServlet extends HttpServlet{
	

	private static final long serialVersionUID = 1L;
    private SubjectDAO subjectDao;

    @Override
    public void init() { subjectDao = new SubjectDAOImpl(); }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // session check
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        List<Subject> subjects = subjectDao.getAllSubjects();
        req.setAttribute("subjects", subjects);
        req.getRequestDispatcher("subjects.jsp").forward(req, resp);
    }
}
