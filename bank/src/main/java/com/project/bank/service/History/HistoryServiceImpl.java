package com.project.bank.service.History;

import com.project.bank.entity.History;
import com.project.bank.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;
    @Override
    public Page<History> historyList(String accountNumber, Pageable pageable) {

        List<History> histories = historyRepository.findByAccount_AccountNumberOrderByUpdateDateDesc(accountNumber,pageable);
       Long totalCount = historyRepository.countHistory(accountNumber);
//        Long totalCount = 10L;

     return  new PageImpl<>(histories, pageable, totalCount);
    }



}
