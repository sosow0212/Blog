package com.example.springblog.controller;

import com.example.springblog.config.auth.PrincipalDetail;
import com.example.springblog.model.RoleType;
import com.example.springblog.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminPage(@AuthenticationPrincipal PrincipalDetail principalDetail) {
        User user = principalDetail.getUser();
        System.out.println(user.getRole());

        if(user.getRole() == RoleType.ADMIN) {
            System.out.println("나 실행됨 1");
            return "/admin/adminMainPage";
        } else {
            System.out.println("나 실행됨 2");
            return "redirect:/";
        }

    }

}
