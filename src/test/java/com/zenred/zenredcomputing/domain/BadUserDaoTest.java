package com.zenred.zenredcomputing.domain;

import static org.junit.Assert.assertNull;
import junit.framework.Assert;

import org.junit.Test;

public class BadUserDaoTest {

	@Test
	public void test() {
		UserDao userDao = new UserDao();
		User user = userDao.readUser("baduser@aol.com", "badpass");
		assertNull(user);
		System.out.println("test failed as it should");
	}

}
