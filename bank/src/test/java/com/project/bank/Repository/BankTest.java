package com.project.bank.Repository;

import com.project.bank.BankApplication;
import com.project.bank.dto.BankDTO;
import com.project.bank.entity.Bank;
import com.project.bank.repository.BankRepository;
import com.project.bank.service.Bank.BankService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@Log4j2
@AutoConfigureMockMvc
@SpringBootTest(classes = BankApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
public class BankTest {

    @Autowired
    BankRepository bankRepository;
    @Autowired
    BankService bankService;

    @Test
    @DisplayName(value = "은행명 테스트 ")
    public void Test(){

       /* Long id = 1L;
        String bankName = "국민은행";

        Bank bank = new Bank();
        bank.setBankNo(id);
        bank.setBankName(bankName);
        log.info(bank);

        bankRepository.save(bank);*/

        BankDTO bankDTO = BankDTO.builder()
                .bankName("한국은행")
                .build();

     Long bankId =  bankService.register(bankDTO);

     log.info(bankId);


    }



}
