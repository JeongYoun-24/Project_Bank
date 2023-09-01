package com.project.bank.service.Member;

import com.project.bank.dto.MemberDTO;

public interface MemberServeice {


    public String register(MemberDTO memberDTOm);
    public MemberDTO readOne(String memberId);
    public int modify(MemberDTO memberDTOm);
    public int remove(String memberId);


}
