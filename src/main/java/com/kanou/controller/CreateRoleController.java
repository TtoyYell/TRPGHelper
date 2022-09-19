package com.kanou.controller;

import com.kanou.entity.CocRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 创建调查员请求
 * @author YeTianyi
 * @version 1.0
 * @date 2022/5/23 20:33
 */
@Api(tags = "创建角色")
@Controller
@RequestMapping("/coc")
public class CreateRoleController {

    @ApiOperation(value = "创建角色")
    @ResponseBody
    @RequestMapping(value = "/createRole",method = RequestMethod.POST)
    public String createRole(@RequestBody CocRole role){
        return "hello";
    }

}
