package com.zenred.zenredcomputing.domain;

import java.util.HashMap;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

public class PostsDAO extends AbstractJDBCDao {
	
	private static String tableName = "Posts";
	private static String joinTableName = "UserToPosts";
	private static String joinTableName2 = "SubjectsToPosts";
	
	private String userIndexSql = "SELECT User_id FROM User WHERE emailAddress = ?";
	private String subjectIndexSql = "SELECT Subject_id FROM Subject WHERE Subjects_name = ?";
	private String subjectToUsersIndexSql = "SELECT count(*) FROM UserToSubjects WHERE User_id = ? AND Subjects_id = ?";
	private String lastInsertSql = "SELECT LAST_INSERT_ID()";
	
	
	public class NoSubjectChoosen extends Exception{

		/**
		 * 
		 */
		private static final long serialVersionUID = -3944965751346748111L;
		
	}
	
	/**
	 * 
	 * @param post
	 * @param usersEMail
	 * @param subject
	 * @throws NoSubjectChoosen
	 */
	@Transactional
	public void addPost(String post, String usersEMail, String subject) throws NoSubjectChoosen{
		
		Integer userId = super.jdbcSetUp().getSimpleJdbcTemplate()
				.queryForInt(userIndexSql, usersEMail);
		
		Integer subjectId = super.jdbcSetUp().getSimpleJdbcTemplate()
				.queryForInt(subjectIndexSql, subject);
		int count = super.jdbcSetUp().getSimpleJdbcTemplate().queryForInt(subjectToUsersIndexSql, userId, subjectId);
		if(count == 0){
			throw new NoSubjectChoosen();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Content", post);
		super.jdbcInsertSetup().withTableName(tableName)
		.usingColumns("Content").execute(map);
		Integer posts_id = super.jdbcSetUp().getSimpleJdbcTemplate().queryForInt(lastInsertSql);
	}


}
