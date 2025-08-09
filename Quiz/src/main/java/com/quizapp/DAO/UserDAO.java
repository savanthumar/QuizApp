package com.quizapp.DAO;

import java.util.List;

import com.quizapp.model.User;

public interface UserDAO {
	
	boolean registerUser(User user);
    User login(String username, String password);
    boolean isUsernameExists(String username);
    List<User> getAllUsers();

}
