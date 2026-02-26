package com.se.sample.service;

import com.se.sample.model.DocumentHistory;
import com.se.sample.repository.DocumentHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final DocumentHistoryRepository documentHistoryRepository;
    //    @Value("${history.save.interval.month}")
    private final int periodInMonth = 1;

    public List<DocumentHistory> getAll() {
        return documentHistoryRepository.findAll();
    }

    @Retryable(backoff = @Backoff(delay = 1, maxDelay = 100, random = true))
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public DocumentHistory insert(String user, String document, String publisher) {
        DocumentHistory historyItem = new DocumentHistory();
        historyItem.setUserEmail(user);
        historyItem.setDocumentId(document);
        historyItem.setPublisher(publisher);

        return documentHistoryRepository.save(historyItem);
    }
}
