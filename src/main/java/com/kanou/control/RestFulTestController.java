package com.kanou.control;

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
        return "hahahah";
    }

    @PostMapping("/test")
    public CocRole testRest(@RequestBody CocRole role){
        System.out.println(role);
        return role;
    }

    //=====================================================================================
    // @RequestParam注解可以绑定请求中的数据名称 即使不叫id
    @ResponseBody
    @RequestMapping(value = "/testid")
    public String test(@RequestParam("id") String id1){
        System.out.println(id1);
        return "hello";
    }

    // 如果请求体中的参数和形参中实体的名字相同，会自动填入
    // 如果有引用类型  前端传参时使用  字段名.引用类型里面的字段名做key 也能传进来
    @ResponseBody
    @RequestMapping(value = "/testpojo")
    public String test(CocRole cocRole) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(cocRole);
    }

    // 参数用多个likes为key 就可以传进来
    @ResponseBody
    @RequestMapping(value = "/testarray")
    public String testarray(String[] likes) {
        return Arrays.toString(likes);
    }

    // 集合是一个引用类型 不像数组 需要加@RequestParam注解 就能接到参数 仅限于非json格式
    @ResponseBody
    @RequestMapping(value = "/testlist")
    public String testarray(@RequestParam List<String> likes) {
        return likes.toString();
    }

    // 集合 用于json格式
    @ResponseBody
    @RequestMapping(value = "/testjsonlist")
    public List<String> testjsonlist(@RequestBody List<String> likes) {
        return likes;
    }

    // 集合 用于json格式
    @ResponseBody
    @RequestMapping(value = "/testjsonpojo")
    public CocRole testjsonpojo(@RequestBody CocRole cocRole) {
        return cocRole;
    }
}
