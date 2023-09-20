package com.example.projectboard.repository;

import com.example.projectboard.domain.Article;
import com.example.projectboard.domain.ArticleComment;
import com.example.projectboard.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<ArticleComment>,   //기본 검색기능을 추가해준다.
        QuerydslBinderCustomizer<QArticle>
{

    @Override
    default void customize(QuerydslBindings bindings, QArticle root){
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);

        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);  //like '%${v}%' % 형식이 들어가 있음
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);  //like '%${v}%' % 형식이 들어가 있음
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);  //like '%${v}%' % 형식이 들어가 있음
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);  //like '%${v}%' % 형식이 들어가 있음
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);  //like '%${v}%' % 형식이 들어가 있음
    }

}