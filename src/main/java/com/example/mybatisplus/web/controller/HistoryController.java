package com.example.mybatisplus.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.HistoryService;
import com.example.mybatisplus.model.domain.History;


/**
 *
 *  前端控制器
 *
 *
 * @author rbl
 * @since 2023-05-21
 * @version v1.0
 */
@Controller
@RequestMapping("/api/history")
public class HistoryController {

    private final Logger logger = LoggerFactory.getLogger( HistoryController.class );

    @Autowired
    private HistoryService historyService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        History  history =  historyService.getById(id);
        return JsonResponse.success(history);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        historyService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateHistory(History  history) throws Exception {
        historyService.updateById(history);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建History
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(History  history) throws Exception {
        historyService.save(history);
        return JsonResponse.success(null);
    }
}

