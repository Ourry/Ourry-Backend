package com.bluewhaletech.Ourry.domain.poll.repository;

import com.bluewhaletech.Ourry.domain.poll.PollChoice;
import com.bluewhaletech.Ourry.domain.article.Question;
import org.springframework.stereotype.Repository;

@Repository
public interface PollChoiceJpaRepository extends org.springframework.data.repository.Repository<PollChoice, Long> {
    boolean existsByQuestionAndSequence(Question question, int sequence);
}