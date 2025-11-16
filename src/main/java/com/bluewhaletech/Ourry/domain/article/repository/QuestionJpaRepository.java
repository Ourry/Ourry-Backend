package com.bluewhaletech.Ourry.domain.article.repository;

import com.bluewhaletech.Ourry.domain.article.ArticleCategory;
import com.bluewhaletech.Ourry.domain.member.Member;
import com.bluewhaletech.Ourry.domain.article.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionJpaRepository extends org.springframework.data.repository.Repository<Question, Long> {
    @Query(value = "select q from Question q where (q.title like %:searchKeyword%) or (q.content like %:searchKeyword%) or (q.member.nickname like %:searchKeyword) order by q.createdAt desc")
    List<Question> searchQuestionList(String searchKeyword);
    List<Question> findByCategory(ArticleCategory category);

    boolean existsByMemberAndQuestionId(Member member, Long questionId);
}