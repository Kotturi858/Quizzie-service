package com.lot.quiz.app.quizzie.Repo.GenericRepoImpl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lot.quiz.app.quizzie.models.Language;

@Repository
public class GenericRepoImpl {
	private final JdbcTemplate jdbcTemplate;

	public GenericRepoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Language> findAll() {
        return jdbcTemplate.query("SELECT * FROM languages", new LanguageRowMapper());
    }
}
