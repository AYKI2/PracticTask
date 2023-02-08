package org.example.repository;

import org.example.model.Programmer;
import org.example.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    void saveProject(Project project);
    void saveAllProject(List<Project> projects);
    List<Project> getAllProjects();
    Optional<Project> findProjectById(Long id);
    Optional<Project> deleteProjectById(Long id);
    void deleteAllProjects();
    Optional<Project> updateProject(Long id, Project newProject);
    void assignProgrammersToProject(Long projectId, Long programmerId);
    Optional<Project> getExpensiveProject();
    String getProjectWrittenInAShortTime();
}
