package com.lot.quiz.app.quizzie.Repo.GenericRepoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import com.lot.quiz.app.quizzie.models.Language;

public class LanguageRowMapper implements RowMapper<Language> {

	@Override
	public Language mapRow(ResultSet rs, int rowNum) throws SQLException {
		Language language = new Language();
		language.setLanguageId(rs.getInt("language_Id"));
		language.setLanguageName(rs.getString("language_Name"));
		return language;
	}

}
