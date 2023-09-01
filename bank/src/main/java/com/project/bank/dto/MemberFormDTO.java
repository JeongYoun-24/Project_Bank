package com.project.bank.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberFormDTO {

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String memberId; // 아이디
    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min=4, max=16, message = "비밀번호는 4자 이상, 16자 이하로 입력해주세요")
    private String password;     // 비밀번호
    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;     // 이메일
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;        // 이름
    private String phone1;       // 전화번호 첫줄
    private String phone2;       // 전화번호 두줄
    private String phone3;       // 전화번호 세줄
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String age;         // 생년월일
    @NotEmpty(message = "주소는 필수 입력 값입니다.")
    private String address;     // 주소
    private String gender;      // 성별
    private LocalDateTime regDate;  // 등록일자

    //    @Enumerated(EnumType.STRING)
  //  private String level;       // 레벨






}
