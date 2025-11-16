package com.bluewhaletech.Ourry.domain.report.repository;

import com.bluewhaletech.Ourry.domain.member.Member;
import com.bluewhaletech.Ourry.domain.report.Report;
import com.bluewhaletech.Ourry.domain.report.ReportDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportDetailJpaRepository extends org.springframework.data.repository.Repository<ReportDetail, Long> {
    boolean existsByReportAndAuthor(Report report, Member member);

    List<ReportDetail> findByReport(Report report);
}