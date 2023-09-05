package com.project.bank.repository;

import com.project.bank.dto.LoanHistoryDTO;
import com.project.bank.entity.History;
import com.project.bank.entity.LoanHistory;
import com.project.bank.entity.LoanProduct;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoanHistoryRepository extends JpaRepository<LoanHistory,Long>{

    @Query(value = "select o from LoanHistory o where o.account.accountNumber = :accountNumber order by o.loanDate desc")
    List<LoanHistory> LoanHistoryList(@Param("accountNumber") String accountNumber, Pageable pageable);
//    @Query("select o from Order2 o where o.users.name = :name order by  o.orderStatus, o.orderDate desc")

    @Query(value = "select count(o) from LoanHistory o where o.account.accountNumber = :accountNumber")
    Long countLoanHistory(@Param("accountNumber") String accountNumber);

}
