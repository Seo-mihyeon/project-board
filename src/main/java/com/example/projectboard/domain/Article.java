package com.example.projectboard.domain;

import java.time.LocalDate;

public class Article {

    private long id;
    private String title;   //제목
    private String content; //내용
    private String hashtag; //해시태그

    private LocalDate createAt; //생성일시
    private String createdBy;   //생성자
    private LocalDate modifiedAt;   //수정일시
    private String modifiedBy;  //수정자

}
