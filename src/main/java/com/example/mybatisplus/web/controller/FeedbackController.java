package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.model.domain.User;
import com.example.mybatisplus.model.domain.WindFarmUrl;
import com.example.mybatisplus.model.dto.DeleteDTO;
import com.example.mybatisplus.model.dto.PageDTO;
import com.example.mybatisplus.model.vo.FeedbackVO;
import com.example.mybatisplus.model.vo.UserVO;
import com.example.mybatisplus.service.WindFarmUrlService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.FeedbackService;
import com.example.mybatisplus.model.domain.Feedback;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;


/**
 *
 *  前端控制器
 *
 *
 * @author rbl
 * @since 2023-08-12
 * @version v1.0
 */
@Controller
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final Logger logger = LoggerFactory.getLogger( FeedbackController.class );

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private WindFarmUrlService windFarmUrlService;
    /**
     * 描述:创建新的反馈
     *
     */
    @RequestMapping(value = "/createFeedback", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse createFeedback(@RequestBody Feedback feedback){
        Long farmId = feedback.getFarmId();
        LocalDateTime now = LocalDateTime.now();
        feedback.setCreateTime(now);
        QueryWrapper<WindFarmUrl> windFarmUrlQueryWrapper = new QueryWrapper<>();
        windFarmUrlQueryWrapper.eq("farm_id",farmId);
        if(windFarmUrlService.getOne(windFarmUrlQueryWrapper)==null){
            return JsonResponse.message(false,"该风电场不存在");
        }else {
            feedbackService.save(feedback);
        }
        return JsonResponse.message(true,"创建成功");
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse queryPageList(PageDTO pageDTO, Feedback feedback){
        System.out.println(feedback.getFarmId());
        Page<FeedbackVO> page = feedbackService.pageList(pageDTO,feedback);
        return JsonResponse.success(page);
    }

    @RequestMapping(value = "batchDeletes",method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse batchDeletes(@RequestBody DeleteDTO deleteDTO){
        boolean result=feedbackService.removeByIds(deleteDTO.getIds());
        return JsonResponse.success(result);
    }
    @RequestMapping(value = "singleDelete",method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse singDelete(@RequestBody DeleteDTO deleteDTO){
        boolean result=feedbackService.removeById(deleteDTO.getId());
        return JsonResponse.success(result);
    }

    @RequestMapping(value = "exportFeedback",method = RequestMethod.POST)
    @ResponseBody
    public void exportFeedback(HttpServletResponse response, @RequestBody Feedback feedback){
        feedbackService.exportFeedback(response,feedback);
    }

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Feedback  feedback =  feedbackService.getById(id);
        return JsonResponse.success(feedback);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        feedbackService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateFeedback(Feedback  feedback) throws Exception {
        feedbackService.updateById(feedback);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建Feedback
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(Feedback  feedback) throws Exception {
        feedbackService.save(feedback);
        return JsonResponse.success(null);
    }
}

