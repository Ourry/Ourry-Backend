package com.bluewhaletech.Ourry.domain.poll.repository;

import com.bluewhaletech.Ourry.domain.poll.PollChoice;
import com.bluewhaletech.Ourry.domain.article.Question;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class PollChoiceRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(PollChoice choice) {
        if(choice.getChoiceId() == null) {
            em.persist(choice);
        } else {
            em.merge(choice);
        }
    }

    public PollChoice findByQuestionAndSequence(Question question, int sequence) {
        TypedQuery<PollChoice> query = em.createQuery("select c from PollChoice c where c.question =: question and c.sequence =: sequence", PollChoice.class)
                .setParameter("question", question)
                .setParameter("sequence", sequence);
        return query.getSingleResult();
    }
}