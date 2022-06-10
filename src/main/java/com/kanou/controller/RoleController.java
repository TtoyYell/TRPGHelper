package com.kanou.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kanou.entity.CocRole;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/5/28 20:41
 */
@RestController

public class RoleController {

    @GetMapping(value = "/home")
    @SneakyThrows
    public String addRole(CocRole role) {
        ObjectMapper objectMapper = new ObjectMapper();
        String res = objectMapper.writeValueAsString(role);
        return res;
    }
}
