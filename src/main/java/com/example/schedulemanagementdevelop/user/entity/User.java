package com.example.schedulemanagementdevelop.user.entity;

import com.example.schedulemanagementdevelop.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String userName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public void modifyUserName(String newUserName) {
        if (!StringUtils.isEmpty(newUserName)) {
            this.userName = newUserName;
        }
    }

    public void modifyPassword(String newPassword) {
        this.password = newPassword;
    }
}
