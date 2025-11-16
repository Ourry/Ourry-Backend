package com.bluewhaletech.Ourry.domain.poll;

import com.bluewhaletech.Ourry.domain.base.BaseEntity;
import com.bluewhaletech.Ourry.domain.article.Question;
import com.bluewhaletech.Ourry.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Poll extends BaseEntity {
    @Builder
    public Poll(Long pollId, Member member, Question question, PollChoice choice) {
        this.pollId = pollId;
        this.member = member;
        this.question = question;
        this.choice = choice;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "poll_id", nullable = false)
    private Long pollId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "choice_id", nullable = false)
    private PollChoice choice;
}