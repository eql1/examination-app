package com.equal.examinationapp.service;

import com.equal.examinationapp.exception.ExamNotFoundException;
import com.equal.examinationapp.model.Exam;
import com.equal.examinationapp.model.User;
import com.equal.examinationapp.repo.ExamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {
    private ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
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
    examRepository.deleteEmployeeById(id);
    }

    public Exam updateExam(Exam exam) {
        return examRepository.save(exam);
    }
}
