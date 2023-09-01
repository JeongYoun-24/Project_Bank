package com.project.bank.controller;

import com.project.bank.dto.MemberDTO;
import com.project.bank.dto.MemberFormDTO;
import com.project.bank.entity.Member;
import com.project.bank.service.Member.LoginService;
import com.project.bank.service.Member.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
@Log4j2
public class LoginController {

    private final MemberServiceImpl memberService;
    private final PasswordEncoder passwordEncoder;
    private final LoginService loginService;



    @GetMapping(value="/register")
    public String memberForm(Model model){
        model.addAttribute("memberFormDTO", new MemberFormDTO());
        return "Member/register";
    }

    @PostMapping(value="/register")
    public String saveMember(@Valid MemberFormDTO memberFormDTO, BindingResult bindingResult, Model model){

        String phone = memberFormDTO.getPhone1()+"-"+memberFormDTO.getPhone2()+"-"+memberFormDTO.getPhone3();

        MemberDTO memberDTO = MemberDTO.builder()
                .memberId(memberFormDTO.getMemberId())
                .password(memberFormDTO.getPassword())
                .name(memberFormDTO.getName())
                .email(memberFormDTO.getEmail())
                .phone(phone)
                .age(memberFormDTO.getAge())
                .gender(memberFormDTO.getGender())
                .address(memberFormDTO.getAddress())
                .build();


        log.info("--- member post");
        log.info("DTO값"+memberDTO);


        if (bindingResult.hasErrors()){
            return "Member/register";
        }

        try {
            Member member = Member.createMember(memberDTO, passwordEncoder);
            member.setRegDate(LocalDateTime.now());
            member.setLevel("5등급");
            
            loginService.saveMember(member);
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "Member/register";
        }


        return "redirect:/members/login";
    }



    @GetMapping(value="/login")
    public String loginGET(String error, String logout){


        return "Member/login";
    }

    @GetMapping(value="/login/error")
    public String loginError(Model model){
        log.info("---- login/error ----");

        model.addAttribute("loginErrorMsg","아이디 또는 비밀번호를 확인해주세요");
        return "Member/login";
    }
    @GetMapping("/logout")
    public String logoutGet(){

        return "redirect :/members/login?logout";
    }

}
