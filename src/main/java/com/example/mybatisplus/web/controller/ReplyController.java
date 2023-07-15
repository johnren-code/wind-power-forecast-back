package com.example.mybatisplus.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.ReplyService;
import com.example.mybatisplus.model.domain.Reply;


/**
 *
 *  前端控制器
 *
 *
 * @author rbl
 * @since 2023-06-25
 * @version v1.0
 */
@Controller
@RequestMapping("/api/reply")
public class ReplyController {

    private final Logger logger = LoggerFactory.getLogger( ReplyController.class );

    @Autowired
    private ReplyService replyService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Reply  reply =  replyService.getById(id);
        return JsonResponse.success(reply);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        replyService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateReply(Reply  reply) throws Exception {
        replyService.updateById(reply);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建Reply
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(Reply  reply) throws Exception {
        replyService.save(reply);
        return JsonResponse.success(null);
    }
}

