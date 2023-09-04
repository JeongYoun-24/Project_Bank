package com.project.bank.dto;

import com.project.bank.entity.Bank;
import com.project.bank.entity.LoanProduct;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoanProductDTO {

    private Long productNo;             // 대출 번호
    private String productName;         // 대출 상품 이름
    private String loanMoney;           // 대출 금액
    private String interest;            // 이자율
    private String repaymentDate;       // 상환 날짜
//    private String loanDitail;          // 대출상품 설명

    private Bank bank;                  // 은행 아이디

    private static ModelMapper modelMapper = new ModelMapper();
    public static LoanProductDTO of(LoanProduct loanProduct){
        // entity -> dto
        return modelMapper.map(loanProduct, LoanProductDTO.class);
    }

}
