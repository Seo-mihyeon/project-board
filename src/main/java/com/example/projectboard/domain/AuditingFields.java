package com.example.projectboard.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AuditingFields {
    // 엔티티들의 공통적인 부분을 따로 추출할 수 있다.
    // 자동으로 데이터 값이 생성된다. ( 자동으로 생성되기 위해서는 JpaConfig 설정이 이루어져야한다. )
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(nullable = false, updatable = false)    //null 불가, update 불가
    private LocalDateTime createdAt; //생성일시

    @CreatedBy
    @Column(nullable = false, length = 100)
    private String createdBy;   //생성자

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt;   //수정일시

    @LastModifiedBy
    @Column(nullable = false, length = 100)
    private String modifiedBy;  //수정자

}
