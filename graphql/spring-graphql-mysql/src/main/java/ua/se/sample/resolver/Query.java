package ua.se.sample.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import ua.se.sample.entity.Language;
import ua.se.sample.entity.Project;
import ua.se.sample.entity.User;
import ua.se.sample.service.LanguageService;
import ua.se.sample.service.ProjectService;
import ua.se.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private LanguageService languageService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;

    // Language

    public List<Language> allLanguages() {
        return languageService.getAll();
    }

    public Language language(Long id) {
        return languageService.getById(id);
    }

    public List<Language> languageType(String languageType) {
        return languageService.getByLanguageType(languageType);
    }

    // User

    public List<User> allUsers() {
        return userService.getAll();
    }

    public User user(Long id) {
        return userService.getById(id);
    }

    // Project

    public List<Project> allProjects() {
        return projectService.getAll();
    }

    public Project project(Long id) {
        return projectService.getById(id);
    }

    public List<Project> userProjects(Long userId) {
        return projectService.getUserProjects(userId);
    }
}
