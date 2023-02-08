package org.example.service;

import org.example.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    String saveProject(Project project);
    String saveAllProject(List<Project> projects);
    List<Project> getAllProjects();
    Optional<Project> findProjectById(Long id);
    Optional<Project> deleteProjectById(Long id);
    String deleteAllProjects();
    Optional<Project> updateProject(Long id, Project newProject);
    String assignProgrammersToProject(Long projectId, Long programmerId);
    Optional<Project> getExpensiveProject();
    String getProjectWrittenInAShortTime();
}
