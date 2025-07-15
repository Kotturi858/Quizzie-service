package com.lot.quiz.app.quizzie.Repo.GenericRepo;

import com.lot.quiz.app.quizzie.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query methods can be defined here if needed
    // For example,
     Optional<User> findByEmail(String email);
     Optional<User> findByUserName(String userName);
}
