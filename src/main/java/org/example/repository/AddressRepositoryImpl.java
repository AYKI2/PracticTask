package org.example.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.example.enums.Country;
import org.example.model.Address;
import org.example.model.Countries;
import org.example.util.DataBaseConnection;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddressRepositoryImpl implements AddressRepository,AutoCloseable{
    private EntityManagerFactory entityManagerFactory = new DataBaseConnection().getSession();
    @Override
    public void saveAddress(Address address, Long countryId) {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            Countries country = em.find(Countries.class, countryId);
            country.getAddresses().add(address);
            em.merge(address);
            em.persist(address);
            em.getTransaction().commit();
            em.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveAllAddress(List<Address> addresses, Long countryId) {
        try{
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            Countries countries = em.find(Countries.class, countryId);
            for(Address a:addresses){
                countries.getAddresses().add(a);
                em.persist(a);
            }
            em.getTransaction().commit();
            em.close();
        }catch(HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Address> getAllAddresses() {
        List<Address> addresses = new ArrayList<>();
        try{
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            addresses = em.createQuery("SELECT a FROM Address a").getResultList();
            em.getTransaction().commit();
            em.close();
        }catch(HibernateException e){
            System.out.println(e.getMessage());
        }
        return addresses;
    }

    @Override
    public Optional<Address> findById(Long id) {
        Address a = new Address();
        try{
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            a = em.createQuery("SELECT a FROM Address a WHERE a.id = :id", Address.class)
                    .setParameter("id",id).getSingleResult();
            em.getTransaction().commit();
            em.close();
        }catch(HibernateException e){
            System.out.println(e.getMessage());
        }
        return Optional.of(a);
    }

    @Override
    public Optional<Address> deleteById(Long id) {
        Address a = new Address();
        try{
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            a = em.createQuery("SELECT a FROM Address a WHERE a.id = :id", Address.class)
                    .setParameter("id",id).getSingleResult();
            em.remove(a);
            em.getTransaction().commit();
            em.close();
        }catch(HibernateException e){
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(a);
    }

    @Override
    public void deleteAllAddress() {
        try{
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Address ");
            em.getTransaction().commit();
            em.close();
        }catch(HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Optional<Address> updateAddress(Long id, Address newAddress) {
        Address a = new Address();
        try{
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            a = em.createQuery("SELECT a FROM Address a WHERE a.id = :id", Address.class)
                    .setParameter("id", id).getSingleResult();
            a.setRegionName(newAddress.getRegionName());
            a.setStreet(newAddress.getStreet());
            a.setHomeNumber(newAddress.getHomeNumber());
            em.merge(a);
            em.getTransaction().commit();
            em.close();
        }catch(HibernateException e){
            System.out.println(e.getMessage());
        }
        return Optional.of(a);
    }
    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
