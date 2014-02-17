package com.zenred.zenredcomputing.domain;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class SubjectsDAOTest {

	@Test
	public void test() {
		SubjectsDAO subjectsDAO = new SubjectsDAO();
		List<String> subjects = subjectsDAO.readSubjects();
		System.out.println(subjects);
		assertTrue(!subjects.isEmpty());
	}

}
