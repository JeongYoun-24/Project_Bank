package com.project.bank.Repository;

import com.project.bank.BankApplication;
import com.project.bank.dto.BankDTO;
import com.project.bank.entity.Bank;
import com.project.bank.entity.LoanProduct;
import com.project.bank.repository.BankRepository;
import com.project.bank.repository.LoanProductRepository;
import com.project.bank.service.Bank.BankService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityExistsException;

@Log4j2
@AutoConfigureMockMvc
@SpringBootTest(classes = BankApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
public class LoanTest {

    @Autowired
    BankRepository bankRepository;
    @Autowired
    LoanProductRepository loanProductRepository;
    @Autowired
    BankService bankService;

    @Test
    @DisplayName(value = "은행 대출 등록 테스트 ")
    public void Test(){
       Long bankNo= 3L;

       Bank bank = bankRepository.findById(bankNo).orElseThrow(EntityExistsException::new);

        LoanProduct loanProduct = LoanProduct.builder()
                .productName("100만원 대출")
                .loanMoney("1000000")
                .interest("1.8")
                .repaymentDate("16개월")
                .bank(bank)
                .build();
        log.info(loanProduct);

        LoanProduct loanProductlist=  loanProductRepository.save(loanProduct);

        log.info(loanProductlist);

        LoanProduct loanProduct1 = LoanProduct.builder()
                .productName("200만원 대출")
                .loanMoney("2000000")
                .interest("2.1")
                .repaymentDate("24개월")
                .bank(bank)
                .build();

        loanProductRepository.save(loanProduct1);

        LoanProduct loanProduct2 = LoanProduct.builder()
                .productName("300만원 대출")
                .loanMoney("3000000")
                .interest("2.8")
                .repaymentDate("36개월")
                .bank(bank)
                .build();

        loanProductRepository.save(loanProduct2);




    }



}
