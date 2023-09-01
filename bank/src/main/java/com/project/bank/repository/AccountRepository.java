package com.project.bank.repository;

import com.project.bank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {


    Account findByMember_MemberId(String memberId);

//    int findByPassword(String password);

}
