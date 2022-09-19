package com.kanou.service.impl;

import com.kanou.dao.SpellMapper;
import com.kanou.entity.ResponseResult;
import com.kanou.service.SpellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/9/19 10:53
 */
@Service
public class SpellServiceImpl implements SpellService {

    @Autowired
    SpellMapper mapper;

    @Override
    public ResponseResult addSpell(Map condition) {
        Integer res = mapper.addSpell(condition);
        return new ResponseResult(200,res,1);
    }
}
