package com.kanou.service.impl;

import com.kanou.constant.ResponseCode;
import com.kanou.dao.SpellMapper;
import com.kanou.entity.ResponseResult;
import com.kanou.entity.Spell;
import com.kanou.service.SpellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public ResponseResult addSpell(Spell condition) {
        Integer res = mapper.addSpell(condition);
        return ResponseResult.setRes(ResponseCode.OK,res);
    }

    @Override
    public ResponseResult querySpell(Integer id) {
        Spell res = mapper.querySpell(id);
        return ResponseResult.setRes(ResponseCode.OK,res);
    }
}
