package com.zenred.zenredcomputing.domain;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.zenred.util.GenName;
import com.zenred.zenredcomputing.domain.PostsDAO.NoSubjectChoosen;
import com.zenred.zenredcomputing.domain.PostsDAO.PostIDNotFound;
import com.zenred.zenredcomputing.domain.SubjectsDAO.AlreadyAssociated;

public class PostsForUserDaoTest {
	
	User user2;
	User user;
	UserDao userDao;
	
	private void addUser(){
		String randomEmailAddress = "fundit@fun_" + Math.random() +".org";
		String randomPassword = "stupid_"+(Math.random());
		userDao = new UserDao();
		user = new User();
		user.setEmailAddress(randomEmailAddress);
		user.setFirstName("Elmer");
		user.setLastName("Fudd");
		user.setPassword(randomPassword);
		user.setUser_Status(UserStatus.registered);
		userDao.createUser(user);
		user = userDao.readUser(randomEmailAddress, randomPassword);
		System.out.println(user);
		
		randomEmailAddress = "fundit@fun_" + Math.random() +".org";
		randomPassword = "stupid_"+(Math.random());

		user2 = new User();
		user2.setEmailAddress(randomEmailAddress);
		user2.setFirstName("Dudly");
		user2.setLastName("DewRight");
		user2.setPassword(randomPassword);
		user2.setUser_Status(UserStatus.registered);
		userDao.createUser(user2);
		user2 = userDao.readUser(randomEmailAddress, randomPassword);
		System.out.println(user2);

	}
	
	private void deleteUser(){
		userDao.deleteUser(user.getPassword());
		userDao.deleteUser(user2.getPassword());
	}

	@Test
	public void test() {
		addUser();
		PostsDAO postsDAO = new PostsDAO();
		SubjectsDAO subjectsDAO = new SubjectsDAO();
		try {
			subjectsDAO.associateUserToSubject("Computer History", user.getEmailAddress());
		} catch (AlreadyAssociated aae) {
			aae.printStackTrace();
		}
		try {
			subjectsDAO.associateUserToSubject("Inclusive Software Engineering", user.getEmailAddress());
		} catch (AlreadyAssociated aae) {
			aae.printStackTrace();
		}
		try {
			subjectsDAO.associateUserToSubject("Computer History", user2.getEmailAddress());
		} catch (AlreadyAssociated aae) {
			aae.printStackTrace();
		}
		try {
			subjectsDAO.associateUserToSubject("Business Stories", user2.getEmailAddress());
		} catch (AlreadyAssociated aae) {
			aae.printStackTrace();
		}
		try {
			postsDAO.addPost("a test post", "Test Post 1", user.getEmailAddress(), "Computer History");
		} catch (NoSubjectChoosen nsce) {
			nsce.printStackTrace();
		}
		try {
			postsDAO.addPost("a second test post", "Test Post 2", user.getEmailAddress(), "Inclusive Software Engineering");
		} catch (NoSubjectChoosen nsce) {
			nsce.printStackTrace();
		}
		try {
			postsDAO.addPost("a third test post", "Test Post 3", user2.getEmailAddress(), "Computer History");
		} catch (NoSubjectChoosen nsce) {
			nsce.printStackTrace();
		}
		try {
			postsDAO.addPost("a fourth test post", "Test Post 4", user2.getEmailAddress(), "Business Stories");
		} catch (NoSubjectChoosen nsce) {
			nsce.printStackTrace();
		}
		System.out.println("Read all posts for:"+user.getFirstName());
		List<Posts> posts = postsDAO.readUserPosts(user.getEmailAddress());
		assertNotNull(posts);
		for(Posts post : posts){ System.out.println(post);}
		System.out.println("Read all posts not for:"+user.getFirstName());
		posts = postsDAO.readNonUserPosts(user.getEmailAddress());
		assertNotNull(posts);
		for(Posts post : posts){ System.out.println(post);}
		System.out.println("Read user posts for Subject:"+user.getFirstName());
		posts = postsDAO.readUserPostsWithinSubject(user.getEmailAddress(), "Computer History");
		assertNotNull(posts);
		for(Posts post : posts){ System.out.println(post);}
		System.out.println("Read non user posts for Subject:"+user.getFirstName());
		posts = postsDAO.readNonUserPostsWithinSubject(user.getEmailAddress(), "Computer History");
		assertNotNull(posts);
		for(Posts post : posts){ System.out.println(post);}
		try {
			postsDAO.removePosts("Test Post 1", user.getEmailAddress(), "Computer History");
		} catch (NoSubjectChoosen nsce) {
			nsce.printStackTrace();
		} catch (PostIDNotFound pnfe) {
			pnfe.printStackTrace();
		}
		try {
			postsDAO.removePosts("Test Post 2", user.getEmailAddress(), "Inclusive Software Engineering");
		} catch (NoSubjectChoosen nsce) {
			nsce.printStackTrace();
		} catch (PostIDNotFound pnfe) {
			pnfe.printStackTrace();
		}
		try {
			postsDAO.removePosts("Test Post 3", user2.getEmailAddress(), "Computer History");
		} catch (NoSubjectChoosen nsce) {
			nsce.printStackTrace();
		} catch (PostIDNotFound pnfe) {
			pnfe.printStackTrace();
		}
		try {
			postsDAO.removePosts("Test Post 4", user2.getEmailAddress(), "Business Stories");
		} catch (NoSubjectChoosen nsce) {
			nsce.printStackTrace();
		} catch (PostIDNotFound pnfe) {
			pnfe.printStackTrace();
		}
		deleteUser();
	}

}
