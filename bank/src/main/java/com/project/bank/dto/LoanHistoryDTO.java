package com.project.bank.dto;

import com.project.bank.entity.Account;
import com.project.bank.entity.LoanProduct;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoanHistoryDTO {

    private Long loanNo;                    //번호
    private String product_name;            // 상품명
    private String loanMoney;               // 갚은대출금액
    private String borrowed ;               // 빌린 금액
    private String interest;                // 이자율
    private LocalDateTime loanDate;         // 대출날짜
    private LocalDateTime redemptionDate;   // 상환일자


    private Account account;
    private LoanProduct loanProduct;

}
