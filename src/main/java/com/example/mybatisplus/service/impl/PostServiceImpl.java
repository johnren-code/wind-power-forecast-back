package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.Post;
import com.example.mybatisplus.mapper.PostMapper;
import com.example.mybatisplus.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rbl
 * @since 2023-05-21
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

}
