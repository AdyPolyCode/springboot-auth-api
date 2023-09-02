package com.poly.schoolDataManager.services;

import com.poly.schoolDataManager.entities.User;
import com.poly.schoolDataManager.entities.UserRole;
import com.poly.schoolDataManager.exceptions.UserNotFoundException;
import com.poly.schoolDataManager.payload.PayloadMapper;
import com.poly.schoolDataManager.payload.request.UserCreatePayload;
import com.poly.schoolDataManager.repositories.UserRepository;
import com.poly.schoolDataManager.security.CustomJwtProvider;
import com.poly.schoolDataManager.security.PasswordManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class AuthService {
    private UserRepository repository;

    private PasswordManager passwordManager;

    private AuthenticationManager authenticationManager;

    private CustomJwtProvider jwtProvider;

    @Autowired
    public AuthService(UserRepository repository,
                       PasswordManager passwordManager,
                       AuthenticationManager authenticationManager,
                       CustomJwtProvider jwtProvider
    ) {
        this.repository = repository;
        this.passwordManager = passwordManager;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    public String login(String username, String password) {
        Optional<User> user = repository.findByUsername(username);

        if(user.isEmpty()) {
            throw new UserNotFoundException("Invalid user credentials");
        }

        String hashedPassword = passwordManager.encode(password);

        if(! hashedPassword.equals(user.get().getPassword())) {
            throw new UserNotFoundException("Invalid user credentials");
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return jwtProvider.createToken(username, user.get().getRoles());
    }

    public String register(UserCreatePayload payload) {
        User user = new User();

        String hashedPassword = passwordManager.encode(payload.getPassword());
        payload.setPassword(hashedPassword);

        new PayloadMapper<UserCreatePayload, User>(payload, user).mapWithParamsFrom(payload);
        user.setRoles(new HashSet<>(){{
            add(UserRole.ROLE_USER);
        }});
        repository.saveAndFlush(user);

        return jwtProvider.createToken(user.getUsername(), user.getRoles());
    }
}
