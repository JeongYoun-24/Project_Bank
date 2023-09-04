package com.project.bank.repository;

import com.project.bank.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank,Long> {

    Bank findByBankName(String bankName);

}
