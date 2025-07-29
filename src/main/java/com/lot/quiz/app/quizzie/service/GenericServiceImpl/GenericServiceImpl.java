package com.lot.quiz.app.quizzie.service.GenericServiceImpl;

import com.lot.quiz.app.quizzie.Repo.GenericRepo.*;
import com.lot.quiz.app.quizzie.dto.BadgeDto;
import com.lot.quiz.app.quizzie.dto.LanguageDto;
import com.lot.quiz.app.quizzie.dto.LeaderboardEntryDto;
import com.lot.quiz.app.quizzie.dto.QuizResultDto;
import com.lot.quiz.app.quizzie.models.Language;
import com.lot.quiz.app.quizzie.models.Score;
import com.lot.quiz.app.quizzie.models.User;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class GenericServiceImpl {

    @Autowired
    private BadgeRepository badgeRepository;

    @Autowired
    private BadgeStudentRepository badgeStudentRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public GenericServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private static final Logger logger = LoggerFactory.getLogger(GenericServiceImpl.class);

    public QuizResultDto onload(Long userId) {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            Score latestScore = scoreRepository.findTopByUserUserIdOrderByDateTakenDesc(userId);
            return latestScore != null ? latestScore.getScore() : 0;
        });

        CompletableFuture<List<LanguageDto>> future2 = CompletableFuture.supplyAsync(() -> {
            List<Language> languages = languageRepository.findAll();
            return languages.stream().map(lang -> new LanguageDto(lang.getLanguageId(), lang.getLanguageName())).collect(Collectors.toList());
        });

        CompletableFuture<List<Integer>> future3 = CompletableFuture.supplyAsync(() -> {
            List<Score> recentScores = scoreRepository.findTop3ByUserUserIdOrderByDateTakenDesc(userId);
            List<Integer> mappedies = new ArrayList<Integer>();
            mappedies.add(recentScores.get(0).getCorrectQuestions());
            mappedies.add(recentScores.get(0).getIncorrectQuestions());
            mappedies.add(recentScores.get(0).getSkippedQuestions());

            return mappedies;
        });

        CompletableFuture<List<BadgeDto>> future4 = CompletableFuture.supplyAsync(() -> {
            List<Object[]> badgeAchievements = badgeStudentRepository.findUserBadgeAchievements(userId);
            List<BadgeDto> badgeDtos = new ArrayList<>();

            for (Object[] row : badgeAchievements) {
                String badgeName = (String) row[0];
                boolean isAchieved = (boolean) row[1];
                badgeDtos.add(new BadgeDto(badgeName, isAchieved));
            }
            return badgeDtos;
        });

        CompletableFuture<List<LeaderboardEntryDto>> future5 = CompletableFuture.supplyAsync(() -> {
            List<Object[]> leaderboardData = scoreRepository.findTopScores();
            List<LeaderboardEntryDto> leaderboardEntries = new ArrayList<>();

            for (Object[] row : leaderboardData) {
                String name = (String) row[0];
                int score = ((Number) row[1]).intValue();
                int rank = ((Number) row[2]).intValue();
                leaderboardEntries.add(new LeaderboardEntryDto(rank, name, score));
            }
            return leaderboardEntries;
        });

        CompletableFuture<QuizResultDto> ultimateFuture = CompletableFuture.allOf(future1, future2, future3, future4, future5).thenApply(v -> {
            QuizResultDto resultDto = new QuizResultDto();
//            Why not Create resultDto outside thenApply block:
//            If multiple threads access or reuse the same resultDto, you'll get:
//            Unexpected values
//            Thread safety issues
//            Hard-to-debug bugs in concurrent environments

//          Why Create resultDto inside thenApply is safe:
//            Every thread gets its own resultDto instance
//            No shared state, no race conditions
//            Fully thread-safe and isolated per execution

            resultDto.setScore(future1.join());
            resultDto.setAvailableLanguages(future2.join());
            resultDto.setGraphData(future3.join());
            resultDto.setBadgeData(future4.join());
            resultDto.setLeaderBoard(future5.join());
            return resultDto;
        }).exceptionally(ex -> {
            logger.error("Error while fetching data: ", ex);
            return new QuizResultDto();
        });


        return ultimateFuture.join();
    }

    @Transactional
    public String register(String userName, String email, String password) {
        // Check if user already exists
        if (userRepository.findByEmail(email).isPresent()) {
            return "User with this email already exists.";
        }

        // Create and save new user
        User newUser = new User();
        newUser.setUserName(userName);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password)); // Consider hashing the password before saving
        logger.info(newUser + "Registering new user: " + newUser.getUserName() + ", email: " + newUser.getEmail() + ", password: " + newUser.getPassword());

        userRepository.save(newUser);
        return newUser.getUserName();
    }
}
