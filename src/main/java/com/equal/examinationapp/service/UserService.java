package com.equal.examinationapp.service;

import com.equal.examinationapp.exception.UserNotFoundException;
import com.equal.examinationapp.model.Exam;
import com.equal.examinationapp.model.User;
import com.equal.examinationapp.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findUserById(id).
                orElseThrow(() -> new UserNotFoundException("User by id " + id + " not found"));
    }


    public User addUser(User user) {
        user.setUserCode(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteUserById(id);
    }

    public List<Exam> findAvailableExamsByUserId(Long id) {
        return userRepository.findAvailableExamsByUserId(id);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
