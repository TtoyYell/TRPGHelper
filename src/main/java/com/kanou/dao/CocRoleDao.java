package com.kanou.dao;

import com.kanou.entity.CocRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author YeTianyi
 * @version 1.0
 * @date 2022/5/24 0:36
 */
@Mapper
@Repository
public interface CocRoleDao {
    @Select("select * from COC_ROLE where id = #{id}")
    CocRole getById(Integer id);
}
