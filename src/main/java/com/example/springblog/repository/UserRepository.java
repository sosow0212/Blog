package com.example.springblog.repository;

import com.example.springblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// DAO
// 자동으로 bean 등록
// @Repository 생략가능

@Repository // JpaRepository에 빈으로 등록되는게 있어서, 굳이 이 어노테이션을 안 써도 됨
public interface UserRepository extends JpaRepository<User, Integer> {

}


// JPA Naming Query 전략
// SELECT * FROM user WHERE username = ? AND password = ?;


//    User findByUsernameAndPassword(String username, String password);
//
//    @Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
//    User login(String username, String password);
