package com.equal.examinationapp.service;

import com.equal.examinationapp.exception.UserNotFoundException;
import com.equal.examinationapp.model.User;
import com.equal.examinationapp.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public User findUserById(Long id) {
        return userRepo.findUserById(id).
                orElseThrow(() -> new UserNotFoundException("User by id " + id + " not found"));
    }

    public User addUser(User user) {
        user.setUserCode(UUID.randomUUID().toString());
        return userRepo.save(user);
    }
}
