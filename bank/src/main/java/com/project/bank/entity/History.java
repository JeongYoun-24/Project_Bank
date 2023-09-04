package com.project.bank.entity;

import com.project.bank.entity.Account;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Table(name="historys")
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class History {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long Id;
    private int balance;               // 계좌 잔액
    private int saveBalance;           // 잔액 변동전 금액
    private LocalDateTime updateDate;   // 일출금 일자
    private String memberName;          // 입출금자 이름
    private String chk;              // 일출금이자 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountNumber")
    private Account account;            // 계좌 번호


}
