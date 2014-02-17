package com.zenred.zenredcomputing.domain;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class NumberQuestonDaoTest {

	@Test
	public void test() {
		QuestionDao questionDao = new QuestionDao();
		Integer answer = questionDao.numberOfQuestions();
		assertNotNull(answer);
		System.out.println(answer);
	}

}
