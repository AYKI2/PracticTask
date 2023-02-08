package org.example.service;

import org.example.model.Project;

import java.util.List;
import java.util.Optional;

public class ProjectServiceImpl implements ProjectService {
    @Override
    public String saveProject(Project project) {
        return null;
    }

    @Override
    public String saveAllProject(List<Project> projects) {
        return null;
    }

    @Override
    public List<Project> getAllProjects() {
        return null;
    }

    @Override
    public Optional<Project> findProjectById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Project> deleteProjectById(Long id) {
        return Optional.empty();
    }

    @Override
    public String deleteAllProjects() {
        return null;
    }

    @Override
    public Optional<Project> updateProject(Long id, Project newProject) {
        return Optional.empty();
    }

    @Override
    public String assignProgrammersToProject(Long projectId, Long programmerId) {
        return null;
    }

    @Override
    public Optional<Project> getExpensiveProject() {
        return Optional.empty();
    }

    @Override
    public String getProjectWrittenInAShortTime() {
        return null;
    }
}
