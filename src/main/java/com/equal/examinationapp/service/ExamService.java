package com.equal.examinationapp.service;

import com.equal.examinationapp.exception.ExamNotFoundException;
import com.equal.examinationapp.model.Exam;
import com.equal.examinationapp.repo.ExamRepository;
import org.springframework.stereotype.Service;

@Service
public class ExamService {
    private ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public Exam findExamById(Long id) {
        return examRepository.findExamById(id)
                .orElseThrow(() -> new ExamNotFoundException("Exam with id " + id + "not found"));
    }

    public Exam addExam(Exam exam) {
        return examRepository.save(exam);
    }
}
