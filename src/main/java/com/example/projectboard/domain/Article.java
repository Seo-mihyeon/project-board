package com.example.projectboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class Article {

    // 자동으로 번호 부여를 하기 때문에 따로 setter를 설정 하지 않는다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql의 경우 identity로 설정해야한다.
    private Long id;

    @Setter @Column(nullable = false) private String title;   //제목
    @Setter @Column(nullable = false) private String content; //내용

    @Setter private String hashtag; //해시태그

    //자동으로 데이터 값이 생성된다. ( 자동으로 생성되기 위해서는 JpaConfig 설정이 이루어져야한다. )
    @CreatedDate @Column(nullable = false) private LocalDate createAt; //생성일시
    @CreatedBy @Column(nullable = false, length = 100) private String createdBy;   //생성자
    @LastModifiedDate @Column(nullable = false) private LocalDate modifiedAt;   //수정일시
    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy;  //수정자


    protected Article(){}

    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public Article of(String title, String content, String hashtag) {
        return new Article(title, content, hashtag);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return id != null && id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
