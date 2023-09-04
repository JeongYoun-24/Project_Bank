package com.project.bank.service.Loan;

import com.project.bank.dto.LoanHistoryDTO;
import com.project.bank.dto.LoanProductDTO;
import com.project.bank.entity.LoanHistory;
import com.project.bank.entity.LoanProduct;

import java.util.List;

public interface LoanService  {


    public List<LoanProductDTO> loanList();  // 대출 전체 리스트

    public List<LoanProductDTO> loanproductList(String bankName);  // 은행에대한 상품 조회

    public LoanProductDTO findLoan(Long productNo);               // 상품 하나 조회

    public Long loanHistoryupdeate(LoanHistoryDTO loanHistoryDTO,String accountNumber,Long productNo);   // 대출 내역 생성


}
