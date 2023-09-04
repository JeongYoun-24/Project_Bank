package com.project.bank.repository;

import com.project.bank.entity.LoanHistory;
import com.project.bank.entity.LoanProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanProductRepository extends JpaRepository<LoanProduct,Long> {


    List<LoanProduct> findByBank_BankNo(Long bankNo);

}
