package com.equal.examinationapp.controller;

import com.equal.examinationapp.model.Exam;
import com.equal.examinationapp.model.User;
import com.equal.examinationapp.service.ExamService;
import org.apache.coyote.Response;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/exams")
public class ExamController {
    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Exam>> getAllExams() {
        List<Exam> exams = examService.findAllExams();
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExamById(@PathVariable("id") Long id) {
        Exam exam = examService.findExamById(id);
        return new ResponseEntity<>(exam, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Exam> addExam(@RequestBody Exam exam) {
        Exam newExam = examService.addExam(exam);
        return new ResponseEntity<>(newExam, HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteExam(@PathVariable("id") Long id) {
        examService.deleteExam(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
