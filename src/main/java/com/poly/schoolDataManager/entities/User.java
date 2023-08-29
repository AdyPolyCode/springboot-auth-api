package com.poly.schoolDataManager.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "the_user")
public class User extends BaseEntity {
    @OneToOne(mappedBy = "studentUser")
    private Student student;

    @OneToOne(mappedBy = "teacherUser")
    private Teacher teacher;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "hashed_password", unique = true, nullable = false)
    private String hashedPassword;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<UserRole> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", roles=" + roles +
                '}';
    }
}
