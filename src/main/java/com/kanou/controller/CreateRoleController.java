package com.kanou.controller;

import com.kanou.entity.CocRole;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 创建调查员请求
 * @author YeTianyi
 * @version 1.0
 * @date 2022/5/23 20:33
 */
// 如果所有的请求返回都是responsebody的话 可以用@RestController注解
@Controller
@RequestMapping("/coc")
public class CreateRoleController {

    @ResponseBody
    @RequestMapping(value = "/createRole",method = RequestMethod.POST)
    public String createRole(@RequestBody CocRole role){
        return "hello";
    }


}
