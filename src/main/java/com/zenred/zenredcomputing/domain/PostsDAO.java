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

	public class NoSubjectChoosen extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = -3944965751346748111L;
	}

	public class PostIDNotFound extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1809127850953494473L;
	}

	/**
	 * 
	 * @param post
	 * @param usersEMail
	 * @param subject
	 * @throws NoSubjectChoosen
	 */
	@Transactional
	public void addPost(String post, String title, String usersEMail,
			String subject) throws NoSubjectChoosen {

		Integer userId = super.jdbcSetUp().getSimpleJdbcTemplate()
				.queryForInt(userIndexSql, usersEMail);

		Integer subjectId = super.jdbcSetUp().getSimpleJdbcTemplate()
				.queryForInt(subjectIndexSql, subject);
		int count = super.jdbcSetUp().getSimpleJdbcTemplate()
				.queryForInt(subjectToUsersIndexSql, userId, subjectId);
		if (count == 0) {
			throw new NoSubjectChoosen();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Content", post);
		map.put("Title", title);
		super.jdbcInsertSetup().withTableName(tableName)
				.usingColumns("Content").execute(map);
		Integer posts_id = super.jdbcSetUp().getSimpleJdbcTemplate()
				.queryForInt(lastInsertSql);

		map = new HashMap<String, Object>();
		map.put("User_id", userId);
		map.put("Posts_id", posts_id);
		super.jdbcInsertSetup().withTableName(joinTableName)
				.usingColumns("User_id", "Posts_id");

		map = new HashMap<String, Object>();
		map.put("Subjects_id", userId);
		map.put("Posts_id", posts_id);
		super.jdbcInsertSetup().withTableName(joinTableName2)
				.usingColumns("subjectId", "Posts_id");

	}

	/**
	 * 
	 * @param post
	 * @param usersEMail
	 * @param subject
	 * @throws NoSubjectChoosen
	 * @throws PostIDNotFound
	 */
	@Transactional
	public void removePosts(String title, String usersEMail, String subject)
			throws NoSubjectChoosen, PostIDNotFound {
		Integer userId = super.jdbcSetUp().getSimpleJdbcTemplate()
				.queryForInt(userIndexSql, usersEMail);

		Integer subjectId = super.jdbcSetUp().getSimpleJdbcTemplate()
				.queryForInt(subjectIndexSql, subject);

		int count = super.jdbcSetUp().getSimpleJdbcTemplate()
				.queryForInt(subjectToUsersIndexSql, userId, subjectId);
		if (count == 0) {
			throw new NoSubjectChoosen();
		}
		String joinSql = "SELECT DISTINCT po.Posts_id " + "FROM Posts po "
				+ " JOIN UserToPosts utp ON po.Posts_id = utp.Posts_id "
				+ " JOIN SubjectsToPosts stp ON po.Posts_id = stp.Posts_id "
				+ " WHERE po.Title = ?";
		Integer postsId = super.jdbcSetUp().getSimpleJdbcTemplate()
				.queryForInt(joinSql, title);
		if (postsId == 0) {
			throw new PostIDNotFound();
		}

		String sql = "DELETE FROM " + joinTableName
				+ " WHERE Posts_id = ? AND User_id = ?";
		super.jdbcSetUp().getSimpleJdbcTemplate().update(sql, postsId, userId);

		String sql2 = "DELETE FROM " + joinTableName2
				+ " WHERE Posts_id = ? AND Subjects_id = ?";
		super.jdbcSetUp().getSimpleJdbcTemplate()
				.update(sql, postsId, subjectId);

	}

}
