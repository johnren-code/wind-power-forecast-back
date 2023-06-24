package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.WindFarm;
import com.example.mybatisplus.mapper.WindFarmMapper;
import com.example.mybatisplus.service.WindFarmService;
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
public class WindFarmServiceImpl extends ServiceImpl<WindFarmMapper, WindFarm> implements WindFarmService {

}
