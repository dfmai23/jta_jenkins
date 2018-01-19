package com.rev.junit;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.rev.dao.*;
import com.rev.model.*;

public class JunitTests {
	@Before
	public void setUp() throws Exception {
		DAOUtils.getConnection();
	}
	
	@Test @Ignore
	public void testLogin() {
		User testuser = new User(1, "Edward", "Mass", "emass23", "password1", "Manager");
		
		try {
			assertEquals("JUnit: Users are not the same\n", DAOUtils.getUserDAO().getUser(1), testuser);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test @Ignore
	public void testUpdateUser() {
		fail("Not yet implemented");
	}

	@Test @Ignore
	public void testGetAllUsers() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUser() {
		User testuser = new User(1, "Edward", "Mass", "emass23", "password1", "Manager");
		
		try {
			assertEquals("JUnit: Users are not the same\n", DAOUtils.getUserDAO().getUser(1), testuser);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
