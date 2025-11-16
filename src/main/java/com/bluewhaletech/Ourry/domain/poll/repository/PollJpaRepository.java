package com.bluewhaletech.Ourry.domain.poll.repository;

import com.bluewhaletech.Ourry.domain.poll.PollChoice;
import com.bluewhaletech.Ourry.domain.member.Member;
import com.bluewhaletech.Ourry.domain.article.Question;
import com.bluewhaletech.Ourry.domain.poll.Poll;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PollJpaRepository extends org.springframework.data.repository.Repository<Poll, Long> {
    Poll findByPollId(Long pollId);

    List<Poll> findByQuestion(Question question);

    boolean existsByMemberAndQuestion(Member member, Question question);

    int countByQuestionAndChoice(Question question, PollChoice choice);
}