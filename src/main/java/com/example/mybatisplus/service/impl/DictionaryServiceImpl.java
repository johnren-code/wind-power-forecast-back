package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisplus.mapper.DictionaryMapper;
import com.example.mybatisplus.model.domain.Dictionary;
import com.example.mybatisplus.service.DictionaryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author lxp
 * @since 2023-02-26
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {

    @Override
    public List<Dictionary> listByType(String type) {
        QueryWrapper<Dictionary> wrapper = new QueryWrapper<>();
        wrapper.eq("type",type).ne("parent_id",0);
        List<Dictionary> list=this.list(wrapper);
        return list;
    }
}
