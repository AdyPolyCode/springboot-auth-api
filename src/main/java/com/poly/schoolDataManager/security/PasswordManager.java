package com.poly.schoolDataManager.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordManager extends BCryptPasswordEncoder {
}
