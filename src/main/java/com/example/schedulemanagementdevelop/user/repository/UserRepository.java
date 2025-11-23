package com.example.schedulemanagementdevelop.user.repository;

import com.example.schedulemanagementdevelop.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
