package com.poly.schoolDataManager.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "room")
public class Room extends BaseEntity {

    @Column(name = "type", nullable = false, unique = true)
    private String type;

    @Column(name = "capacity", nullable = false)
    private Short capacity;

    @OneToOne(mappedBy = "roomId")
    private Subject subject;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Short getCapacity() {
        return capacity;
    }

    public void setCapacity(Short capacity) {
        this.capacity = capacity;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + getId() +
                ", type='" + type + '\'' +
                ", capacity=" + capacity +
                ", subject=" + subject +
                '}';
    }
}
