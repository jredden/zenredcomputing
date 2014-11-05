package com.zenred.zenredcomputing.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zenred.zenredcomputing.domain.PostsDAO.NoSubjectChoosen;
import com.zenred.zenredcomputing.domain.PostsDAO.PostIDNotFound;
import com.zenred.zenredcomputing.domain.SubjectsDAO.AlreadyAssociated;
import com.zenred.zenredcomputing.domain.SubjectsDAO.NotAssociated;

public class PostsDAOTest {

	@Test
	public void test() {
		
		SubjectsDAO subjectsDAO = new SubjectsDAO();
		Boolean status = false;
		try {
			subjectsDAO.associateUserToSubject("Business Stories", "johnredden@aol.com");
			status = true;  // made it out of dodge alive
		} catch (AlreadyAssociated ae) {
			ae.printStackTrace();
		}
		assertTrue(status);

		PostsDAO postsDAO = new PostsDAO();
		try {
			postsDAO.addPost("A TEST POST", "testing_a_post", "johnredden@aol.com", "Business Stories");
			assertTrue(true); // made it out of dodge ...
		} catch (NoSubjectChoosen nsce) {
			nsce.printStackTrace();
		}
		
		try {
			postsDAO.removePosts("testing_a_post", "johnredden@aol.com", "Business Stories");
			assertTrue(true); // made it out of dodge ...
		} catch (NoSubjectChoosen nsce) {
			nsce.printStackTrace();
		} catch (PostIDNotFound pidnfe) {
			pidnfe.printStackTrace();
		}
/*		
		status = true;
		try {
			subjectsDAO.disasssociateUserFromSubject("Business Stories", "johnredden@aol.com");
			  // made it out of dodge alive again
		} catch (NotAssociated na) {
			status = false;
			na.printStackTrace();
		}
		System.out.println("Last Status:"+status);
*/		
		
	}
	
	/*
	 * Here_000:<p>From: johnredden@aol.com</p>

<p>Subject: Business Stories</p>

<p>Title: Tran</p>

<p>Start Entry: &nbsp;Kwock punched a hole.</p>
::johnredden@aol.com:: Tran::Business Stories::
	 */

}
