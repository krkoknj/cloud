package com.example.userservice.vo;

import com.example.userservice.dto.UserDto;
import lombok.Data;

@Data
public class RequestUser {
    private String email;
    private String name;
    private String password;

    public UserDto toDto() {
        return UserDto.builder()
                .email(email)
                .name(name)
                .pwd(password)
                .build();
    }
}
