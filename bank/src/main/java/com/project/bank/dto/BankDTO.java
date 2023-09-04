package com.project.bank.dto;

import com.project.bank.entity.Bank;
import com.project.bank.entity.LoanProduct;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.Table;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BankDTO {

    private Long bankNo;

    private String bankName;


    private static ModelMapper modelMapper = new ModelMapper();
    public static BankDTO of(Bank bank){
        // entity -> dto
        return modelMapper.map(bank, BankDTO.class);
    }

}
