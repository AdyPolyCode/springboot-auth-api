package com.poly.schoolDataManager.services;

import com.poly.schoolDataManager.entities.User;
import com.poly.schoolDataManager.exceptions.UserNotFoundException;
import com.poly.schoolDataManager.payload.PayloadMapper;
import com.poly.schoolDataManager.payload.request.UserCreatePayload;
import com.poly.schoolDataManager.payload.request.UserUpdatePayload;
import com.poly.schoolDataManager.payload.response.UserPayload;
import com.poly.schoolDataManager.repositories.UserRepository;
import com.poly.schoolDataManager.security.PasswordManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository repository;

    private PasswordManager passwordManager;

    @Autowired
    public UserService(UserRepository repository, PasswordManager passwordManager) {
        this.repository = repository;
        this.passwordManager = passwordManager;
    }

    public UserPayload create(UserCreatePayload payload) {
        User user = new User();
        UserPayload p = new UserPayload();

        String hashedPassword = passwordManager.encode(payload.getPassword());
        payload.setPassword(hashedPassword);

        new PayloadMapper<UserCreatePayload, User>(payload, user).mapWithParamsFrom(payload);
        new PayloadMapper<User, UserPayload>(user, p).mapWithParamsFrom(p);

        repository.saveAndFlush(user);
        return p;
    }

    public UserPayload updateById(Long id, UserUpdatePayload payload) {
        Optional<User> user = repository.findById(id);

        if(user.isEmpty()) {
            throw new UserNotFoundException("User with id: " + id + " was not found");
        }

        UserPayload p = new UserPayload();
        new PayloadMapper<UserUpdatePayload, User>(payload, user.get()).mapWithParamsFrom(payload);
        new PayloadMapper<User, UserPayload>(user.get(), p).mapWithParamsFrom(p);

        repository.saveAndFlush(user.get());

        return p;
    }

    public void deleteById(Long id) {
        Optional<User> user = repository.findById(id);

        if(user.isEmpty()) {
            throw new UserNotFoundException("User with id: " + id + " was not found");
        }

        repository.delete(user.get());
    }

    public List<UserPayload> getAll() {
        List<User> users = repository.findAll();
        List<UserPayload> payload = new ArrayList<>();

        users.forEach(user -> {
            UserPayload p = new UserPayload();
            new PayloadMapper<User, UserPayload>(user, p).mapWithParamsFrom(p);
            payload.add(p);
        });

        return payload;
    }

    public UserPayload getById(Long id) {
        Optional<User> user = repository.findById(id);

        if(user.isEmpty()) {
            throw new UserNotFoundException("User with id: " + id + " was not found");
        }

        UserPayload payload = new UserPayload();
        new PayloadMapper<User, UserPayload>(user.get(), payload).mapWithParamsFrom(payload);

        return payload;
    }

    public UserPayload getByUsername(String username) {
        Optional<User> user = repository.findByUsername(username);

        if(user.isEmpty()) {
            throw new UserNotFoundException("User with username: " + username + " was not found");
        }

        UserPayload payload = new UserPayload();
        new PayloadMapper<User, UserPayload>(user.get(), payload).mapWithParamsFrom(payload);

        return payload;
    }

    public UserPayload getByEmail(String email) {
        Optional<User> user = repository.findByEmail(email);

        if(user.isEmpty()) {
            throw new UserNotFoundException("User with email: " + email + " was not found");
        }

        UserPayload payload = new UserPayload();
        new PayloadMapper<User, UserPayload>(user.get(), payload).mapWithParamsFrom(payload);

        return payload;
    }
}
