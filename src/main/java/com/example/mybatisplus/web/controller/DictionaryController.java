package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.Dictionary;
import com.example.mybatisplus.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 *
 *  前端控制器
 *
 *
 * @author lxp
 * @since 2023-02-26
 * @version v1.0
 */
@Controller
@RequestMapping("/api/dictionary")
public class DictionaryController {

    private final Logger logger = LoggerFactory.getLogger( DictionaryController.class );

    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping(value = "listByType",method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse list(String type){
        List<Dictionary> dictionaryList = dictionaryService.listByType(type);
        return JsonResponse.success(dictionaryList);
    }
    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Dictionary  dictionary =  dictionaryService.getById(id);
        return JsonResponse.success(dictionary);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        dictionaryService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateDictionary(Dictionary  dictionary) throws Exception {
        dictionaryService.updateById(dictionary);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建Dictionary
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(Dictionary  dictionary) throws Exception {
        dictionaryService.save(dictionary);
        return JsonResponse.success(null);
    }
}

