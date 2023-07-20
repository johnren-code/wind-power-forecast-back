package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.WindFarmService;
import com.example.mybatisplus.model.domain.WindFarm;

import java.util.ArrayList;
import java.util.List;


/**
 *
 *  前端控制器
 *
 *
 * @author rbl
 * @since 2023-07-16
 * @version v1.0
 */
@Controller
@RequestMapping("/api/windFarm")
public class WindFarmController {

    private final Logger logger = LoggerFactory.getLogger( WindFarmController.class );

    @Autowired
    private WindFarmService windFarmService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        WindFarm  windFarm =  windFarmService.getById(id);
        return JsonResponse.success(windFarm);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        windFarmService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateWindFarm(WindFarm  windFarm) throws Exception {
        windFarmService.updateById(windFarm);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建WindFarm
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(WindFarm  windFarm) throws Exception {
        windFarmService.save(windFarm);
        return JsonResponse.success(null);
    }

    @RequestMapping(value = "getFarmDataById",method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getFarmDataById(@RequestParam("farm_id") Long farmId){
        List<WindFarm> windFarmList = new ArrayList<>();
        QueryWrapper<WindFarm> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("farm_id",farmId);
        windFarmList = windFarmService.list(queryWrapper);
        return JsonResponse.success(windFarmList);
    }

}

