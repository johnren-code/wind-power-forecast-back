package com.example.mybatisplus.model.dto;

import lombok.Data;

@Data
public class UserRegister {
    private String email;
    private String code;
    private String username;
    private String password;
}
