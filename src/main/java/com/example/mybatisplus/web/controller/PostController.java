package com.example.mybatisplus.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.PostService;
import com.example.mybatisplus.model.domain.Post;


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
@RequestMapping("/api/post")
public class PostController {

    private final Logger logger = LoggerFactory.getLogger( PostController.class );

    @Autowired
    private PostService postService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Post  post =  postService.getById(id);
        return JsonResponse.success(post);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        postService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updatePost(Post  post) throws Exception {
        postService.updateById(post);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建Post
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(Post  post) throws Exception {
        postService.save(post);
        return JsonResponse.success(null);
    }
}

