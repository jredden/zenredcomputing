package com.zenred.zenredcomputing.domain;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.zenred.zenredcomputing.domain.SubjectsDAO.AlreadyAssociated;
import com.zenred.zenredcomputing.domain.SubjectsDAO.NotAssociated;

public class SubjectsDAOTest {

	@Test
	public void test() {
		SubjectsDAO subjectsDAO = new SubjectsDAO();
		List<String> subjects = subjectsDAO.readSubjects();
		System.out.println(subjects);
		assertTrue(!subjects.isEmpty());
	}

	@Test
	public void testAssociateToSubject(){
		SubjectsDAO subjectsDAO = new SubjectsDAO();
		Boolean status = false;
		try {
			subjectsDAO.associateUserToSubject("Business Stories", "johnredden@aol.com");
		} catch (AlreadyAssociated ae) {
			ae.printStackTrace();
		}
		
		status = subjectsDAO.isUserAssociatedToSubject("Business Stories", "johnredden@aol.com");
		assertTrue(status);
		status = false;
		try {
			subjectsDAO.disasssociateUserFromSubject("Business Stories", "johnredden@aol.com");
		} catch (NotAssociated na) {
			na.printStackTrace();
		}
		status = true;  // made it out of dodge alive again
		assertTrue(status);

	}
}
