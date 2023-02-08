package org.example.repository;

import org.example.model.Address;
import org.example.model.Programmer;
import org.example.util.DataBaseConnection;
import org.hibernate.HibernateException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProgrammerRepositoryImpl implements ProgrammerRepository{
    private EntityManagerFactory entityManagerFactory = DataBaseConnection.getSession();
    @Override
    public void saveProgrammer(Programmer programmer, Long addressId) {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            Address address = em.find(Address.class, addressId);
            address.setProgrammer(programmer);
            em.persist(programmer);
            em.getTransaction().commit();
            em.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Programmer> getAllProgrammer() {
        List<Programmer> programmers = new ArrayList<>();
        try{
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            programmers = em.createQuery("SELECT p FROM Programmer p").getResultList();
            em.getTransaction().commit();
            em.close();
        }catch(HibernateException e){
            System.out.println(e.getMessage());
        }
        return programmers;
    }

    @Override
    public Optional<Programmer> findById(Long id) {
        Programmer p = new Programmer();
        try{
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            p = em.createQuery("SELECT p FROM Programmer p WHERE p.id = :id", Programmer.class)
                    .setParameter("id", id).getSingleResult();
            em.getTransaction().commit();
            em.close();
        }catch(HibernateException e){
            System.out.println(e.getMessage());
        }
        return Optional.of(p);
    }

    @Override
    public Optional<Programmer> deleteById(Long id) {
        Programmer p = new Programmer();
        try{
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            p = em.createQuery("SELECT p FROM Programmer p WHERE p.id = :id", Programmer.class)
                    .setParameter("id",id).getSingleResult();
            em.remove(p);
            em.getTransaction().commit();
            em.close();
        }catch(HibernateException e){
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(p);
    }

    @Override
    public void deleteAllProgrammer() {
        try{
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Programmer");
            em.getTransaction().commit();
            em.close();
        }catch(HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Optional<Programmer> updateProgrammer(Long id, Programmer newProgrammer) {
        Programmer p = new Programmer();
        try{
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            p = em.createQuery("SELECT p FROM Programmer p WHERE p.id = :id", Programmer.class)
                    .setParameter("id", id).getSingleResult();
            p.setFullName(newProgrammer.getFullName());
            p.setEmail(newProgrammer.getEmail());
            p.setDateOfBirth(newProgrammer.getDateOfBirth());
            p.setStatus(newProgrammer.getStatus());
            em.merge(p);
            em.getTransaction().commit();
            em.close();
        }catch(HibernateException e){
            System.out.println(e.getMessage());
        }
        return Optional.of(p);
    }

    @Override
    public List<Programmer> findProgrammersByCountry(String name) {
        List<Programmer> programmers = new ArrayList<>();
        try{
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            programmers = em.createQuery("SELECT c FROM Countries c " +
                    "JOIN Address a on c.id = a.countries.id " +
                    "JOIN Programmer p on p.id = a.programmer.id WHERE c.country = :name", Programmer.class)
                    .setParameter("name",name).getResultList();
            em.getTransaction().commit();
            em.close();
        }catch(HibernateException e){
            System.out.println(e.getMessage());
        }
        return programmers;
    }

    @Override
    public String getYoungProgrammer() {
        try {
            EntityManager en = entityManagerFactory.createEntityManager();
            en.getTransaction().begin();
            Programmer programmer = (Programmer) en.createNativeQuery("select *,extract(year from date_of_birth) as year from programmers order by year desc limit 1", Programmer.class).getSingleResult();
            en.getTransaction().commit();
            en.close();
            int age = (int) Period.between(programmer.getDateOfBirth(), LocalDate.now()).get(ChronoUnit.YEARS);
            return "FullName: " + programmer.getFullName() + "\nage: " + age;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String getOldProgrammer() {
        try {
            EntityManager en = entityManagerFactory.createEntityManager();
            en.getTransaction().begin();
            Programmer programmer = (Programmer) en.createNativeQuery("select *,extract(year from date_of_birth) as year from programmers order by year limit 1", Programmer.class)
                    .getSingleResult();
            en.getTransaction().commit();
            en.close();
            int age = (int) Period.between(programmer.getDateOfBirth(), LocalDate.now()).get(ChronoUnit.YEARS);
            return "FullName: " + programmer.getFullName() + "\nage: " + age;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
