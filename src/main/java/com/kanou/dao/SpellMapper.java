package com.kanou.dao;

import com.kanou.entity.Spell;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/9/19 10:56
 */
@Mapper
@Repository
public interface SpellMapper {

    Integer addSpell(Spell condition);

    List<Spell> querySpell(String name);
}
