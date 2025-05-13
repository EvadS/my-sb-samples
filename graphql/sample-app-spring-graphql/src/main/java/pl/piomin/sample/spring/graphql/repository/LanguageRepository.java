package pl.piomin.sample.spring.graphql.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import pl.piomin.sample.spring.graphql.domain.Department;
import pl.piomin.sample.spring.graphql.domain.LanguageEntity;


public interface LanguageRepository extends CrudRepository<LanguageEntity, Long>,
        JpaSpecificationExecutor<Department> {

}