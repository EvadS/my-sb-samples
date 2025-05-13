package ua.se.sample.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import ua.se.sample.entity.Language;
import ua.se.sample.entity.Project;
import ua.se.sample.entity.User;
import ua.se.sample.service.LanguageService;
import ua.se.sample.service.ProjectService;
import ua.se.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    private LanguageService languageService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;

    // Language

    public Language createLanguage(String name, String code, String color, String languageType) {
        return languageService.create(name, code, color, languageType);
    }

    public Language updateLanguage(Long id, String name, String code, String color, String languageType) {
        return languageService.update(id, name, code, color, languageType);
    }

    public boolean deleteLanguage(Long id) {
        return languageService.delete(id);
    }

    // User

    public User createUser(String username, String email) {
        return userService.create(username, email);
    }

    public User updateUser(Long id, String username, String email) {
        return userService.update(id, username, email);
    }

    public boolean deleteUser(Long id) {
        return userService.delete(id);
    }

    // Language

    public Project createProject(String title, String description, Long language, Long user) {
        return projectService.create(title, description, language, user);
    }

    public Project updateProject(Long id, String title, String description, Long language, Long user) {
        return projectService.update(id, title, description, language, user);
    }

    public boolean deleteProject(Long id) {
        return projectService.delete(id);
    }
}
