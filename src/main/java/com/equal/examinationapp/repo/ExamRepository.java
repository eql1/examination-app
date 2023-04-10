package com.equal.examinationapp.repo;

import com.equal.examinationapp.model.Exam;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    Optional<Exam> findExamById(Long id);

    @Transactional
    void deleteEmployeeById(Long id);
}
