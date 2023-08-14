package com.example.mybatisplus.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.model.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.vo.UserVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rbl
 * @since 2023-06-25
 */
public interface UserMapper extends BaseMapper<User> {

    Page<UserVO> selectMyPage(@Param("page") Page<UserVO> page, @Param("user") User user);
}
