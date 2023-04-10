package com.equal.examinationapp.controller;

import com.equal.examinationapp.model.Exam;
import com.equal.examinationapp.model.User;
import com.equal.examinationapp.service.ExamService;
import com.equal.examinationapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ExamService examService;
    public UserController(UserService userService, ExamService examService) {
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/availableExams")
    public ResponseEntity<List<Exam>> getAvailableExamsByUserId(@PathVariable("id") Long id) {
        List<Exam> exams = userService.findAvailableExamsByUserId(id);
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @PostMapping("/{userId}/addAvailableExam/{examId}")
    public ResponseEntity<User> addAvailableExamToUser(@PathVariable("userId") Long userId, @PathVariable("examId") Long examId) {
        User user = userService.findUserById(userId);
        Exam exam = examService.findExamById(examId);
//        if(user.getAvailableExams().contains((exam))) {
//            return new ResponseEntity<>(user, HttpStatus.ALREADY_REPORTED);
//        }
        user.getAvailableExams().add(exam);
        exam.getUsersWithAccess().add(user);
        userService.updateUser(user);
        examService.updateExam(exam);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/{userId}/deleteAvailableExam/{examId}")
    public ResponseEntity<?> deleteAvailableExamFromUser(@PathVariable("userId") Long userId, @PathVariable("examId") Long examId) {
        User updatedUser = userService.findUserById(userId);

        // CHange to Set
        List<Exam> availableExams = updatedUser.getAvailableExams();
        for (Exam exam : availableExams) {
            if(exam.getId().equals(examId)) {
                availableExams.remove(exam);
            }
        }
        updatedUser.setAvailableExams(availableExams);
        userService.updateUser(updatedUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping("/{userId}/findAvailableExams")
    public ResponseEntity<List<Exam>> getAvailableExams(@PathVariable("userId") Long userId) {
        List<Exam> availableExams = userService.findAvailableExamsByUserId(userId);
        return new ResponseEntity<>(availableExams, HttpStatus.OK);
    }
}
