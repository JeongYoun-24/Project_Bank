package com.project.bank.Repository;

import com.project.bank.BankApplication;
import com.project.bank.dto.MemberDTO;
import com.project.bank.entity.Member;
import com.project.bank.repository.MemberRepository;
import com.project.bank.service.Member.LoginService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Log4j2
@AutoConfigureMockMvc
@SpringBootTest(classes = BankApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    LoginService loginService;

    @Autowired
    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private MockMvc mockMvc;




    public Member MemberJoin(){
        MemberDTO memberDTO = MemberDTO.builder()
                .memberId("aaa")
                .password("1234")
                .email("aaa@naver.com")
                .name("알파")
                .phone("010-1234-2343")
                .address("양산시")
                .gender("남")
                .age("24")
                .level("5등급")
                .build();




     return  Member.createMember(memberDTO, passwordEncoder);
    }


    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest(){

        Member member = MemberJoin();
        member.setRegDate(LocalDateTime.now());
        log.info(member);


      Member member1 =  memberRepository.save(member);

        log.info(member1);

        Member savedMember =  loginService.saveMember(member);

        // assertEquals메소드를 이용하여 저장하려고 요청했던 값과 실제 저장된 데이를 비교
        // assertEquals(기대값, 실제값);
        assertEquals(member.getEmail(), savedMember.getEmail());
        assertEquals(member.getName(), savedMember.getName());
        assertEquals(member.getMemberId(), savedMember.getMemberId());
        assertEquals(member.getPassword(), savedMember.getPassword());
//        assertEquals(member.getRole(), savedMember.getRole());

    }

}
