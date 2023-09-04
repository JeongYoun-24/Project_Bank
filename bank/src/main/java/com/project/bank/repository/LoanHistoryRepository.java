package com.project.bank.repository;

import com.project.bank.entity.LoanHistory;
import com.project.bank.entity.LoanProduct;
import com.project.bank.entity.QLoanProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanHistoryRepository extends JpaRepository<LoanHistory,Long> {






}
