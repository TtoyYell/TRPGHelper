package com.kanou.controller;

import com.kanou.entity.ResponseResult;
import com.kanou.service.SpellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/9/19 10:42
 */
@RestController
public class SpellController {

    @Autowired
    SpellService service;

    @PostMapping
    public ResponseResult addSpell(@RequestBody Map condition){
        return service.addSpell(condition);
    }

}
