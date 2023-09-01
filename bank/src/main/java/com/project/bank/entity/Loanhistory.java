package com.project.bank.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name="loan_history")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Loanhistory {

    @Id
    @Column(name = "loan_no")
    private Long loanNo;                    //번호
    private String product_name;            // 상품명
    private String loanMoney;               // 대출금액
    private LocalDateTime loanDate;         // 대출날짜
    private LocalDateTime redemptionDate;   // 상환일자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountNumber")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_no")
    private LoanProduct loanProduct;



}
