package ua.se.sample.easynotes.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.se.sample.easynotes.entity.Note;
import ua.se.sample.easynotes.entity.SummaryEntity;
import ua.se.sample.easynotes.entity.SummaryId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SummaryRepository extends JpaRepository<SummaryEntity, SummaryId> {
    Page<SummaryEntity> findByCreationDateBetween(LocalDate from, LocalDate to, Pageable pageable);


}
