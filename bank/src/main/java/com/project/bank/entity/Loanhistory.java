package com.project.bank.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name="loan_history")
@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class LoanHistory {

    @Id
    @Column(name = "loan_no")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long loanNo;                    //번호
    private String productName;            // 상품명
    private String loanMoney;               // 갚은대출금액
    private String borrowed ;               // 빌린 금액 
    private String interest;                // 이자율
    private LocalDateTime loanDate;         // 대출날짜
    private LocalDateTime redemptionDate;   // 상환일자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountNumber")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_no")
    private LoanProduct loanProduct;



}
