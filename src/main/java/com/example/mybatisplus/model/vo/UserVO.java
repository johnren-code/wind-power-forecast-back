package com.example.mybatisplus.model.vo;

import com.example.mybatisplus.model.domain.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserVO extends User {
    private String roleName;
}