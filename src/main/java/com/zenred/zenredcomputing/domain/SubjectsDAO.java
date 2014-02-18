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
	
	private class SubjectsName implements ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("Subjects_name");
		}
	
	}
	
	private class IntRowMapperSubjectIndex implements
			ParameterizedRowMapper<Integer> {
		public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getInt("Subjects_id");
		}
	}

	private class IntRowMapperUserIndex implements
			ParameterizedRowMapper<Integer> {
		public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getInt("User_id");
		}
	}
	
	public class AlreadyAssociated extends Exception{
		
	}
	public List<String> readSubjects(){
		List<String> subjects = null;
		String sql = "SELECT Subjects_name FROM "+tableName;
		subjects = super
				.jdbcSetUp()
				.getSimpleJdbcTemplate()
				.query(sql, new SubjectsName());
		return subjects;
	}
	
	@Transactional
	public void associateUserToSubject(String subject, String usersEMail) throws AlreadyAssociated {
		String subjectIndexSql = "SELECT Subjects_id FROM " + tableName
				+ " WHERE Subjects_name = ?";
		String userIndexSql = "SELECT User_id FROM User WHERE emailAddress = ?";
		
		String existingUserAndSubjectSql = "SELECT UserToSubjects_id FROM UserToSubjects WHERE Subjects_id = ? AND User_id = ?";

		Integer subjectsId = super.jdbcSetUp().getSimpleJdbcTemplate()
				.queryForInt(subjectIndexSql, new IntRowMapperSubjectIndex(), subject);
		Integer userId = super.jdbcSetUp().getSimpleJdbcTemplate()
				.queryForInt(userIndexSql, new IntRowMapperUserIndex(), usersEMail);
		
		Object[] param = {subjectsId, userId };
		Map<String, Object> userMap = null;
		userMap = super.jdbcSetUp().getSimpleJdbcTemplate()
				.queryForMap(existingUserAndSubjectSql, param);
		if(userMap.get("UserToSubjects_id") != null){
			throw new AlreadyAssociated();
		}
		
		Map<String, Object> insertMap = new HashMap<String, Object>();
		insertMap.put("Subjects_id", subjectsId);
		insertMap.put("User_id", userId);
		super.jdbcInsertSetup().withTableName(joinTableName)
				.usingColumns("Subjects_id", "User_id").execute(insertMap);
	}
}
