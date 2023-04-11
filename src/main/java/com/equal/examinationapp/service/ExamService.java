package com.equal.examinationapp.service;

import com.equal.examinationapp.exception.ExamNotFoundException;
import com.equal.examinationapp.model.Exam;
import com.equal.examinationapp.model.User;
import com.equal.examinationapp.repo.ExamRepository;
import com.equal.examinationapp.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ExamService {
    private final ExamRepository examRepository;
    private final UserRepository userRepository;

    public ExamService(ExamRepository examRepository, UserRepository userRepository) {
        this.examRepository = examRepository;
        this.userRepository = userRepository;
    }

    public List<Exam> findAllExams() {
        return examRepository.findAll();
    }

    public Exam findExamById(Long id) {
        return examRepository.findExamById(id)
                .orElseThrow(() -> new ExamNotFoundException("Exam with id " + id + "not found"));
    }

    public Exam addExam(Exam exam) {
        return examRepository.save(exam);
    }
    public void deleteExam(Long id) {
        Optional<Exam> exam = examRepository.findExamById(id);
        if (exam.isPresent()) {
            List<User> usersWithAccess = exam.get().getUsersWithAccess();
            for (User user : usersWithAccess) {
                user.getAvailableExams().remove(exam.get());
                userRepository.save(user);
            }
            examRepository.deleteExamById(id);
        }
    }

    public Exam updateExam(Exam exam) {
        return examRepository.save(exam);
    }
}
