package com.example.springblog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content; // 섬머노트 라이브러리 사용할 예정 - <html> 태그가 섞여서 디자인이 됨.

    private int count; // 조회수

    @ManyToOne(fetch = FetchType.EAGER) // EAGER은 바로 로드하는 것임
    @JoinColumn(name="userId")
    private User user;

    // 기본 패치 전략은 LAZY 전략임
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // 연관관계의 주인이 아니다 == FK가 아니라는 뜻, DB에 컬럼을 만들지 말라는 뜻
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;


}
