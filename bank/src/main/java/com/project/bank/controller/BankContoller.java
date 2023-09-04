package com.project.bank.controller;

import com.project.bank.dto.BankDTO;
import com.project.bank.dto.LoanProductDTO;
import com.project.bank.entity.Bank;
import com.project.bank.service.Bank.BankService;
import com.project.bank.service.Loan.LoanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/bank")
@RequiredArgsConstructor
@Log4j2
public class BankContoller {

    private final LoanService loanService;
    private final BankService bankService;

    @GetMapping(value = "list")
    public String getBanklist(Model model){

        List<BankDTO> bankDTOList =  bankService.alllist();

        model.addAttribute("bankDTOList",bankDTOList);


        return "Bank/Bank";
    }

    @GetMapping(value = "find/{bankName}")
    public String getBankfind(@PathVariable("bankName")String bankName, Model model){
        log.info("대출상품 요청 ");
        log.info(bankName);

      List<LoanProductDTO> loanProductDTOList =  loanService.loanproductList(bankName);
        log.info(loanProductDTOList);

        model.addAttribute("list",loanProductDTOList);


        return "Bank/BankFind";
    }




}
