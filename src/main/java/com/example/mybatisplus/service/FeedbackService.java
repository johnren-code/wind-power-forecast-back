package com.example.mybatisplus.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.model.domain.Feedback;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.model.domain.User;
import com.example.mybatisplus.model.dto.PageDTO;
import com.example.mybatisplus.model.vo.FeedbackVO;
import com.example.mybatisplus.model.vo.UserVO;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rbl
 * @since 2023-08-12
 */
public interface FeedbackService extends IService<Feedback> {
    Page<FeedbackVO> pageList(PageDTO pageDTO, Feedback feedback);
    void exportFeedback(HttpServletResponse response, Feedback feedback);
}
