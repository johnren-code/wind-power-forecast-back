package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.History;
import com.example.mybatisplus.mapper.HistoryMapper;
import com.example.mybatisplus.service.HistoryService;
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
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, History> implements HistoryService {

}
