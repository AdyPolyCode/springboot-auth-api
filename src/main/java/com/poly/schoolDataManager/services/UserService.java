package com.poly.schoolDataManager.services;

import com.poly.schoolDataManager.entities.User;
import com.poly.schoolDataManager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create() {
        return null;
    }

    public User updateById() {
        return null;
    }

    public User deleteById() {
        return null;
    }

    public User getById() {
        return null;
    }

    public User getByUsername() {
        return null;
    }
}
