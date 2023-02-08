package org.example.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.example.enums.Country;
import org.example.model.Countries;
import org.example.util.DataBaseConnection;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CountryRepositoryImpl implements CountryRepository,AutoCloseable{
    private EntityManagerFactory en = DataBaseConnection.getSession();
    @Override
    public void saveCountry(Countries countries) {
        try {
            EntityManager em = en.createEntityManager();
            em.getTransaction().begin();
            em.persist(countries);
            em.getTransaction().commit();
            em.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveAllCountries(List<Countries> countries) {
        try{
            EntityManager em = en.createEntityManager();
            em.getTransaction().begin();
            for(Countries c:countries){
                em.persist(c);
            }
            em.getTransaction().commit();
            em.close();
        }catch(HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Countries> getAllCountries() {
        List<Countries> countriesList = new ArrayList<>();
        try{
            EntityManager em = en.createEntityManager();
            em.getTransaction().begin();
            countriesList = em.createQuery("SELECT c FROM Countries c").getResultList();
            em.getTransaction().commit();
            em.close();
        }catch(HibernateException e){
            System.out.println(e.getMessage());
        }
        return countriesList;
    }

    @Override
    public Optional<Countries> findById(Long id) {
        Countries c = new Countries();
        try{
            EntityManager em = en.createEntityManager();
            em.getTransaction().begin();
            c = em.createQuery("SELECT c FROM Countries c WHERE c.id = :id", Countries.class)
                    .setParameter("id",id).getSingleResult();
            em.getTransaction().commit();
            em.close();
        }catch(HibernateException e){
            System.out.println(e.getMessage());
        }
        return Optional.of(c);
    }

    @Override
    public Optional<Countries> deleteById(Long id) {
        Countries c = new Countries();
        try{
            EntityManager em = en.createEntityManager();
            em.getTransaction().begin();
            c = em.createQuery("SELECT c FROM Countries c WHERE c.id = :id", Countries.class)
                    .setParameter("id",id).getSingleResult();
            em.remove(em.merge(c));
            em.getTransaction().commit();
            em.close();
        }catch(HibernateException e){
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(c);
    }

    @Override
    public void deleteAllCountries() {
        try{
            EntityManager em = en.createEntityManager();
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Countries");
            em.getTransaction().commit();
            em.close();
        }catch(HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Optional<Countries> updateCountry(Long id, Countries newCountries) {
        Countries c = new Countries();
        try{
            EntityManager em = en.createEntityManager();
            em.getTransaction().begin();
            c = em.createQuery("SELECT c FROM Countries c WHERE c.id = :id", Countries.class)
                    .setParameter("id", id).getSingleResult();
            c.setCountry(newCountries.getCountry());
            c.setDescription(newCountries.getDescription());
            em.merge(c);
            em.getTransaction().commit();
            em.close();
        }catch(HibernateException e){
            System.out.println(e.getMessage());
        }
        return Optional.of(c);
    }

    @Override
    public Optional<Countries> getCountryByLongDescription() {
        try{
            EntityManager em = en.createEntityManager();
            em.getTransaction().begin();
            Countries c = (Countries) em.createNativeQuery("SELECT * FROM countries ORDER BY length(description) DESC limit 1", Countries.class).getSingleResult();
            em.getTransaction().commit();
            em.close();
            return Optional.of(c);
        }catch(HibernateException e){
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public int countryQuantity(String name) {
        try {
            EntityManager em = en.createEntityManager();
            em.getTransaction().begin();
            List<Country> countries = em.createQuery("select c from Countries c where country=:name", Country.class)
                    .setParameter("name", name)
                    .getResultList();
            em.getTransaction().commit();
            em.close();
            return countries.size();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public void close() throws Exception {
        en.close();
    }
}
