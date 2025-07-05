package com.lot.quiz.app.quizzie.dto;

import lombok.Data;

import java.io.Serializable;

import lombok.AllArgsConstructor;

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