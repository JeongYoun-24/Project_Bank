package com.project.bank.controller;

import com.project.bank.dto.AccoutDTO;
import com.project.bank.dto.HistoryDTO;
import com.project.bank.dto.LoanHistoryDTO;
import com.project.bank.dto.LoanProductDTO;
import com.project.bank.entity.Account;
import com.project.bank.repository.AccountRepository;
import com.project.bank.repository.LoanProductRepository;
import com.project.bank.repository.MemberRepository;
import com.project.bank.service.Bank.AccountService;
import com.project.bank.service.History.HistoryService;
import com.project.bank.service.Loan.LoanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

@Controller
@RequestMapping("/loan")
@RequiredArgsConstructor
@Log4j2
public class LoanBankController {

    private final LoanService loanService;
    private final LoanProductRepository loanProductRepository;
    private final AccountService accountService;
    private final HistoryService historyService;
    private final MemberRepository memberRepository;



    @GetMapping(value = "/loanFind/{productNo}")
    public String getLoanBank(@PathVariable("productNo")Long productNo, Model model, Principal principal){

        if(principal == null){
            model.addAttribute("msg","로그인후 이용가능합니다.");

            return "Member/login";
        }

      LoanProductDTO loanProductDTO =  loanService.findLoan(productNo);

        model.addAttribute("dto",loanProductDTO);
        log.info(loanProductDTO);

        return "loan/loanBank";
    }

    @ResponseBody   // 계좌 생성 본인 인증
    @RequestMapping(value = "/product",method = {RequestMethod.POST}, produces="application/json;charset=UTF-8")
    public ResponseEntity loanProduct(@RequestBody HashMap<String,Object> map, Principal principal, Model model){
        log.info("------------------");
        log.info(principal.getName());
        log.info(map.get("productNo"));
        log.info("------------------");

        String productNoId = (String) map.get("productNo");
        String bankId = (String) map.get("bankNo");
        Long productNo =(Long.parseLong(productNoId));
        Long bankNo =(Long.parseLong(bankId));

        LoanProductDTO loanProductDTO = loanService.findLoan(productNo);
        log.info(loanProductDTO);
        AccoutDTO accoutDTO =  accountService.selectAccountMember(principal.getName());
        log.info(accoutDTO);

        LoanHistoryDTO loanHistoryDTO = LoanHistoryDTO.builder()
                .borrowed(loanProductDTO.getLoanMoney())  // 대출 금액
                .productName(loanProductDTO.getProductName())  // 대출 상품 이름
                .interest(loanProductDTO.getInterest())         // 이자율
                .loanDate(LocalDateTime.now())                   // 대출 일자
                .loanMoney("0")                                  // 갚은 금액
                .build();

       Long loanHistoryId = loanService.loanHistoryupdeate(loanHistoryDTO,accoutDTO.getAccountNumber(),productNo);

        int balance = (Integer.parseInt(loanProductDTO.getLoanMoney()));

        HistoryDTO historyDTO = HistoryDTO.builder()
                .balance(balance)
                .chk("입금")
                .memberName(loanProductDTO.getBank().getBankName())
                .build();


        accountService.loanAccount(historyDTO,accoutDTO.getAccountNumber());


        return  new ResponseEntity<String>("거래성공", HttpStatus.OK);
    }


    @GetMapping(value = {"/loanList","/loanList/{page}"})
    public String loanHistory(@PathVariable("page") Optional<Integer> page,Principal principal,Model model){   // 대출 내역 보기

        if(principal == null){

            model.addAttribute("msg","로그인후 이용가능합니다.");
            return "Member/login";
        }

        Pageable pageable = PageRequest.of(page.isPresent()?page.get():0, 10);
         AccoutDTO accoutDTO = accountService.selectAccountMember(principal.getName());



        Page<LoanHistoryDTO> loanHistoryList = loanService.loanHistoryList(accoutDTO.getAccountNumber(),pageable);
        log.info(loanHistoryList.toString());

        model.addAttribute("loanHistoryList",loanHistoryList);
        model.addAttribute("account",accoutDTO);
        model.addAttribute("maxPage", 10);// 한줄에 보여질 페이지 번호 개수
        model.addAttribute("page", pageable.getPageNumber());// 총 페이지 수



        return "loan/loanhistory";
    }


}
