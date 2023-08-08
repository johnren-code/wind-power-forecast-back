package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.model.domain.WindFarm;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.WindFarmUrlService;
import com.example.mybatisplus.model.domain.WindFarmUrl;

import java.util.ArrayList;
import java.util.List;


/**
 *
 *  前端控制器
 *
 *
 * @author rbl
 * @since 2023-07-20
 * @version v1.0
 */
@Controller
@RequestMapping("/api/windFarmUrl")
public class WindFarmUrlController {

    private final Logger logger = LoggerFactory.getLogger( WindFarmUrlController.class );

    @Autowired
    private WindFarmUrlService windFarmUrlService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        WindFarmUrl  windFarmUrl =  windFarmUrlService.getById(id);
        return JsonResponse.success(windFarmUrl);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        windFarmUrlService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateWindFarmUrl(WindFarmUrl  windFarmUrl) throws Exception {
        windFarmUrlService.updateById(windFarmUrl);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建WindFarmUrl
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(WindFarmUrl  windFarmUrl) throws Exception {
        windFarmUrlService.save(windFarmUrl);
        return JsonResponse.success(null);
    }

    /**
     * 描述:创建新的风电场
     *
     */
    @RequestMapping(value = "/createFarm", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse createFarm(@RequestBody WindFarmUrl windFarmUrl){
        Long farmId = windFarmUrl.getFarmId();
        QueryWrapper<WindFarmUrl> windFarmUrlQueryWrapper = new QueryWrapper<>();
        windFarmUrlQueryWrapper.eq("farm_id",farmId);
        if(windFarmUrlService.getOne(windFarmUrlQueryWrapper)!=null){
            return JsonResponse.message(false,"该风电场已存在");
        }else {
            windFarmUrlService.saveOrUpdate(windFarmUrl);
        }
        return JsonResponse.message(true,"创建成功");
    }
    /**
     * 描述:查询得到风电场的id list
     *
     */
    @RequestMapping(value = "/getFarmIds",method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getFarmIds(){
        QueryWrapper<WindFarmUrl> windFarmUrlQueryWrapper = new QueryWrapper<>();
        windFarmUrlQueryWrapper.select("farm_id");
        List<Object> windFarmUrlList = windFarmUrlService.listObjs(windFarmUrlQueryWrapper);
        return JsonResponse.success(windFarmUrlList);
    }

    @RequestMapping(value = "/getOriginFileById",method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getFarmDataById(@RequestParam("farm_id") Long farmId){
        QueryWrapper<WindFarmUrl> windFarmUrlQueryWrapper = new QueryWrapper<>();
        windFarmUrlQueryWrapper.eq("farm_id",farmId);
        WindFarmUrl one = windFarmUrlService.getOne(windFarmUrlQueryWrapper);
        return JsonResponse.success(one);
    }

    /**
     * 描述:创建新的风电场
     *
     */
    @RequestMapping(value = "/updateFarm", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse updateFarm(@RequestBody WindFarmUrl windFarmUrl){
        Long farmId = windFarmUrl.getFarmId();
        String filePath = windFarmUrl.getOriginFileUrl();
        QueryWrapper<WindFarmUrl> windFarmUrlQueryWrapper = new QueryWrapper<>();
        windFarmUrlQueryWrapper.eq("farm_id",farmId);
        WindFarmUrl one = windFarmUrlService.getOne(windFarmUrlQueryWrapper);
        one.setOriginFileUrl(filePath);
        windFarmUrlService.updateById(one);
        return JsonResponse.message(true,"更新成功");
    }

    /**
     * 描述:创建新的风电场
     *
     */
    @RequestMapping(value = "/getAllFarm", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getAllFarm(){
        List<WindFarmUrl> list = windFarmUrlService.list();
        return JsonResponse.success(list);
    }

    /**
     * 描述:删除风电场
     *
     */
    @RequestMapping(value = "/deleteFarm", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse deleteFarm(@RequestBody WindFarmUrl windFarmUrl){
        Long id = windFarmUrl.getId();
        windFarmUrlService.removeById(id);
        return JsonResponse.message(true,"删除成功");
    }
}

