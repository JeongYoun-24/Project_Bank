package com.project.bank.controller;

import com.project.bank.dto.AccoutDTO;
import com.project.bank.dto.MemberDTO;
import com.project.bank.entity.Account;
import com.project.bank.entity.History;
import com.project.bank.entity.Member;
import com.project.bank.repository.AccountRepository;
import com.project.bank.repository.MemberRepository;
import com.project.bank.service.Bank.AccountService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.security.Principal;
import java.util.HashMap;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
@Log4j2
public class AccountController {

    private final MemberRepository memberRepository;
    private final AccountService accountService;
    private final AccountRepository accountRepository;



    @GetMapping(value = "/register") // 계좌생성 페이지 이동
    public String getaccountRegister(Principal principal, Model model){
        if(principal == null){
            model.addAttribute("msg","로그인후 이용가능합니다.");
            return "redirect:members/login";

        }


        return "account/account";
    }
    @ResponseBody   // 계좌 생성 본인 인증
    @RequestMapping(value = "/email",method = {RequestMethod.POST}, produces="application/json;charset=UTF-8")
    public ResponseEntity postaccountRegister(@RequestBody HashMap<String,Object> map, Principal principal, Model model){
        log.info("POST 요청");
        MemberDTO memberDTO =new MemberDTO();


        String email = (String) map.get("email");
        String memberId = (String) map.get("memberId");

        memberDTO.setEmail(email);

        Member member = memberRepository.findByMemberId(memberId);

        log.info(member.toString());

        log.info(member.getEmail());
        log.info(memberDTO.getEmail());
        if(member.getEmail() != email){
            log.info("인증 실패");
            return new ResponseEntity<String>("인증실패",HttpStatus.BAD_REQUEST);
        }



        return  new ResponseEntity<String>("인증완료",HttpStatus.OK);
    }


    @ResponseBody     // 계좌 및 계좌 내역 생성
    @RequestMapping(value = "/register",method = {RequestMethod.POST}, produces="application/json;charset=UTF-8")
    public ResponseEntity postaccountRegister2(@RequestBody HashMap<String,Object> map, Principal principal, Model model){

        String memberId = (String) map.get("memberId");
        String password = (String) map.get("password");

        Member member = memberRepository.findByMemberId(memberId);

        AccoutDTO accoutDTO = AccoutDTO.builder()
                .member(member)
                .password(password)
                .build();

        accountService.register(accoutDTO);



        return  new ResponseEntity<String>("생성완료",HttpStatus.OK);
    }



    @GetMapping(value = "/find")   // 계좌 조회
    public String getaccountFind(Principal principal, Model model){
        if(principal == null){
            model.addAttribute("msg","로그인후 이용가능합니다.");
            return "redirect:user/login";
        }

        Member member= memberRepository.findByMemberId(principal.getName());

        Account account = accountRepository.findByMember_MemberId(member.getMemberId());

       AccoutDTO accoutDTO = accountService.readOne(account.getAccountNumber());

        log.info(accoutDTO);

        model.addAttribute("accountDTO",accoutDTO);


        return "account/find";
    }


    @ResponseBody     // 계좌 및 계좌 내역 생성
    @RequestMapping(value = "/pwd",method = {RequestMethod.POST}, produces="application/json;charset=UTF-8")
    public ResponseEntity postPassword(@RequestBody HashMap<String,Object> map, Model model){
        log.info("비밀번호 확인 요청");

        String password = (String) map.get("password");
        String accountNumber = (String) map.get("accountNumber");
        log.info("비밀번호 확인 요청 비번"+password);
        log.info("비밀번호 확인 요청 계좌"+accountNumber);

        int result =  accountService.pwd(password,accountNumber);
        log.info(result);
        if(result== 0){

            return  new ResponseEntity<Integer>(result,HttpStatus.BAD_REQUEST);
        }


        return  new ResponseEntity<Integer>(result,HttpStatus.OK);
    }

    @ResponseBody     // 계좌 입금
    @RequestMapping(value = "/balance",method = {RequestMethod.POST}, produces="application/json;charset=UTF-8")
    public ResponseEntity postbalance(@RequestBody HashMap<String,Object> map, Model model){
        log.info("계좌 입금 요청");

        String test = (String) map.get("balance");
        String name = (String) map.get("name");
        String accountNumber = (String) map.get("accountNumber");

        log.info("금액"+test);
        log.info("보낸사람"+name);
        log.info("계좌번호"+accountNumber);

        int balance = (Integer.parseInt(test));

        log.info("금액"+balance);

        AccoutDTO accoutDTO = AccoutDTO.builder()
                .accountNumber(accountNumber)
                .balance(balance)
                .build();

        int result =0;
        try {
            result = accountService.modify(accoutDTO,name);
            log.info("입금성공");
            log.info(result);
        }catch (Exception e){
            log.info("입금실패");
            return  new ResponseEntity<String>("입금실패",HttpStatus.BAD_REQUEST);
        }



        return  new ResponseEntity<Integer>(result,HttpStatus.OK);
    }

    @ResponseBody     // 계좌 이체
    @RequestMapping(value = "/transfer",method = {RequestMethod.POST}, produces="application/json;charset=UTF-8")
    public ResponseEntity MemberAccount(@RequestBody HashMap<String,Object> map, Model model){
        log.info("계좌 입금 요청");

        String test = (String) map.get("balance");                  // 보낸 금액
        String name = (String) map.get("name");                    // 보낸사람 이름
        String accountNumber = (String) map.get("accountNumber"); // 받는사람 계좌
        String accountNumberId = (String) map.get("accountNumberId"); // 보낸 사람 계좌

        log.info("금액"+test);
        log.info("보낸사람"+name);
        log.info("계좌번호"+accountNumber);

        int balance = (Integer.parseInt(test));

        log.info("금액"+balance);

        AccoutDTO accoutDTO = AccoutDTO.builder()
                .accountNumber(accountNumberId)
                .balance(balance)
                .build();


        Account account = accountRepository.findById(accoutDTO.getAccountNumber()).orElseThrow(EntityExistsException::new);

        log.info("금액 들어있는금액 "+account.getBalance());

        int price = accoutDTO.getBalance() ;
        int price2 = account.getBalance();



        log.info("금액 들어있는금액 "+price);
        log.info("금액 들어있는금액 "+price2);

        int price3 = price2 - price;

        log.info("금액 들어있는금액3 "+price3);

        int price4 = price3+0000;
        log.info("금액 들어있는금액4 "+price4);

        int result =0;
        try {
           result = accountService.transfer(accoutDTO,name,accountNumber);
            log.info("입금성공");
            log.info(result);
        }catch (Exception e){
            log.info("입금실패");
            return  new ResponseEntity<String>("입금실패",HttpStatus.BAD_REQUEST);
        }






        return  new ResponseEntity<Integer>(result,HttpStatus.OK);
    }





}
