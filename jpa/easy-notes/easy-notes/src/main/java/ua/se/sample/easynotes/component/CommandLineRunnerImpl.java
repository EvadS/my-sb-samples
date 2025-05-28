package ua.se.sample.easynotes.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ua.se.sample.easynotes.entity.DateTimeItem;
import ua.se.sample.easynotes.exception.ResourceNotFoundException;
import ua.se.sample.easynotes.repository.DateTimeRepository;

import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;


@Component
@Order(1)
public class CommandLineRunnerImpl implements CommandLineRunner {

    @Autowired
    private DateTimeRepository dateTimeRepository;

    @Override
    public void run(String... args) throws Exception {

        //Long  id = storeTestData();
       // System.out.println("-------------------------------------");
       // printTestData(id);
        //System.out.println("-------------------------------------");
       /// deleteTestData(id);
    }


    private void deleteTestData(Long id) {
        DateTimeItem dateTimeItem = dateTimeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("date time", "id", id));

        dateTimeRepository.delete(dateTimeItem);
    }

    private void printTestData(Long id) {
        DateTimeItem dateTimeItem = dateTimeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("date time", "id", id));

        System.out.println(dateTimeItem);

    }

    private Long storeTestData() {

        DateTimeItem e = new DateTimeItem();

        e.setLocalDate(LocalDate.of(2019, 7, 19));
        e.setLocalDateTime(LocalDateTime.of(2019, 7, 19, 15, 5, 30));
        // null
        e.setOffsetTime(OffsetTime.of(15, 5, 30, 0, ZoneOffset.ofHours(+2)));

        // Hibernate-specific - not supported by JPA
        e.setDuration(Duration.ofHours(2));
        e.setInstant(Instant.now());
        e.setZonedDateTime(ZonedDateTime.of(2019, 7, 18, 15, 5, 30, 0, ZoneId.of("UTC-4")));

        e.setZonedDateTimeNative(ZonedDateTime.of(2019, 7, 18, 15, 5, 30, 0, ZoneId.of("UTC-4")));

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2015);
        calendar.set(Calendar.MONTH,  Calendar.JULY);
        calendar.set(Calendar.DATE, 28);
        Date date = calendar.getTime();

        e.setUtilDate(date);
        e.setUtilCalendar(new GregorianCalendar(2019, 6, 18));

        dateTimeRepository.save(e);

        return e.getId();
    }
}
