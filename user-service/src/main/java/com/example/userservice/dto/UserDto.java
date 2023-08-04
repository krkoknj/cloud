package com.example.userservice.dto;

import com.example.userservice.entity.UserEntity;
import com.example.userservice.vo.ResponseOrder;
import com.example.userservice.vo.ResponseUser;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private String email;
    private String name;
    private String pwd;
    private String userId;
    private Date createdAt;

    private String encryptedPwd;

    private List<ResponseOrder> orders;

    @Builder
    public UserDto(String email, String name, String pwd, String userId) {
        this.email = email;
        this.name = name;
        this.pwd = pwd;
        this.userId = userId;
    }

    public UserEntity toEntity() {
        return UserEntity.builder()
                .userId(userId)
                .email(email)
                .name(name)
                .encryptedPwd(encryptedPwd)
                .build();
    }

    public ResponseUser toResponse() {
        return ResponseUser.builder()
                .userId(userId)
                .email(email)
                .name(name)
                .build();
    }
}
