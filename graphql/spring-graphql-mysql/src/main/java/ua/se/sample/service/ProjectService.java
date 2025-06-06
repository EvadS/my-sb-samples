package ua.se.sample.service;

import ua.se.sample.entity.Language;
import ua.se.sample.entity.Project;
import ua.se.sample.entity.User;
import ua.se.sample.exception.ProjectException;
import ua.se.sample.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private LanguageService languageService;
    @Autowired
    private UserService userService;

    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public Project getById(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (!project.isPresent()) {
            throw new ProjectException(404, "Project with id " + id + " cannot be found");
        }
        return project.get();
    }

    public Project create(String title, String description, Long languageId, Long userId) {
        Language language = languageService.getById(languageId);
        User user = userService.getById(userId);
        Project project = Project.create(title, description, language, user);
        return projectRepository.save(project);
    }

    public Project update(Long id, String title, String description, Long languageId, Long userId) {
        Project project = getById(id);
        Language language = null;
        User user = null;

        if (languageId != null)
            language = languageService.getById(languageId);
        if (userId != null)
            user = userService.getById(userId);

        project.updateFields(title, description, language, user);
        return projectRepository.save(project);
    }

    public boolean delete(Long id) {
        Project project = getById(id);
        projectRepository.delete(project);
        return true;
    }

    public List<Project> getUserProjects(Long userId) {
        return projectRepository.findUserProjects(userId);
    }
}
