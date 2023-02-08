package org.example.util;

import javax.persistence.EntityManagerFactory;
import org.example.model.Address;
import org.example.model.Countries;
import org.example.model.Programmer;
import org.example.model.Project;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class DataBaseConnection {
    public static EntityManagerFactory getSession(){
        Configuration configuration = new Configuration();
        try {
            Properties properties = new Properties();
            properties.put(Environment.DRIVER, "org.postgresql.Driver");
            properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/java8");
            properties.put(Environment.USER, "postgres");
            properties.put(Environment.PASS, "admin123");

            properties.put(Environment.HBM2DDL_AUTO, "update");
            properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
            properties.put(Environment.SHOW_SQL, "true");

            configuration.addProperties(properties);
            configuration.addAnnotatedClass(Address.class);
            configuration.addAnnotatedClass(Countries.class);
            configuration.addAnnotatedClass(Programmer.class);
            configuration.addAnnotatedClass(Project.class);
            System.out.println("Successfully connected...");
            return  configuration.buildSessionFactory().unwrap(EntityManagerFactory.class);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return configuration.buildSessionFactory().unwrap(EntityManagerFactory.class);
    }
}
