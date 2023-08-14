package com.example.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.model.domain.Dictionary;

import java.util.List;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author lxp
 * @since 2023-02-26
 */
public interface DictionaryService extends IService<Dictionary> {

    List<Dictionary> listByType(String type);
}
