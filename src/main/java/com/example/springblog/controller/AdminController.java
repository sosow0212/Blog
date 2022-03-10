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
        if (user.getRole() == RoleType.ADMIN) {
            return "/admin/adminMainPage";
        } else {
            System.out.println("나 실행됨 2");
            return "redirect:/";
        }
    }


    // 전체 회원 관리
    @GetMapping("/admin/manage/member")
    public String manageMember(@AuthenticationPrincipal PrincipalDetail principalDetail) {
        User user = principalDetail.getUser();
        if (user.getRole() == RoleType.ADMIN) {





            return "/admin/manageMember";
        } else {
            System.out.println("나 실행됨 2");
            return "redirect:/";
        }


    }



    // 방문 통계 관리
    @GetMapping("/admin/manage/visit")
    public String manageVisit(@AuthenticationPrincipal PrincipalDetail principalDetail) {

        User user = principalDetail.getUser();
        if (user.getRole() == RoleType.ADMIN) {






            return "/admin/manageVisit";
        } else {
            System.out.println("나 실행됨 2");
            return "redirect:/";
        }
    }

}
