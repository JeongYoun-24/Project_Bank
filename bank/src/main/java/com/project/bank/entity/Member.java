package com.project.bank.entity;


import com.project.bank.dto.MemberDTO;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Table(name="member")
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @Column(name="member_id")
    private String memberId; // 아이디
    private String password;     // 비밀번호
    private String email;     // 이메일
    private String name;      // 이름
    private String phone;       // 전화번호
    private String age;         // 생년월일
    private String address;     // 주소
    private String gender;      // 성별
    private LocalDateTime regDate;  // 등록일자

//    @Enumerated(EnumType.STRING)
    private String level;       // 레벨


    public static Member createMember(MemberDTO memberDTO, PasswordEncoder passwordEncoder){
        Member member = new Member();

        member.setName(memberDTO.getName());
        member.setEmail(memberDTO.getEmail());
        member.setMemberId(memberDTO.getMemberId());
        member.setPhone(memberDTO.getPhone());
        member.setGender(memberDTO.getGender());
        member.setAddress(memberDTO.getAddress());
        member.setLevel(memberDTO.getLevel());
        member.setAge(memberDTO.getAge());

        // 암호화
        String password = passwordEncoder.encode(memberDTO.getPassword());
        member.setPassword(password);
//        member.setRole(Role.USER);
//        users.setRole(Role.ADMIN);
//        users.setRole(Role.MANAGER);

        return member;
    }
}
