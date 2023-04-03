package com.equal.examinationapp.repo;

import com.equal.examinationapp.model.Exam;
import com.equal.examinationapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserById(Long id);

    @Query("select u.availableExams from User u WHERE u.id = :userId")
    List<Exam> findAvailableExamsByUserId(@Param("userId") Long userId);
}

