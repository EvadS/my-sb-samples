package pl.piomin.sample.spring.graphql.resolver;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import pl.piomin.sample.spring.graphql.LanguageRequest;
import pl.piomin.sample.spring.graphql.ResourceNotFoundException;
import pl.piomin.sample.spring.graphql.domain.Employee;
import pl.piomin.sample.spring.graphql.domain.LanguageEntity;
import pl.piomin.sample.spring.graphql.domain.Organization;
import pl.piomin.sample.spring.graphql.repository.LanguageRepository;

@Controller
public class LanguageController {
    LanguageRepository repository;

    LanguageController(LanguageRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public Iterable<LanguageEntity> languages() {
        return repository.findAll();
    }

    @QueryMapping
    public LanguageEntity language(@Argument Long id) {
        return repository.findById(id).orElseThrow();
    }

    @MutationMapping
    public LanguageEntity newLanguage(@Argument LanguageRequest languageRequest) {
        LanguageEntity languageEntity = new LanguageEntity();
        languageEntity.setLanguageName(languageEntity.getLanguageName());
        languageEntity.setLanguageCode(languageEntity.getLanguageCode());

        return repository.save(languageEntity);
    }

    @MutationMapping
    public LanguageEntity update(@Argument Long id,  @Argument LanguageRequest languageRequest) {
        LanguageEntity languageEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("land", "id", id));

        languageEntity.setLanguageName(languageRequest.getName());
        languageEntity.setLanguageCode(languageRequest.getCode());

        languageEntity = repository.save(languageEntity);

        return  languageEntity;
    }

}
