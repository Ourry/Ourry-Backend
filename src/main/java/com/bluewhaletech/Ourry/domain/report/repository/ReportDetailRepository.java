package com.bluewhaletech.Ourry.domain.report.repository;

import com.bluewhaletech.Ourry.domain.report.ReportDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ReportDetailRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(ReportDetail reportDetail) {
        if(reportDetail.getId() == null) {
            em.persist(reportDetail);
        } else {
            em.merge(reportDetail);
        }
    }
}