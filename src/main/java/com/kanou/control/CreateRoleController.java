package com.kanou.control;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kanou.entity.CocRole;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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
