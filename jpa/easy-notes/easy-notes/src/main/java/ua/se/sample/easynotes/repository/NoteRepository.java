package ua.se.sample.easynotes.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.se.sample.easynotes.entity.Note;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query(value = "from Note t where createdAt BETWEEN :startDate AND :endDate")
    List<Note> getAllBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate")LocalDateTime endDate);

    Page<Note> findByCreatedAtBetween(LocalDateTime publicationTimeStart,
                                      LocalDateTime  publicationTimeEnd,
                                      Pageable pageable);
}
