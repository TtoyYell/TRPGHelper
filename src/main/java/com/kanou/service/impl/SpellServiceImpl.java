package com.kanou.service.impl;

import com.kanou.constant.ResponseCode;
import com.kanou.dao.SpellMapper;
import com.kanou.entity.ResponseResult;
import com.kanou.entity.Spell;
import com.kanou.service.SpellService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/9/19 10:53
 */
@Service
public class SpellServiceImpl implements SpellService {

    @Autowired
    SpellMapper mapper;

    Log log= LogFactory.getLog(SpellServiceImpl.class);

    @Override
    public ResponseResult addSpell(Spell condition) {
        condition.setDescription(condition.getDescription().replace("\n","\\n"));
        Integer res = mapper.addSpell(condition);
        return ResponseResult.setRes(ResponseCode.OK,res);
    }

    @Override
    public ResponseResult querySpell(String name) {
        List<Spell> res = mapper.querySpell("%"+name+"%");
        log.info(res.toString());
        return ResponseResult.setRes(ResponseCode.OK,res);
    }

    @Override
    public ResponseResult editSpell(Spell condition) {
        condition.setDescription(condition.getDescription().replace("\n","\\n"));
        Integer res = mapper.editSpell(condition);
        return ResponseResult.setRes(ResponseCode.OK,res);
    }
}
