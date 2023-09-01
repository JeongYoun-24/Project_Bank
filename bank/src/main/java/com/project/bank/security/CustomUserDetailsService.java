package com.project.bank.security;

import com.project.bank.entity.Member;
import com.project.bank.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
//@Transactional
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {


    private final MemberRepository memberRepository;



    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
//        Users users = usersRepository.findByEmail(Email);
        Member member = memberRepository.findByMemberId(memberId);
        log.info(member);
        if(member == null){  //회원 정보가 없다
            throw new UsernameNotFoundException(memberId);
        }

        //db로 회원 정보
///        return User.builder()
 //               .username(member.getName())
 //               .password(member.getPassword())
//                .roles(member.getRole().toString())
//                .authorities("ROLE"+member.getRole().toString())
 //               .build();

        List<GrantedAuthority> authorities = new ArrayList<>();
        //authorities.add(new SimpleGrantedAuthority(member.getRole().toString()));

        return new CustomUsers(member, authorities);

    }








}
