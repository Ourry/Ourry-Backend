package com.bluewhaletech.Ourry.domain.article;

import com.bluewhaletech.Ourry.domain.base.BaseEntity;
import com.bluewhaletech.Ourry.domain.member.Member;
import com.bluewhaletech.Ourry.domain.poll.PollChoice;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 질문 Entity
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question extends BaseEntity {
    @Builder
    public Question(Long questionId, String title, String content, ArticleCategory category, Member member, List<PollChoice> choices) {
        this.questionId = questionId;
        this.title = title;
        this.content = content;
        this.category = category;
        this.member = member;
        this.choices = choices;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_id", nullable = false)
    private Long questionId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
//    @JoinColumn(name = "category_id", nullable = false)
//    private Category category;
    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private ArticleCategory category;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "question")
    private List<PollChoice> choices;
}