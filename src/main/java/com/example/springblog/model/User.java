package com.example.springblog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
//@DynamicInsert // Insert시에 null인 필드를 제외시킴
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 20)
    private String username; // 아이디

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

//    @ColumnDefault("user")
    // DB는 RoleType이라는게 없어서, String임을 알려준다.
    @Enumerated(EnumType.STRING)
    private RoleType role; // Enum을 쓰면, ADMIN,USER로 형이 강제가 된다.

    @CreationTimestamp // 시간이 자동 입력
    private Timestamp createDate;
}
