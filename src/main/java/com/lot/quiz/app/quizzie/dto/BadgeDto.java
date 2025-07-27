package com.lot.quiz.app.quizzie.dto;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;

@Data
public class BadgeDto {
    @JsonProperty("badgeName")
    private String badgeName;

    // Use JsonProperty to ensure the field name in JSON is "isAchieved"
    @JsonProperty("isAchieved")
    private boolean achieved;

    // Default constructor
    public BadgeDto() {
    }

    // Parameterized constructor
    public BadgeDto(String badgeName, boolean achieved) {
        this.badgeName = badgeName;
        this.achieved = achieved;
    }

    // Standard getters and setters
    public String getBadgeName() {
        return badgeName;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    // Note: Using isAchieved() instead of getIsAchieved() to follow Java Bean
    // conventions
    public boolean isAchieved() {
        return achieved;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }
}
