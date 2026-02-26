package com.se.sample.repository;


import com.se.sample.model.DocumentHistory;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface DocumentHistoryRepository extends JpaRepository<DocumentHistory, Long> {

    Page<DocumentHistory> findDocumentHistoriesByUserEmail(String  email, Pageable pageable);
    Optional<DocumentHistory> findByDocumentIdAndUserEmail(String documentId, String userEmail);

    @Transactional
    @Modifying
    @Query("DELETE FROM DocumentHistory dh WHERE dh.userEmail = :userId AND dh.documentViewDate < :documentView")
    int deleteOldHistory(@Param("userId") String userEmail2, @Param("documentView")  Date documentView);
}


