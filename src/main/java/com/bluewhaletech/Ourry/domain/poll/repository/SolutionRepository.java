package com.bluewhaletech.Ourry.domain.poll.repository;

import com.bluewhaletech.Ourry.domain.poll.Solution;
import com.bluewhaletech.Ourry.domain.poll.PollId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class SolutionRepository {
    @PersistenceContext
    private EntityManager em;

    public PollId save(Solution solution) {
        if(solution.getPollId() == null) {
            em.persist(solution);
        } else {
            em.merge(solution);
        }
        return solution.getPollId();
    }
}
