package com.example.mybatisplus.common;

import java.util.HashMap;
import java.util.Map;

public interface BaseConstant {
    String[] ROLES = {"超级管理员","数据分析师","风场运维师"};
    Map<String,Long> ROLEMAP = new HashMap<String,Long>(){{
        put("超级管理员",1L);
        put("数据分析师",1L);
        put("风场运维师",3L);
    }};
}
