package pl.piomin.sample.spring.graphql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import pl.piomin.sample.spring.graphql.domain.LanguageEntity;
import pl.piomin.sample.spring.graphql.domain.Organization;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureGraphQlTester
public class LanguageControllerTests {

    @Autowired
    private GraphQlTester tester;

    @Test
    void addLanguage() {
        String query = "mutation { newLanguage(languageRequest: { name: \"Test10\" ,code: \"Test code\" } ) { id } }";
        LanguageEntity language = tester.document(query)
                .execute()
                .path("data.newLanguage")
                .entity(LanguageEntity.class)
                .get();
        Assertions.assertNotNull(language);
        Assertions.assertNotNull(language.getId());
    }

    @Test
    void updateLanguage() {
        String query = "mutation { update( id: 1,languageRequest: { name: \"Test10\" ,code: \"Test code\" } ) { id , languageCode, languageName} }";
        LanguageEntity language = tester.document(query)
                .execute()
                .path("data.update")
                .entity(LanguageEntity.class)
                .get();
        Assertions.assertNotNull(language);
        Assertions.assertNotNull(language.getId());
        Assertions.assertEquals("Test code", language.getLanguageCode());
        Assertions.assertEquals("Test10", language.getLanguageName());
    }


    @Test
    void findAll() {
        String query = "{ languages { id languageCode languageName } }";
        List<LanguageEntity> languages = tester.document(query)
                .execute()
                .path("data.languages[*]")
                .entityList(LanguageEntity.class)
                .get();
        Assertions.assertTrue(languages.size() > 0);
        Assertions.assertNotNull(languages.get(0).getId());
        Assertions.assertNotNull(languages.get(0).getLanguageName());
    }

    @Test
    void findById() {
        //        String query = "{ department(id: 1) { id name organization { id } } }";
        //   String query = "{ employee(id: 1) { id firstName lastName salary } }";
        //
        String query = "{ language(id: 1) { id languageCode languageName } }";
        LanguageEntity languages = tester.document(query)
                .execute()
                .path("data.language")
                .entity(LanguageEntity.class)
                .get();
        Assertions.assertNotNull(languages);
        Assertions.assertNotNull(languages.getId());
    }
}
