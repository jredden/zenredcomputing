package com.zenred.zenredcomputing.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

public class PostsDAO extends AbstractJDBCDao {

	private static String tableName = "Posts";
	private static String joinTableName = "UserToPosts";
	private static String joinTableName2 = "SubjectsToPosts";
	private static String joinTableName3 = "UserToSubjects";
	private static String userTableName = "User";
	private static String subjectsTableName = "Subjects";

	private String userIndexSql = "SELECT User_id FROM User WHERE emailAddress = ?";
	private String subjectIndexSql = "SELECT Subjects_id FROM Subjects WHERE Subjects_name = ?";
	private String subjectToUsersIndexSql = "SELECT count(*) FROM UserToSubjects WHERE User_id = ? AND Subjects_id = ?";
	private String lastInsertSql = "SELECT MAX(Posts_id) FROM Posts";
	private String userPostsSql = "SELECT po.Title, po.Content, po.Datestamp, po.Posts_id FROM "
			+ tableName
			+ " po INNER JOIN "
			+ joinTableName
			+ " utp ON po.Posts_id = utp.Posts_id "
			+" WHERE utp.User_id = ?";
	
	private String userPostsWithinSubjectSql = "SELECT po.Title, po.Content, po.Datestamp, po.Posts_id FROM "
			+ tableName
			+ " po INNER JOIN "
			+ joinTableName
			+ " utp ON po.Posts_id = utp.Posts_id JOIN "
			+ joinTableName3 
			+" uts ON uts.User_id = utp.User_id JOIN "
			+ joinTableName2
			+ " stp ON po.Posts_id = stp.Posts_id "
			+" WHERE uts.Subjects_id = stp.Subjects_id AND utp.User_id = ? "
			+" AND stp.Subjects_id = ? ORDER BY po.Datestamp"
			;

	private String nonUserPostsSql = "SELECT po.Title, po.Content, po.Datestamp, po.Posts_id FROM "
			+ tableName
			+ " po INNER JOIN "
			+ joinTableName
			+ " utp ON po.Posts_id = utp.Posts_id "
			+" WHERE utp.User_id != ?";

	private String nonUserPostsWithinSubjectSql = "SELECT po.Title, po.Content, po.Datestamp, po.Posts_id FROM "
			+ tableName
			+ " po INNER JOIN "
			+ joinTableName
			+ " utp ON po.Posts_id = utp.Posts_id JOIN "
			+ joinTableName3 
			+" uts ON uts.User_id = utp.User_id JOIN "
			+ joinTableName2
			+ " stp ON po.Posts_id = stp.Posts_id "
			+" WHERE uts.Subjects_id = stp.Subjects_id AND utp.User_id != ? "
			+" AND stp.Subjects_id = ? ORDER BY po.Datestamp"
			;

	
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
				.usingColumns("Content", "Title").execute(map);
		Integer posts_id = super.jdbcSetUp().getSimpleJdbcTemplate()
				.queryForInt(lastInsertSql);

		map = new HashMap<String, Object>();
		map.put("User_id", userId);
		map.put("Posts_id", posts_id);
		super.jdbcInsertSetup().withTableName(joinTableName)
				.usingColumns("User_id", "Posts_id").execute(map);

