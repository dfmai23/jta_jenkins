package com.rev.dao;

import java.sql.SQLException;
import java.util.List;

import com.rev.model.*;

public interface UserDAO {
	public User login(String username, String password) throws SQLException;
	
	public void updateUser(User oldUser, User newUser) throws SQLException;
	
	public List<User> getAllUsers() throws SQLException;
	
	public User getUser(int id) throws SQLException;
}
