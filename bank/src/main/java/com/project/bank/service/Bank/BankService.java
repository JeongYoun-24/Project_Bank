package com.project.bank.service.Bank;

import com.project.bank.dto.AccoutDTO;
import com.project.bank.dto.BankDTO;
import com.project.bank.entity.Bank;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface BankService {



    public Long register(BankDTO bankDTO); // 은행 등록
    public BankDTO readOne(String bankName);  // 은행 조회

    public List<BankDTO> alllist();  // 은행 전체 조회


}
