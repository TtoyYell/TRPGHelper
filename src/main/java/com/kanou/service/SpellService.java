package com.kanou.service;

import com.kanou.entity.ResponseResult;
import com.kanou.entity.Spell;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/9/19 10:53
 */
public interface SpellService {

    ResponseResult addSpell(Spell condition);

    ResponseResult querySpell(String name);
}
