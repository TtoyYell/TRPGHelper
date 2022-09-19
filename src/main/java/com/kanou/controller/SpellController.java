package com.kanou.controller;

import com.kanou.entity.ResponseResult;
import com.kanou.entity.Spell;
import com.kanou.service.SpellService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/9/19 10:42
 */
@Api(tags = "法术控制类")
@RestController
@RequestMapping("/DND")
@CrossOrigin
public class SpellController {

    @Autowired
    SpellService service;

    @ApiOperation(value = "增加法术")
    @PostMapping("/addSpell")
    public ResponseResult addSpell(@RequestBody Spell condition){
        return service.addSpell(condition);
    }

    @ApiOperation(value = "查询法术")
    @GetMapping("/querySpell")
    public ResponseResult querySpell(Integer id){
        return service.querySpell(id);
    }

}
