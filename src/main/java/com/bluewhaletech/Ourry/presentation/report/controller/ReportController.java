package com.bluewhaletech.Ourry.presentation.report.controller;

import com.bluewhaletech.Ourry.presentation.report.dto.ReportRegistrationDTO;
import com.bluewhaletech.Ourry.application.report.ReportService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * 신고 목록 불러오기 API
     * @return List<ReportDTO> (articleType, articleId, status, targetEmail, targetNickname, reportCnt, createdAt)
     */
    @GetMapping("/report/getReportList")
    public ResponseEntity<Object> getReportList(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization");
        return ResponseEntity.ok().body(reportService.getReportList(accessToken));
    }

    /*
     * 신고 상세내용 불러오기 API
     * @param dto (reportId)
     * @return

    @GetMapping("/report/getReportDetail")
    public ResponseEntity<Object> getReportDetail(HttpServletRequest request, ReportIdDTO dto) {
        String accessToken = request.getHeader("Authorization");
        return ResponseEntity.ok().body(reportService.getReportDetail(accessToken, dto));
    }
    */

    /**
     * 신고 등록하기 API
     * @param dto (email, password, nickname, phone)
     */
    @PostMapping("/report/addReport")
    public ResponseEntity<Object> addReport(HttpServletRequest request, @RequestBody ReportRegistrationDTO dto) {
        String accessToken = request.getHeader("Authorization");
        reportService.addReport(accessToken, dto);
        return ResponseEntity.ok().build();
    }
}