package com.bluewhaletech.Ourry.domain.poll.repository;

import com.bluewhaletech.Ourry.domain.poll.Solution;
import com.bluewhaletech.Ourry.domain.poll.Poll;
import org.springframework.stereotype.Repository;

@Repository
public interface SolutionJpaRepository extends org.springframework.data.repository.Repository<Solution, Long> {
    boolean existsByPoll(Poll poll);

    Solution findByPoll(Poll poll);
}