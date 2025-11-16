package com.bluewhaletech.Ourry.domain.member.repository;

import com.bluewhaletech.Ourry.domain.member.Member;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;

    public Long save(Member member) {
        if(member.getMemberId() == null) {
            em.persist(member);
        } else {
            em.merge(member);
        }
        return member.getMemberId();
    }

    public Member findOne(Long memberId) {
        return em.find(Member.class, memberId);
    }
}