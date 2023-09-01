package com.project.bank.dto;

import com.project.bank.entity.Account;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
public class HistoryDetailDTO {  // 계좌 내역 조회 DTO


    private Long historyId;
    private int balance;               // 계좌 잔액
    private LocalDateTime updateDate;   // 일출금 일자
    private String memberName;          // 입출금자 이름
    private String chk;              // 일출금이자 여부
    private Account account;       // 계좌 번호


    public HistoryDetailDTO(Long historyId, int balance,LocalDateTime  updateDate  ,String memberName, String chk, Account account){
        this.historyId = historyId;
        this.balance = balance;
        this.updateDate = updateDate;
        this.memberName = memberName;
        this.chk = chk;
        this.account = account;

    }


}
