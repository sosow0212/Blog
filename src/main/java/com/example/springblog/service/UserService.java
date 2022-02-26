package com.example.springblog.service;

import com.example.springblog.model.User;
import com.example.springblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional // 전체가 성공시 Commit, 실패시 Rollback - springframework
    public void 회원가입(User user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true) // Select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 (정합성 유지)
    public User 로그인(User user) {
       return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
