package com.equal.examinationapp.resource;

import com.equal.examinationapp.repo.ExamRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/exams")
public class ExamResource {
    private final ExamRepository examRepository;

    public ExamResource(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }
}
