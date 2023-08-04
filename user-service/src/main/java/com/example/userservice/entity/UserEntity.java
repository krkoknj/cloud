package com.example.userservice.entity;

import com.example.userservice.dto.UserDto;
import com.example.userservice.vo.ResponseUser;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String userId;
    private String encryptedPwd;

    @Builder
    public UserEntity(String email, String name, String userId, String encryptedPwd) {
        this.email = email;
        this.name = name;
        this.userId = userId;
        this.encryptedPwd = encryptedPwd;
    }

    protected UserEntity() {

    }

    public UserDto toDto() {
        return UserDto.builder()
                .pwd(encryptedPwd)
                .name(name)
                .email(email)
                .userId(userId)
                .build();
    }

    public ResponseUser toResponseUser() {
        return ResponseUser.builder()
                .name(name)
                .email(email)
                .userId(userId)
                .build();
    }
}
