package ua.se.sample.easynotes;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import ua.se.sample.easynotes.dto.enums.IpsRole;
import ua.se.sample.easynotes.dto.enums.ProcessingStatus;
import ua.se.sample.easynotes.entity.SummaryEntity;
import ua.se.sample.easynotes.entity.SummaryId;
import ua.se.sample.easynotes.repository.NoteRepository;
import ua.se.sample.easynotes.repository.SummaryEntityRepository;
import ua.se.sample.easynotes.repository.SummaryRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@EnableJpaAuditing
@SpringBootApplication
public class EasyNotesApplication  implements CommandLineRunner {

    @Autowired
    private SummaryRepository summaryRepository;

    public static void main(String[] args) {
        SpringApplication.run(EasyNotesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        SummaryId summaryId = new SummaryId();
        summaryId.setIpsRole(IpsRole.manager);
        summaryId.setCreationDateTime(LocalDate.now());

        SummaryEntity summary = new SummaryEntity();
        summary.setIpsRole(summaryId.getIpsRole());
        summary.setCreationDate(summaryId.getCreationDateTime());

        summary.setComment("comment: " + LocalDateTime.now());
        summary.setStatus(ProcessingStatus.fail);

      //  summaryRepository.save(summary);

        Pageable pageable = PageRequest.of(0, 4);
        // диапазон поиска на дату
        LocalDate dateFrom = LocalDate.now().plusDays(-1L);
        LocalDate dateTo = LocalDate.now().plusDays(1L);

        Page<SummaryEntity> paged = summaryRepository.findByCreationDateBetween(dateFrom, dateTo, pageable);
        paged.getContent().forEach(System.out::println);

    }
}
