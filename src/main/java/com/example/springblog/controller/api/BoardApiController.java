package com.example.springblog.controller.api;

import com.example.springblog.config.auth.PrincipalDetail;
import com.example.springblog.dto.ResponseDto;
import com.example.springblog.model.Board;
import com.example.springblog.model.Reply;
import com.example.springblog.model.User;
import com.example.springblog.repository.ReplyRepository;
import com.example.springblog.service.BoardService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    // 글쓰기처리
    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        boardService.글쓰기(board, principalDetail.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable("id") Integer id) {
        boardService.글삭제하기(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable("id") Integer id, @RequestBody Board board) {
        boardService.글수정하기(id, board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }



    // 데이터를 받을 때, 원래 컨트롤러에서 dto를 만들어서 받는게 좋음
    // dto를 사용하지 않는 이유는, 큰 프로젝트에서는 데이터가 오고가는게 많으니 꼭 써야함
    // 댓글쓰기
    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(@PathVariable("boardId") Integer boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principalDetail) {

        boardService.댓글쓰기(principalDetail.getUser(), boardId, reply);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyDelete(@AuthenticationPrincipal PrincipalDetail principalDetail ,@PathVariable("boardId") Integer boardId, @PathVariable("replyId") Integer replyId) {

        User replyWriter = boardService.댓글주인(replyId);
        if(replyWriter == principalDetail.getUser()) {
            boardService.댓글삭제(replyId);
            return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
        } else {
            return new ResponseDto<Integer>(HttpStatus.FORBIDDEN.value(), 1);
        }

    }
}
