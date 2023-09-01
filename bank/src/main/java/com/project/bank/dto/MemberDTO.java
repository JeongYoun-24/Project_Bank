package com.project.bank.dto;

import lombok.*;

import javax.persistence.Table;
import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {


    private String memberId; // 아이디
    private String password;     // 비밀번호
    private String email;     // 이메일
    private String name;        // 이름
    private String phone;       // 전화번호
    private String age;         // 생년월일
    private String address;     // 주소
    private String gender;      // 성별
    private LocalDateTime regDate;  // 등록일자

    //    @Enumerated(EnumType.STRING)
    private String level;       // 레벨






}
