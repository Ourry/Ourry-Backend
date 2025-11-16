package com.bluewhaletech.Ourry.domain.report.repository;

import com.bluewhaletech.Ourry.domain.article.ArticleType;
import com.bluewhaletech.Ourry.domain.report.Report;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportJpaRepository extends org.springframework.data.repository.Repository<Report, Long> {
    boolean existsByArticleIdAndArticleType(Long articleId, ArticleType articleType);

    Report findByArticleIdAndArticleType(Long articleId, ArticleType articleType);
}