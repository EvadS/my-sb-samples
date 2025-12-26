package ua.se.sample.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.se.sample.model.Institute;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstituteRepository extends MongoRepository<Institute, String> {
    //A normal spring data query method.
    Optional<List<Institute>> findByName(String name);
}