		map = new HashMap<String, Object>();
		map.put("Subjects_id", subjectId);
		map.put("Posts_id", posts_id);
		super.jdbcInsertSetup().withTableName(joinTableName2)
				.usingColumns("Subjects_Id", "Posts_id").execute(map);

	}

	/**
	 * used mostly for testing purposes.
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
				.update(sql2, postsId, subjectId);

		String sql2_5 = "DELETE FROM " + joinTableName3
				+ " WHERE User_id = ? AND Subjects_id = ?";
		super.jdbcSetUp().getSimpleJdbcTemplate()
				.update(sql2_5, userId, subjectId);

		String sql3 = "DELETE FROM " + tableName + " WHERE Posts_id = ?";
		super.jdbcSetUp().getSimpleJdbcTemplate().update(sql3, postsId);
	}

	/**
	 * read those posts posted by the user 
	 * 
	 * @param userEmail
	 * @return
	 */
	public List<Posts> readUserPosts(String userEmail) {
		Integer usersId = new UserDao().readUserId(userEmail);
		List<Posts> postsList = new ArrayList<Posts>();
		List<Map<String, Object>> postsListMap = super.jdbcSetUp()
				.getSimpleJdbcTemplate()
				.queryForList(userPostsSql, usersId);
		for (Map<String, Object> postsMap : postsListMap) {
			Posts posts = new Posts();
			posts.setContent((String) postsMap.get("Content").toString());
			posts.setTitle((String) postsMap.get("Title").toString());
			posts.setDatestamp((String) postsMap.get("Datestamp").toString());
			String s_setPosts_id = (String) postsMap.get("Posts_id").toString();
			posts.setPosts_id(new Integer(s_setPosts_id).intValue());
			postsList.add(posts);
		}
		return postsList;
	}
	
	/**
	 * read those posts not posted by the user 
	 * 
	 * @param userEmail
	 * @return
	 */
	public List<Posts> readNonUserPosts(String userEmail) {
		Integer usersId = new UserDao().readUserId(userEmail);
		List<Posts> postsList = new ArrayList<Posts>();
		List<Map<String, Object>> postsListMap = super.jdbcSetUp()
				.getSimpleJdbcTemplate()
				.queryForList(nonUserPostsSql, usersId);
		for (Map<String, Object> postsMap : postsListMap) {
			Posts posts = new Posts();
			posts.setContent((String) postsMap.get("Content").toString());
			posts.setTitle((String) postsMap.get("Title").toString());
			posts.setDatestamp((String) postsMap.get("Datestamp").toString());
			String s_setPosts_id = (String) postsMap.get("Posts_id").toString();
			posts.setPosts_id(new Integer(s_setPosts_id).intValue());
			postsList.add(posts);
		}
		return postsList;
	}

	/**
	 * read those posts posted by the user within a subject
	 * 
	 * @param userEmail
	 * @param subject
	 * @return
	 */
	public List<Posts> readUserPostsWithinSubject(String userEmail, String subject) {
		Integer usersId = new UserDao().readUserId(userEmail);
		Integer subjectId = new SubjectsDAO().fetchSubjectId(subject);
		List<Posts> postsList = new ArrayList<Posts>();
		List<Map<String, Object>> postsListMap = super.jdbcSetUp()
				.getSimpleJdbcTemplate()
				.queryForList(userPostsWithinSubjectSql, usersId, subjectId);
		for (Map<String, Object> postsMap : postsListMap) {
			Posts posts = new Posts();
			posts.setContent((String) postsMap.get("Content").toString());
			posts.setTitle((String) postsMap.get("Title").toString());
			posts.setDatestamp((String) postsMap.get("Datestamp").toString());
			String s_setPosts_id = (String) postsMap.get("Posts_id").toString();
			posts.setPosts_id(new Integer(s_setPosts_id).intValue());
			postsList.add(posts);
		}
		return postsList;
	}

	/**
	 * read those posts not posted by the user within a subject
	 * 
	 * @param userEmail
	 * @param subject
	 * @return
	 */
	public List<Posts> readNonUserPostsWithinSubject(String userEmail, String subject) {
		Integer usersId = new UserDao().readUserId(userEmail);
		Integer subjectsId = new SubjectsDAO().fetchSubjectId(subject);
		List<Posts> postsList = new ArrayList<Posts>();
		List<Map<String, Object>> postsListMap = super.jdbcSetUp()
				.getSimpleJdbcTemplate()
				.queryForList(nonUserPostsWithinSubjectSql, usersId, subjectsId);
		for (Map<String, Object> postsMap : postsListMap) {
			Posts posts = new Posts();
			posts.setContent((String) postsMap.get("Content").toString());
			posts.setTitle((String) postsMap.get("Title").toString());
			posts.setDatestamp((String) postsMap.get("Datestamp").toString());
			String s_setPosts_id = (String) postsMap.get("Posts_id").toString();
			posts.setPosts_id(new Integer(s_setPosts_id).intValue());
			postsList.add(posts);
		}
		return postsList;
	}
}
