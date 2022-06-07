package com.kanou.control;

import com.kanou.entity.CocRole;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Ye Tianyi
 * @version 1.0
 * @date 2022/5/28 20:41
 */
@Controller
public class RoleController {

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public Object addRole(CocRole role, Model model){
        model.addAttribute("msg",role.getPcName());
        System.out.println(role.getPcName());
        return "home";
    }
}
