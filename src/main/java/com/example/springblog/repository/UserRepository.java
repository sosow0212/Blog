package com.example.springblog.repository;

import com.example.springblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // JpaRepository에 빈으로 등록되는게 있어서, 굳이 이 어노테이션을 안 써도 됨
public interface UserRepository extends JpaRepository<User, Integer> {
}
