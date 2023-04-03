package com.equal.examinationapp.resource;

import com.equal.examinationapp.model.Exam;
import com.equal.examinationapp.repo.ExamRepository;
import com.equal.examinationapp.service.ExamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/exams")
public class ExamResource {
    private final ExamService examService;

    public ExamResource(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExamById(@PathVariable("id") Long id) {
        Exam exam = examService.findExamById(id);
        return new ResponseEntity<>(exam, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Exam> addExam(@RequestBody Exam exam) {
        return new ResponseEntity<>(exam, HttpStatus.CREATED);
    }
}
