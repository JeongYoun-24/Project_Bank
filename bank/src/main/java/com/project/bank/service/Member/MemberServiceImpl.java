package com.project.bank.service.Member;

import com.project.bank.dto.MemberDTO;
import com.project.bank.entity.Member;
import com.project.bank.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberServeice {


    private final ModelMapper modelMapper;

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public String register(MemberDTO memberDTOm) {
        Member member = modelMapper.map(memberDTOm,Member.class);
        String memberId = memberRepository.save(member).getMemberId();

        return memberId;
    }

    @Override
    public MemberDTO readOne(String memberId) {
        return null;
    }

    @Override
    public int modify(MemberDTO memberDTOm) {
        return 0;
    }

    @Override
    public int remove(String memberId) {
        return 0;
    }










}
