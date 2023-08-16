package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.utls.ExcelUtil;
import com.example.mybatisplus.model.domain.User;
import com.example.mybatisplus.mapper.UserMapper;
import com.example.mybatisplus.model.dto.PageDTO;
import com.example.mybatisplus.model.vo.UserVO;
import com.example.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rbl
 * @since 2023-06-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Page<UserVO> pageList(PageDTO pageDTO, User user) {
        Page<UserVO> page = new Page<>(pageDTO.getPageNo(), pageDTO.getPageSize());
        System.out.println("pagesize");
        System.out.println(pageDTO.getPageSize());
        QueryWrapper<User> adminQueryWrapper = new QueryWrapper<>();
        if(!StringUtils.isBlank(user.getAccount())){
            adminQueryWrapper.like("account",user.getAccount());
        }
        if(!StringUtils.isBlank(user.getPassword())){
            adminQueryWrapper.like("password",user.getPassword());
        }
        if(Objects.nonNull(user.getRole())){
            adminQueryWrapper.eq("role",user.getRole());
        }
        userMapper.selectMyPage(page,user);
        return page;
    }

    @Override
    public void exportUser(HttpServletResponse response, User user) {
        PageDTO pageDTO = new PageDTO().setPageSize(-1);
        Page<UserVO> pageList = pageList(pageDTO, user);

        //导出
        try {
            ExcelUtil.exportUser(response, pageList.getRecords());
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
