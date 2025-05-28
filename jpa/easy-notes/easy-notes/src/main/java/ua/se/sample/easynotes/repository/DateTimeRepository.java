package ua.se.sample.easynotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.se.sample.easynotes.entity.DateTimeItem;

@Repository
public interface DateTimeRepository extends JpaRepository<DateTimeItem, Long> {

}
