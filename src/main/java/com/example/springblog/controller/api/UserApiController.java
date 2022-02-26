package com.example.springblog.controller.api;

import com.example.springblog.dto.ResponseDto;
import com.example.springblog.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("UserApiController : save 호출됨");
        return new ResponseDto<Integer>(HttpStatus.OK, 1); // 자바 오브젝트를 JSON으로 변환하여 전송 (JACKSON)
    }
}
