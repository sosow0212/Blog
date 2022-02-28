package com.example.springblog.controller;

import com.example.springblog.config.auth.PrincipalDetail;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


// 인증이 안된 사용자들이 출입할 수 있는 경로
// /auth/** 허용
// 그리고 그냥 주소가 / 이면 index.html 허용
// static 이하에 있는 /js/** , /css/**, /image/** 허용

@Controller
public class UserController {

    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }


    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }


    // 카카오
    @GetMapping("/auth/kakao/callback")
    public @ResponseBody String kakaoCallback(String code) {
        // @ResponseBody는 Data를 리턴해주는 컨트롤러 함수가 됨


        // POST방식으로 Key=Value 데이터를 요청 (카카오쪽으로)
        RestTemplate rt = new RestTemplate();

        // HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "19bc8b342e4af553e0ec5456a5c82bde");
        params.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
        params.add("code", code);

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);

        // Http 요청하기 - POST방식으로 - 그리고 response 변수의 응답 받음
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        return "카카오 토큰 요청 완료 : 토큰 요청에 대한 응답 : " + response;
    }



    @GetMapping("/user/updateForm")
    public String updateForm(Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        model.addAttribute("principal", principalDetail.getUser());
        return "/user/updateForm";
    }
}
