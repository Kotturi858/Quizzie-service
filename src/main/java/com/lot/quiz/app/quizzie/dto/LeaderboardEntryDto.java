package com.lot.quiz.app.quizzie.dto;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;

@Data
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class LeaderboardEntryDto {

    // Default constructor
    public LeaderboardEntryDto() {
    }

    // Parameterized constructor
    public LeaderboardEntryDto(int rank, String name, int score) {
        this.rank = rank;
        this.name = name;
        this.score = score;
    }
	@JsonProperty("rank")
    private int rank;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("score")
    private int score;
}
