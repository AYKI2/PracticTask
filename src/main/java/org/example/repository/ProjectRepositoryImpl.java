package org.example.repository;

import org.example.enums.Status;
import org.example.model.Countries;
import org.example.model.Programmer;
import org.example.model.Project;
import org.example.util.DataBaseConnection;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectRepositoryImpl implements ProjectRepository,AutoCloseable{
    private EntityManagerFactory entityManagerFactory = DataBaseConnection.getSession();
    @Override
    public void saveProject(Project project) {
        try {
            EntityManager en = entityManagerFactory.createEntityManager();
            en.getTransaction().begin();
            en.persist(project);
            en.getTransaction().commit();
            en.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveAllProject(List<Project> projects) {
        try{
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            for(Project c:projects){
                em.persist(c);
            }
            em.getTransaction().commit();
            em.close();
        }catch(HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        try {
            EntityManager en = entityManagerFactory.createEntityManager();
            en.getTransaction().begin();
            projects = en.createQuery("select p from Project p", Project.class).getResultList();
            en.getTransaction().commit();
            en.close();
            return projects;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return projects;
    }

    @Override
    public Optional<Project> findProjectById(Long id) {
        try {
            EntityManager en = entityManagerFactory.createEntityManager();
            en.getTransaction().begin();
            Project project = en.createQuery("select l from Project l where id=:id", Project.class)
                    .setParameter("id", id).getSingleResult();
            en.getTransaction().commit();
            en.close();
            return Optional.ofNullable(project);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Project> deleteProjectById(Long id) {
        try {
            EntityManager en = entityManagerFactory.createEntityManager();
            en.getTransaction().begin();
            Project result = en.createQuery("select l from Project l where id=:id", Project.class)
                    .setParameter("id", id).getSingleResult();
            en.remove(result);
            en.getTransaction().commit();
            en.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void deleteAllProjects() {
        try {
            EntityManager en = entityManagerFactory.createEntityManager();
            en.getTransaction().begin();
            en.createNativeQuery("truncate table  projects cascade").executeUpdate();
            en.getTransaction().commit();
            en.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Optional<Project> updateProject(Long id, Project newProject) {
        try {
            EntityManager en = entityManagerFactory.createEntityManager();
            en.getTransaction().begin();
            Project project = en.createQuery("select l from Project l where id=:id", Project.class)
                    .setParameter("id", id).getSingleResult();
            project.setProjectName(newProject.getProjectName());
            project.setPrice(newProject.getPrice());
            project.setDescription(newProject.getDescription());
            project.setDateOfFinish(newProject.getDateOfFinish());
            project.setDateOfStart(newProject.getDateOfStart());
            en.merge(project);
            en.getTransaction().commit();
            en.close();
            return Optional.of(project);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void assignProgrammersToProject(Long projectID, Long programmerID) {
        try {
            EntityManager en = entityManagerFactory.createEntityManager();
            en.getTransaction().begin();
            Project project = en.createQuery("select l from Project l where id=:id", Project.class)
                    .setParameter("id", projectID).getSingleResult();
            Programmer programmer1 = en.find(Programmer.class, programmerID);
            for (Programmer programmer : project.getProgrammers()) {
                if (programmer.getStatus().equals(programmer1.getStatus()) && programmer.getStatus().equals(Status.OWNER)) {
                    System.out.println("The owner already exists!!!");
                }
            }
            project.getProgrammers().add(programmer1);
            en.merge(project);
            en.getTransaction().commit();
            en.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Optional<Project> getExpensiveProject() {
        try {
            EntityManager en = entityManagerFactory.createEntityManager();
            en.getTransaction().begin();
            Project project = (Project) en.createNativeQuery("select *,price as pr from projects  order by pr desc limit 1", Project.class).getSingleResult();
            en.getTransaction().commit();
            en.close();
            return Optional.ofNullable(project);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public String getProjectWrittenInAShortTime() {
        try {
            EntityManager en = entityManagerFactory.createEntityManager();
            en.getTransaction().begin();
            Project project = (Project) en.createNativeQuery("select *,concat(extract(year from date_of_finish)-extract(year from date_of_start)) as bet from projects order by bet limit 1",
                    Project.class).getSingleResult();
            en.getTransaction().commit();
            en.close();
            int year = (int) Period.between(project.getDateOfStart(), project.getDateOfFinish()).get(ChronoUnit.YEARS);
            int month = (int) Period.between(project.getDateOfStart(), project.getDateOfFinish()).get(ChronoUnit.MONTHS);
            int days = (int) Period.between(project.getDateOfStart(), project.getDateOfFinish()).get(ChronoUnit.DAYS);
            return "nameProject: " + project.getProjectName() + "\nperiod: "+ year+"/"+month+"/"+days;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
