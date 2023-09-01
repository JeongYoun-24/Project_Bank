package com.project.bank.service.History;

import com.project.bank.entity.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HistoryService  {


    Page<History> historyList(String accountNumber, Pageable pageable);  // 계좌 내역 조회 페이징 처리



}
