package com.project.bank.service.Bank;

import com.project.bank.dto.BankDTO;
import com.project.bank.dto.LoanProductDTO;
import com.project.bank.entity.Bank;
import com.project.bank.entity.LoanProduct;
import com.project.bank.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class BankServiceImpl  implements   BankService{

    private final BankRepository bankRepository;
    @Override
    public Long register(BankDTO bankDTO) {

        Bank bank = Bank.builder()
                .bankName(bankDTO.getBankName())
                .build();

        Bank banklist = bankRepository.save(bank);



        return banklist.getBankNo();
    }

    @Override
    public BankDTO readOne(String bankName) {
        return null;
    }

    @Override
    public List<BankDTO> alllist() {

        List<Bank> bankList=   bankRepository.findAll();
        List<BankDTO> bankDTOList = new ArrayList<>();

        for(Bank bank: bankList){
            BankDTO bankDTO = BankDTO.of(bank);// entity->dto 메서드호출
            bankDTOList.add(bankDTO);

        }

        return bankDTOList;
    }



}
