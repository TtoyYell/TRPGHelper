package com.kanou.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kanou.entity.CocRole;
import com.kanou.service.RoleService;
import com.kanou.util.IPUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/5/28 20:41
 */
@Api(tags = "角色控制类")
@RestController
@RequestMapping("/role")
@CrossOrigin
public class RoleController {

    @Autowired
    RoleService roleService;

    Log log= LogFactory.getLog(RoleController.class);

    @GetMapping(value = "/home")
    @SneakyThrows
    public String addRole(CocRole role) {
        ObjectMapper objectMapper = new ObjectMapper();
        String res = objectMapper.writeValueAsString(role);
        return res;
    }

    @ApiOperation(value = "随机角色")
    @GetMapping(value = "/randomRole")
    @SneakyThrows
    public List<Map> randomRole(int quantity, HttpServletRequest request) {
        log.info("===============IP:"+IPUtil.getIpAddr(request));
        ArrayList<Map> res = new ArrayList<>();
        Date date = new Date();
        Long seed = Long.parseLong(String.format("%tN", date));
        for (int i = 0; i < quantity; i++) {
            res.add(roleService.randomRole(seed));
            seed++;
        }
        return res;
    }
}
