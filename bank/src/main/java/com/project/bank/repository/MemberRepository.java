package com.project.bank.repository;

import com.project.bank.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository  extends JpaRepository<Member,String> {

    Member findByMemberId(String memberId);

    Member findByNameOrEmail(String name,String email); // 아이디 찾기
    Member findByMemberIdOrEmail(String memberId,String email); // 비밀번호 찾기

    Member findByEmail(String email);
    Member findByName(String name);


}
