package com.example.springblog.service;

import com.example.springblog.model.Board;
import com.example.springblog.model.User;
import com.example.springblog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;


    @Transactional
    public void 글쓰기(Board board, User user) { //title, content, count
        board.setCount(0); // 조회수
        board.setUser(user);

        boardRepository.save(board);
    }

    public List<Board> 글목록() {
        return boardRepository.findAll();
    }

}
