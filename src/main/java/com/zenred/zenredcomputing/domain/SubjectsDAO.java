package com.zenred.zenredcomputing.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class SubjectsDAO extends AbstractJDBCDao {

	private static String tableName = "Subjects";
	
	private class SubjectsName implements ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("Subjects_name");
		}
	
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
}
