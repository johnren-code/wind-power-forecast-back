package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.common.utls.ExcelUtil;
import com.example.mybatisplus.mapper.UserMapper;
import com.example.mybatisplus.model.domain.Feedback;
import com.example.mybatisplus.mapper.FeedbackMapper;
import com.example.mybatisplus.model.domain.User;
import com.example.mybatisplus.model.dto.PageDTO;
import com.example.mybatisplus.model.vo.FeedbackVO;
import com.example.mybatisplus.model.vo.UserVO;
import com.example.mybatisplus.service.FeedbackService;
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
 * @since 2023-08-12
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {
    @Autowired
    private FeedbackMapper feedbackMapper;
    @Override
    public Page<FeedbackVO> pageList(PageDTO pageDTO, Feedback feedback) {
        Page<FeedbackVO> page = new Page<>(pageDTO.getPageNo(), pageDTO.getPageSize());
        QueryWrapper<Feedback> adminQueryWrapper = new QueryWrapper<>();
        if(Objects.nonNull(feedback.getFarmId())){
            adminQueryWrapper.eq("farm_id",feedback.getFarmId());
        }
        if(Objects.nonNull(feedback.getState())){
            adminQueryWrapper.eq("state",feedback.getState());
        }
        feedbackMapper.selectMyPage(page,feedback);
        return page;
    }

    @Override
    public void exportFeedback(HttpServletResponse response, Feedback feedback) {
        PageDTO pageDTO = new PageDTO().setPageSize(-1);
        Page<FeedbackVO> pageList = pageList(pageDTO, feedback);

        //导出
        try {
            ExcelUtil.exportFeedback(response, pageList.getRecords());
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
