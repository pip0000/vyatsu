package com.vyatsu.task11;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.io.File;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        try {
            return new Configuration().configure(new File("src/main/resources/hibernate.cfg.xml"))
                    .addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(Product.class)
                    .addAnnotatedClass(CustomerProduct.class)
                    .buildSessionFactory();
        }
        catch (Throwable e){
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void Shutdown(){
        getSessionFactory().close();
    }
}