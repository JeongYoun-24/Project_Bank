package com.project.bank.dto;

import com.project.bank.entity.Account;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class HistoryDTO {


    private Long historyId;
    private int balance;               // 계좌 잔액
    private LocalDateTime updateDate;   // 일출금 일자
    private String memberName;          // 출금일자
    private String chk;              // 일출금이자 여부
    private Account account;       // 계좌 번호


    public HistoryDTO(Long historyId, int balance, String memberName, String chk, Account account){
        this.historyId = historyId;
        this.balance = balance;
        this.memberName = memberName;
        this.chk = chk;
        this.account = account;

    }


}
