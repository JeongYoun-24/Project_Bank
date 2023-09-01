package com.project.bank.repository;

import com.project.bank.entity.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History,Long>, QuerydslPredicateExecutor<History> {


    History findByAccount_AccountNumber(String accountNumber);

//    @Query(value = "select o from history o where o.account.accountNumber =:accountNumber order by o.updateDate desc")
//    List<History> histryList(@Param("accountNumber") String accountNumber);

    List<History> findByAccount_AccountNumberOrderByUpdateDateDesc(String accountNumber,Pageable pageable);

    @Query(value = "select count(o) from History o where o.account.accountNumber = :accountNumber")
    Long countHistory(@Param("accountNumber") String accountNumber);
}
