package com.zenred.zenredcomputing.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Transactional;

public class SubjectsDAO extends AbstractJDBCDao {

	private static String tableName = "Subjects";
	private static String joinTableName = "UserToSubjects";
	
	private String subjectIndexSql = "SELECT Subjects_id FROM " + tableName
			+ " WHERE Subjects_name = ?";
	private String userIndexSql = "SELECT User_id FROM User WHERE emailAddress = ?";
	
	private String existingUserAndSubjectSql = "SELECT COUNT(*) FROM UserToSubjects WHERE Subjects_id = ? AND User_id = ?";

	
	private class SubjectsName implements ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("Subjects_name");
		}
	
	}
	
	
	public class AlreadyAssociated extends Exception{

		/**
		 * 
		 */
		private static final long serialVersionUID = 4604559942286856819L;
		
	}	
	
	public class NotAssociated extends Exception{

		/**
		 * 
		 */
		private static final long serialVersionUID = 4250258286584099820L;
		
	}
	
	/**
	 * list all subjects
	 * @return
	 */
	public List<String> readSubjects(){
		List<String> subjects = null;
		String sql = "SELECT Subjects_name FROM "+tableName;
		subjects = super
				.jdbcSetUp()
				.getSimpleJdbcTemplate()
				.query(sql, new SubjectsName());
		return subjects;
	}
	
	/**
	 * 
	 * @param subject
	 * @return subjectId
	 */
	public Integer fetchSubjectId(String subject){
		Integer subjectsId = super.jdbcSetUp().getSimpleJdbcTemplate()
				.queryForInt(subjectIndexSql,subject);
		return subjectsId;
	}
	
	/**
	 * 
	 * @param subject
	 * @param usersEMail
	 * @throws AlreadyAssociated
	 */
	@Transactional
	public void associateUserToSubject(String subject, String usersEMail) throws AlreadyAssociated {

		Integer subjectsId = super.jdbcSetUp().getSimpleJdbcTemplate()
				.queryForInt(subjectIndexSql,subject);
		Integer userId = super.jdbcSetUp().getSimpleJdbcTemplate()
				.queryForInt(userIndexSql, usersEMail);
		
		/*
		 * gets bummed out with zero rows returned
		 * 
		Object[] param = {subjectsId, userId };
		Map<String, Object> userMap = null;
		userMap = super.jdbcSetUp().getSimpleJdbcTemplate()
				.queryForMap(existingUserAndSubjectSql, param);
		if(userMap.get("UserToSubjects_id") != null){
			throw new AlreadyAssociated();
		}
		*/
		
		int count = super.jdbcSetUp().getSimpleJdbcTemplate().queryForInt(existingUserAndSubjectSql, subjectsId, userId);
		if(count > 0){
			throw new AlreadyAssociated();
		}
		
		Map<String, Object> insertMap = new HashMap<String, Object>();
		insertMap.put("Subjects_id", subjectsId);
		insertMap.put("User_id", userId);
		super.jdbcInsertSetup().withTableName(joinTableName)
				.usingColumns("Subjects_id", "User_id").execute(insertMap);
	}
	/**
	 * 
	 * @param subject
	 * @param usersEMail
	 * @return
	 */
	@Transactional
	public Boolean isUserAssociatedToSubject(String subject, String usersEMail){
		Boolean alreadyAssociated = true;
		
		Integer subjectsId = super.jdbcSetUp().getSimpleJdbcTemplate()
				.queryForInt(subjectIndexSql,subject);
		Integer userId = super.jdbcSetUp().getSimpleJdbcTemplate()
				.queryForInt(userIndexSql, usersEMail);

		int count = super.jdbcSetUp().getSimpleJdbcTemplate().queryForInt(existingUserAndSubjectSql, subjectsId, userId);
		if(count == 0){
			alreadyAssociated = false;
		}
		return alreadyAssociated;
	}
	/**
	 * 
	 * @param subject
	 * @param usersEMail
	 * @throws NotAssociated
	 */
	@Transactional
	public void disasssociateUserFromSubject(String subject, String usersEMail) throws NotAssociated{
		Integer subjectsId = super.jdbcSetUp().getSimpleJdbcTemplate()
				.queryForInt(subjectIndexSql,subject);
		Integer userId = super.jdbcSetUp().getSimpleJdbcTemplate()
				.queryForInt(userIndexSql, usersEMail);
		
		int count = super.jdbcSetUp().getSimpleJdbcTemplate().queryForInt(existingUserAndSubjectSql, subjectsId, userId);
		if(count == 0){
			throw new NotAssociated();
		}
		String sql = "DELETE FROM " + joinTableName + " WHERE Subjects_id = ? AND User_id = ?";
		super.jdbcSetUp().getSimpleJdbcTemplate().update(sql, subjectsId, userId);
	}
}
