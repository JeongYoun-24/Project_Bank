package com.project.bank.Repository;

import com.project.bank.BankApplication;
import com.project.bank.entity.History;
import com.project.bank.repository.AccountRepository;
import com.project.bank.repository.HistoryRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Log4j2
@AutoConfigureMockMvc
@SpringBootTest(classes = BankApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
public class HistoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    HistoryRepository historyRepository;


    @Test
    @DisplayName(value = "계좌 내역 테스트 ")
    public void Test(){


        String accountNumber = "3333-06-2980990";
        log.info(accountNumber);

        List<History> histories = historyRepository.histryList(accountNumber);
        log.info(histories);

    }

}
