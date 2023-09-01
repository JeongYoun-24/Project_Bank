package com.project.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Table(name="loan_product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LoanProduct {


    @Id
    @Column(name="product_no")
    private Long productNo;
    private String productName;
    private String loanMoney;
    private String interest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_no")
    private Bank bank;



}
