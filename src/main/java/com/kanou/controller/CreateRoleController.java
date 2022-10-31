package com.kanou.controller;

import com.kanou.entity.CocRole;
import com.kanou.util.BeanCreateUtil;
import com.kanou.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    ConfigurableApplicationContext context;

    @ApiOperation(value = "创建角色")
    @ResponseBody
    @RequestMapping(value = "/createRole",method = RequestMethod.POST)
    public String createRole(@RequestBody CocRole role){
        return "hello";
    }

    @ApiOperation(value = "测试动态注入bean")
    @ResponseBody
    @GetMapping("/testBean")
    public String testBean(){
        Map<String,Object> argsMap = new HashMap<>();
        argsMap.put("id","10");
        argsMap.put("plName","詹姆斯");
        argsMap.put("pcName","乔纳森");
        argsMap.put("age",20);
        argsMap.put("profession","侦探");
        argsMap.put("sex","男");
        return StringUtils.valueOf(BeanCreateUtil.registerBean(context,CocRole.class,"testName",argsMap));
    }

}
