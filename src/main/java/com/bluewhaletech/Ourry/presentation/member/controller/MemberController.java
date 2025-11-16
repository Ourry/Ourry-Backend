package com.bluewhaletech.Ourry.presentation.member.controller;

import com.bluewhaletech.Ourry.infrastructure.jwt.JwtProvider;
import com.bluewhaletech.Ourry.application.member.MemberService;
import com.bluewhaletech.Ourry.presentation.auth.dto.EmailAuthenticationDTO;
import com.bluewhaletech.Ourry.presentation.auth.dto.JwtDTO;
import com.bluewhaletech.Ourry.presentation.auth.dto.MemberLoginDTO;
import com.bluewhaletech.Ourry.presentation.auth.dto.MemberRegistrationDTO;
import com.bluewhaletech.Ourry.presentation.member.dto.*;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.UnsupportedEncodingException;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService, JwtProvider tokenProvider) {
            this.memberService = memberService;
    }

    /**
     * 회원정보 불러오기 API
     * @return dto (email, nickname, phone, createdAt)
     */
    @GetMapping("/member/getMemberInfo")
    public ResponseEntity<Object> getMemberInfo(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization");
        return ResponseEntity.ok().body(memberService.getMemberInfo(accessToken));
    }

    /**
     * 닉네임 변경 API
     * @param dto (nickname, password)
     */
    @PostMapping("/member/updateNickname")
    public ResponseEntity<Object> updateNickname(HttpServletRequest request, @RequestBody NicknameUpdateDTO dto) {
        String accessToken = request.getHeader("Authorization");
        memberService.updateNickname(accessToken, dto);
        return ResponseEntity.ok().build();
    }

    /**
     * 비밀번호 재설정 API
     * @param dto (email, newPassword, confirmPassword)
     */
    @PostMapping("/member/passwordReset")
    public ResponseEntity<Object> passwordReset(@RequestBody PasswordResetDTO dto) {
        memberService.passwordReset(dto);
        return ResponseEntity.ok().build();
    }

    /**
     * 회원가입 API
     * @param dto (email, password, nickname, phone)
     */
    @PostMapping("/member/createAccount")
    public ResponseEntity<Object> createAccount(HttpServletRequest request, @RequestBody MemberRegistrationDTO dto) {
        memberService.createAccount(dto);
        return ResponseEntity.ok().build();
    }

    /**
     * 회원 로그인 API
     * @param dto (email, password)
     * @return jwt (JWT)
     */
    @PostMapping("/member/memberLogin")
    public ResponseEntity<Object> memberLogin(@RequestBody MemberLoginDTO dto, HttpServletRequest request, HttpServletResponse response) {
        JwtDTO newJwt = memberService.memberLogin(dto);
        response.setHeader("Authorization", "Bearer " + newJwt.getAccessToken());
        response.setHeader("Refresh", "Bearer " + newJwt.getRefreshToken());
        return ResponseEntity.ok().build();
    }

    /**
     * JWT Token 재발급 API
     */
    @PostMapping("/member/reissueToken")
    public ResponseEntity<Object> reissueToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = request.getHeader("Refresh");
        JwtDTO newJwt = memberService.reissueToken(refreshToken);
        response.setHeader("Authorization", "Bearer " + newJwt.getAccessToken());
        response.setHeader("Refresh", "Bearer " + newJwt.getRefreshToken());
        return ResponseEntity.ok().build();
    }

    /**
     * 회원 로그아웃 API
     */
    @PostMapping("/member/memberLogout")
    public ResponseEntity<Object> memberLogout(HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization");
        memberService.memberLogout(accessToken);
        return ResponseEntity.ok().build();
    }

    /**
     * 인증코드 발송 API
     * @param dto (email)
     * @return code (이메일 인증 코드)
     */
    @PostMapping("/member/sendAuthenticationCode")
    public ResponseEntity<Object> sendAuthenticationCode(@RequestBody EmailAddressDTO dto) throws MessagingException, UnsupportedEncodingException {
        memberService.sendAuthenticationCode(dto);
        return ResponseEntity.ok().build();
    }

    /**
     * 인증코드 비교 API
     * @param dto (email, code)
     */
    @PostMapping("/member/emailAuthentication")
    public ResponseEntity<Object> emailAuthentication(@RequestBody EmailAuthenticationDTO dto) {
        memberService.emailAuthentication(dto);
        return ResponseEntity.ok().build();
    }
}