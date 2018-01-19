package com.rev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.rev.model.*;

public class UserDAOImpl implements UserDAO {
	public User login(String username, String password) throws SQLException {
		boolean authenticated = false;
		Connection connection = DAOUtils.getConnection();
		
		String sql = "select * from users where username = ? and password = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, username);
		statement.setString(2, password);
		ResultSet rs = statement.executeQuery();
		
		User user = null;
	    while (rs.next()) {
	    	user = new User(rs.getInt("id"),
	    			rs.getString("firstname"),
	    			rs.getString("lastname"),
	    			rs.getString("username"),
	    			rs.getString("password"),
	    			rs.getString("role"));
	    } 
	    statement.close();
		return user;
	} //login()
	
	public void updateUser(User oldUser, User newUser) throws SQLException {
		Connection connection = DAOUtils.getConnection();
		String update_user = "update users "
				+ "set firstname = ?, lastname = ?, username = ?, password = ? "
				+ "where id = ?";
		
		PreparedStatement statement = connection.prepareStatement(update_user);
		statement.setString(1, newUser.getFname());
		statement.setString(2, newUser.getLname());
		statement.setString(3, newUser.getUname());
		statement.setString(4, newUser.getPassword());
		statement.setInt(5, oldUser.getId());
		statement.executeUpdate();
		statement.close();
	}
	
	public List<User> getAllUsers() throws SQLException {
		Connection connection = DAOUtils.getConnection();
		List<User> users = new ArrayList<User>();
		String getallusers = "select * from users";
		PreparedStatement statement = connection.prepareStatement(getallusers);
		ResultSet rs = statement.executeQuery();
		
	    while (rs.next()) {
	    	User user = new User(rs.getInt("id"),
	    			rs.getString("firstname"),
	    			rs.getString("lastname"),
	    			rs.getString("username"),
	    			rs.getString("password"),
	    			rs.getString("role"));
	    	users.add(user);
	    } 
	    statement.close();
	    return users;
	}
	
	public User getUser(int id) throws SQLException {
		Connection connection = null;
		try {
			connection = DAOUtils.getConnection();
	
			User user = null;
			String getuser = "select * from users where id = ?";
			PreparedStatement statement = connection.prepareStatement(getuser);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			
		    while (rs.next()) {
		    	user = new User(rs.getInt("id"),
		    			rs.getString("firstname"),
		    			rs.getString("lastname"),
		    			rs.getString("username"),
		    			rs.getString("password"),
		    			rs.getString("role"));
		    } 
		    statement.close();
		    return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
