package com.example.springblog.controller;

import com.example.springblog.config.auth.PrincipalDetail;
import com.example.springblog.model.Board;
import com.example.springblog.model.User;
import com.example.springblog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 전체 글 보기
    @GetMapping({"", "/"})
    public String index(Model model, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("boards", boardService.글목록(pageable));
        return "index";
    }

    // 일상 카테고리
    @GetMapping("/daily")
    public String daily(Model model, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("boards", boardService.카테고리글목록(pageable, "daily"));
        return "daily";
    }

    // 데이트 카테고리
    @GetMapping("/date")
    public String date(Model model, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("boards", boardService.카테고리글목록(pageable, "date"));
        return "date";
    }




    @GetMapping("/board/{id}")
    public String findById(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("board", boardService.글상세보기(id));
        return "board/detail";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable("id") Integer id, Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        Board board = boardService.글상세보기(id);
        User boardWriter = board.getUser();
        if(boardWriter.equals(principalDetail.getUser())) {
            model.addAttribute("board", boardService.글상세보기(id));
            return "board/updateForm";
        } else {
            return "redirect:/";
        }


    }



    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }
}
