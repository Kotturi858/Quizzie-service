package com.lot.quiz.app.quizzie.dto;

import lombok.Data;

import java.io.Serializable;

import lombok.AllArgsConstructor;


//public class LanguageDto implements Serializable {
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	public LanguageDto(Long languageId, String languageName) {
//		this.languageId = languageId;
//		this.languageName = languageName;
//	}
//
//	private Long languageId;
//	private String languageName;
//}

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

// This annotation ensures Jackson can "see" the private fields
@Data
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class LanguageDto implements Serializable {
    @JsonProperty("languageId")
    private Long languageId;
    
    @JsonProperty("name")
    private String name;
    
    // Default no-args constructor required by Jackson
    public LanguageDto() {
    }
    
    public LanguageDto(Long languageId, String name) {
        this.languageId = languageId;
        this.name = name;
    }
    
    // Standard getters and setters
    public Long getLanguageId() {
        return languageId;
    }
    
    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}