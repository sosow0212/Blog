package com.example.springblog.service;

import com.example.springblog.model.Board;
import com.example.springblog.model.RoleType;
import com.example.springblog.model.User;
import com.example.springblog.repository.BoardRepository;
import com.example.springblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public User findUserById(int id) {
        return userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("유저 찾기 실패 : 아이디를 찾을 수 없습니다.");
        });
    }

    public List<Board> findAllBoardByUser(User user) {
       return boardRepository.findAllByUserOrderByIdDesc(user);
    }

    @Transactional
    public void changeRole(int userId, User roleUser) {
        User persistance = userRepository.findById(userId).orElseThrow(()->{
            return new IllegalArgumentException("회원 찾기 실패");
        });

        persistance.setRole(roleUser.getRole());
    }
}
