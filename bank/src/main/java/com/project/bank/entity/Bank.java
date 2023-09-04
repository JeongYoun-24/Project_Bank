package com.project.bank.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Table(name="bank")
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bank {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="bank_no")
    private Long bankNo;

    private String bankName;




}
