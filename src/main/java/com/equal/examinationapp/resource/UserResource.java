package com.equal.examinationapp.resource;

import com.equal.examinationapp.model.Exam;
import com.equal.examinationapp.model.User;
import com.equal.examinationapp.service.ExamService;
import com.equal.examinationapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserResource {
    private final UserService userService;

    private final ExamService examService;

    public UserResource(UserService userService, ExamService examService) {
        this.userService = userService;
        this.examService = examService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/availableExams")
    public ResponseEntity<List<Exam>> getAvailableExamsByUserId(@PathVariable("id") Long id) {
        List<Exam> exams = userService.findAvailableExamsByUserId(id);
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @PostMapping("/{userId}/availableExams/{examId}")
    public ResponseEntity<?> addAvailableExamToUser(@PathVariable("userId") Long userId, @PathVariable("examId") Long examId) {
        User user = userService.findUserById(userId);
        Exam exam = examService.findExamById(examId);
        user.getAvailableExams().add(exam);
        userService.updateUser(user);
        return ResponseEntity.ok().build();
    }
}
