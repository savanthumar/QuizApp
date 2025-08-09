package com.quizapp.controller;

import java.io.IOException;

import com.quizapp.DAO.UserDAO;
import com.quizapp.DAOImpl.UserDAOImpl;
import com.quizapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;
    private UserDAO userDao;

    @Override
    public void init() { userDao = new UserDAOImpl(); }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // show registration page
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username").trim();
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();

        // basic validation
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // create user model
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password); // NOTE: hash in production
        user.setRole("student");

        boolean saved = userDao.registerUser(user);
        if (saved) {
            response.sendRedirect("login.jsp?registered=1");
        } else {
            request.setAttribute("error", "Registration failed (username/email may exist).");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
	
}
