package com.poly.schoolDataManager.repositories;

import com.poly.schoolDataManager.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
