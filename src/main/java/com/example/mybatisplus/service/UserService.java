package com.example.mybatisplus.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.model.dto.PageDTO;
import com.example.mybatisplus.model.vo.UserVO;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rbl
 * @since 2023-06-25
 */
public interface UserService extends IService<User> {
    Page<UserVO> pageList(PageDTO pageDTO, User user);
    void exportUser(HttpServletResponse response, User user);
}
