package com.example.springblog.controller.api;

import com.example.springblog.dto.ResponseDto;
import com.example.springblog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class testApi {

    @Autowired
    private BoardService boardService;

    @GetMapping("/api/test")
    public ResponseDto<?> test() {
        return new ResponseDto<>(HttpStatus.OK.value(), boardService.allBoard());

    }
}
