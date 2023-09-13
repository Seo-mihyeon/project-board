package com.example.projectboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class Article extends AuditingFields{

    // 자동으로 번호 부여를 하기 때문에 따로 setter를 설정 하지 않는다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql의 경우 identity로 설정해야한다.
    private Long id;

    @Setter @Column(nullable = false) private String title;   //제목
    @Setter @Column(nullable = false) private String content; //내용

    @Setter private String hashtag; //해시태그

    @ToString.Exclude   // ToString이 ArticleCommet에 까지 가서 적용 되기 때문에 한번 끊어준다.
    @OrderBy("id")  //정렬기준 id
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL) // 양방향 바인드
    private final Set<ArticleComment> articleCommentSet = new LinkedHashSet<>();


    protected Article(){}

    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static Article of(String title, String content, String hashtag) {
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
