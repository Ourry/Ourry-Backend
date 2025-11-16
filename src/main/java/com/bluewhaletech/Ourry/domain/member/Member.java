package com.bluewhaletech.Ourry.domain.member;

import com.bluewhaletech.Ourry.domain.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * 회원 Entity
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {
    @Builder
    protected Member(Long memberId, String email, String password, String nickname, String phone, MemberRole role) {
        this.memberId = memberId;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Setter
    @Column(name = "password", nullable = false)
    private String password;

    @Setter
    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberRole role;
}