package com.poly.schoolDataManager.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "length_in_minutes", nullable = false)
    private Short lengthInMinutes;

    @OneToOne(targetEntity = Teacher.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacherId;

    @ManyToMany(targetEntity = Student.class)
    @JoinTable(
            name = "student_subject",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Student> studentId;

    @OneToOne(targetEntity = Room.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room roomId;

    public Subject() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setLengthInMinutes(Short lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    public Teacher getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Teacher teacherId) {
        this.teacherId = teacherId;
    }

    public Set<Student> getStudentId() {
        return studentId;
    }

    public void setStudentId(Set<Student> studentId) {
        this.studentId = studentId;
    }

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", lengthInMinutes=" + lengthInMinutes +
                ", teacherId=" + teacherId +
                ", studentId=" + studentId +
                ", roomId=" + roomId +
                '}';
    }
}
