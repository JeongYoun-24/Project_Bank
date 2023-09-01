package com.project.bank.controller;

import com.project.bank.dto.AccoutDTO;
import com.project.bank.entity.Account;
import com.project.bank.entity.History;
import com.project.bank.repository.AccountRepository;
import com.project.bank.repository.HistoryRepository;
import com.project.bank.repository.MemberRepository;
import com.project.bank.service.Bank.AccountService;
import com.project.bank.service.History.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/history")
@RequiredArgsConstructor
@Log4j2
public class HistoryController {
    private final MemberRepository memberRepository;
    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final HistoryRepository historyRepository;
    private final HistoryService historyService;

    @GetMapping(value = {"/list/{accountNumber}","/list/{accountNumber}/{page}","/list/{accountNumber}/{page}/{size}"})
    public String getHisttory(@PathVariable("accountNumber")String accountNumber,@PathVariable("page") Optional<Integer> page, Model model,String size ,Principal principal){

        if(size !=null){
            log.info(size);
            int size2 = (Integer.parseInt(size));
            log.info(size2);
            Pageable pageable = PageRequest.of(page.isPresent()?page.get():0, size2);

            Page<History> histories=historyService.historyList(accountNumber,pageable);
//        List<History> histories = historyRepository.findByAccount_AccountNumberOrderByUpdateDateDesc(accountNumber,pageable);

            AccoutDTO account =  accountService.readOne(accountNumber);

            log.info(histories);

            model.addAttribute("historyList",histories);
            model.addAttribute("account",account);
            model.addAttribute("maxPage", 10);// 한줄에 보여질 페이지 번호 개수
            model.addAttribute("page", pageable.getPageNumber());// 총 페이지 수
            return "history/history";
        }



        Pageable pageable = PageRequest.of(page.isPresent()?page.get():0, 20);

        Page<History> histories=historyService.historyList(accountNumber,pageable);
//        List<History> histories = historyRepository.findByAccount_AccountNumberOrderByUpdateDateDesc(accountNumber,pageable);

        AccoutDTO account =  accountService.readOne(accountNumber);

        log.info(histories);

        model.addAttribute("historyList",histories);
        model.addAttribute("account",account);
        model.addAttribute("maxPage", 10);// 한줄에 보여질 페이지 번호 개수
        model.addAttribute("page", pageable.getPageNumber());// 총 페이지 수

        return "history/history";
    }


    @PostMapping(value = "/history")
    public String PostHisttory(){

        return "history/history";
    }

}
