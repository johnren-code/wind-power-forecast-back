package com.example.mybatisplus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisplusApplicationTests {

    @Autowired
    private AdminService adminService;
    @Test
    void contextLoads() {
        Admin byId = adminService.getById(1);
        System.out.println(byId);
    }

}
