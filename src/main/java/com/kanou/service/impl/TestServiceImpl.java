package com.kanou.service.impl;

import com.kanou.dao.CocRoleMapper;
import com.kanou.entity.CocRole;
import com.kanou.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/9/16 15:15
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    CocRoleMapper mapper;

    @Override
    public CocRole getCocRole() {
        return mapper.getById(1);
    }
}
