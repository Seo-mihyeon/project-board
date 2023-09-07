package com.example.projectboard.domain;

import java.time.LocalDate;

public class ArticleComment {

    private Long id;
    private Article article;    //게시글(ID)
    private String content; //내용

    private LocalDate createAt; //생성일시
    private String createdBy;   //생성자
    private LocalDate modifiedAt;   //수정일시
    private String modifiedBy;  //수정자
}
