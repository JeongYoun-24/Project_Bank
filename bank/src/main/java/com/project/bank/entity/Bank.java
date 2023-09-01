package com.project.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Table(name="bank")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bank {

    @Id
    @Column(name="bank_no")
    private Long bankNo;

    private String bankName;




}
