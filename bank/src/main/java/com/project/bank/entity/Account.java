package com.project.bank.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Table(name="Account")
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @Column(name = "account_number")
    private String accountNumber;       // 계좌 번호
    private LocalDateTime cerateDate;   // 생성 일자
    private int balance;                // 계좌 잔액
    private String password;            // 계좌 비밀번호

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;              // 회원 아이디



}
