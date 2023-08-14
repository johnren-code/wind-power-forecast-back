package com.example.mybatisplus.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.model.domain.Feedback;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.domain.User;
import com.example.mybatisplus.model.vo.FeedbackVO;
import com.example.mybatisplus.model.vo.UserVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author rbl
 * @since 2023-08-12
 */
public interface FeedbackMapper extends BaseMapper<Feedback> {

    Page<FeedbackVO> selectMyPage(@Param("page") Page<FeedbackVO> page, @Param("feedback") Feedback feedback);
}
