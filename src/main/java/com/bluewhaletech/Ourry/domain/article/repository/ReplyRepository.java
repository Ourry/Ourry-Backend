package com.bluewhaletech.Ourry.domain.article.repository;

import com.bluewhaletech.Ourry.domain.article.Reply;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ReplyRepository {
    @PersistenceContext
    private EntityManager em;

    public Long save(Reply reply) {
        if(reply.getReplyId() == null) {
            em.persist(reply);
        } else {
            em.merge(reply);
        }
        return reply.getReplyId();
    }
}
