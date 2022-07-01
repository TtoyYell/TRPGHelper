package com.kanou.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kanou.entity.CocRole;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/5/28 1:15
 */
@RestController
@RequestMapping("/rest")
public class RestFulTestController {

    @GetMapping("/test/{name}")
    public String testRest(@PathVariable("name") String name){
        System.out.println(name);
        return name;
    }

    @PostMapping("/test")
    public CocRole testRest(@RequestBody CocRole role){
        System.out.println(role);
        return role;
    }

    //=====================================================================================
    // @RequestParam注解可以绑定请求中url里的的参数数据名称 即使不叫id
    @GetMapping(value = "/testid")
    public String test(@RequestParam("id") String id111){
        System.out.println(id111);
        return id111;
    }

    // 如果请求体中的参数和形参中实体的名字相同，会自动填入
    // 如果有引用类型  前端传参时使用  字段名.引用类型里面的字段名做key 也能传进来
    @GetMapping(value = "/testpojo")
    public String test(CocRole cocRole) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(cocRole);
    }

    // 参数用多个likes为key 就可以传进来
//    @GetMapping(value = "/testarray")
//    public String testarray(String[] likes) {
//        return Arrays.toString(likes);
//    }

    // 集合是一个引用类型 不像数组 需要加@RequestParam注解 就能接到参数 仅限于非json格式
    @GetMapping(value = "/testlist")
    public String testarray(@RequestParam List<String> likes) {
        return likes.toString();
    }

    // 集合 用于json格式
    @PostMapping(value = "/testjsonlist")
    public List<String> testjsonlist(@RequestBody List<String> likes) {
        return likes;
    }

    // 集合 用于json格式
    @PostMapping(value = "/testjsonpojo")
    public CocRole testjsonpojo(@RequestBody CocRole cocRole) {
        return cocRole;
    }
}
