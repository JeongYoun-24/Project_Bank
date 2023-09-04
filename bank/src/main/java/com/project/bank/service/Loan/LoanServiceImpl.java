package com.project.bank.service.Loan;

import com.project.bank.dto.LoanHistoryDTO;
import com.project.bank.dto.LoanProductDTO;
import com.project.bank.entity.Account;
import com.project.bank.entity.Bank;
import com.project.bank.entity.LoanHistory;
import com.project.bank.entity.LoanProduct;
import com.project.bank.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class LoanServiceImpl  implements LoanService{


    private final LoanProductRepository loanProductRepository; // 대출 상품
    private final LoanHistoryRepository loanHistoryRepository; // 대출 내역
    private final HistoryRepository historyRepository;
    private final AccountRepository accountRepository;
    private final BankRepository bankRepository;    // 은행

    @Override
    public List<LoanProductDTO> loanList() {   // 대출 상품 전체 조회

      List<LoanProduct> loanProductList =  loanProductRepository.findAll();

        List<LoanProductDTO> loanProductDTOList = new ArrayList<>();

        for(LoanProduct loanProduct: loanProductList){
            LoanProductDTO loanProductDTO = LoanProductDTO.of(loanProduct);// entity->dto 메서드호출
            loanProductDTOList.add(loanProductDTO);

        }


        return loanProductDTOList;
    }

    @Override
    public List<LoanProductDTO> loanproductList(String bankName) { // 은행 아이디로 대출상품 조회

      Bank bankId =  bankRepository.findByBankName(bankName);


        Bank bank = bankRepository.findById(bankId.getBankNo()).orElseThrow(EntityExistsException::new);

       List<LoanProduct> loanProductList =  loanProductRepository.findByBank_BankNo(bank.getBankNo());

        List<LoanProductDTO> loanProductDTOList = new ArrayList<>();

        for(LoanProduct loanProduct: loanProductList){
            LoanProductDTO loanProductDTO = LoanProductDTO.of(loanProduct);// entity->dto 메서드호출
            loanProductDTOList.add(loanProductDTO);

        }


        return loanProductDTOList;
    }

    @Override
    public LoanProductDTO findLoan(Long productNo) { // 대출 상품 id 로 조회

    LoanProduct product =  loanProductRepository.findById(productNo).orElseThrow(EntityExistsException::new);


        LoanProductDTO loanProductDTO = LoanProductDTO.of(product);// entity->dto 메서드호출



        return loanProductDTO;
    }

    @Override
    public Long loanHistoryupdeate(LoanHistoryDTO loanHistoryDTO ,String accountNumber,Long productNo) {

       LoanProduct loanProduct = loanProductRepository.findById(productNo).orElseThrow(EntityExistsException::new);
        Account account = accountRepository.findById(accountNumber).orElseThrow(EntityExistsException::new);

        LoanHistory loanHistory = LoanHistory.builder()
                .borrowed(loanHistoryDTO.getBorrowed())  // 대출 금액
                .product_name(loanHistoryDTO.getProduct_name())  // 대출 상품 이름 
                .interest(loanHistoryDTO.getInterest())         // 이자율
                .loanDate(LocalDateTime.now())                   // 대출 일자 
                .loanProduct(loanProduct)                        // 대출 상품 아이디
                .account(account)                                // 계좌 번호
                .loanMoney("0")                                  // 갚은 금액
                .build();

       LoanHistory loanHistoryId = loanHistoryRepository.save(loanHistory);


        return loanHistoryId.getLoanNo();
//        return 0L;
    }
}
