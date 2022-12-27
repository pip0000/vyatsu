package com.vyatsu.task12;

import jakarta.persistence.LockModeType;
import jakarta.persistence.OptimisticLockException;
import org.hibernate.HibernateException;
import org.hibernate.PessimisticLockException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class MainApp {
    public static void main(String[] args) throws InterruptedException {
        long time = System.currentTimeMillis();
        long opt;
        long pes;

        optimistic();
        opt = System.currentTimeMillis() - time;
        System.out.printf("Optimistic - %d ms", opt);
        System.out.println();
//        pessimistic();
//        pes = System.currentTimeMillis() - time;
//        System.out.printf("Pessimistic - %d ms", pes);
//        System.out.println();
    }

    public static void UncheckableSleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void optimistic() throws InterruptedException {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.getTransaction().begin();

        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            items.add(new Item(0, 0));
            session.persist(items.get(i));
        }

        session.getTransaction().commit();

        CountDownLatch cdl = new CountDownLatch(8);

        for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                Session session_t = HibernateUtil.getSessionFactory().openSession();

                for (int j = 0; j < 20000; j++) {
                    try {
                        session_t.beginTransaction();
                        Item item = session_t.get(Item.class, (int) (Math.random() * 40 + 1));
                        item.setValue(item.getValue() + 1);
                        session_t.getTransaction().commit();
                    }
                    catch (OptimisticLockException a) {
                        session_t.getTransaction().rollback();
                        j--;
                    }

                    UncheckableSleep(15);
                }

                System.out.println("Thread ready");

                session_t.close();

                cdl.countDown();
            }).start();
        }
        cdl.await();
        List res = session.createNativeQuery("select sum(i.value) from Item i").getResultList();
        System.out.println("Objects sum : " + res.get(0));

        session.close();
    }

    public static void pessimistic() throws InterruptedException {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.getTransaction().begin();

        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            items.add(new Item(0, 0));
            session.persist(items.get(i));
        }

        session.getTransaction().commit();

        CountDownLatch cdl = new CountDownLatch(8);

        for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                Session session_t = HibernateUtil.getSessionFactory().openSession();

                for (int j = 0; j < 20000; j++) {
                    try {
                        session_t.beginTransaction();

                        Item item = session_t.createQuery("from Item where id = :id", Item.class)
                                .setParameter("id", (int) (Math.random() * 40 + 1))
                                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                                .getSingleResult();

                        item.setValue(item.getValue() + 1);

                        session_t.persist(item);
                        session_t.getTransaction().commit();
                    }
                    catch (HibernateException e) {
                        session_t.getTransaction().rollback();
                        j--;
                    }
                    catch(OptimisticLockException art){
                        j--;
                        session_t.getTransaction().rollback();
                    }

                    UncheckableSleep(15);
                }

                System.out.println("Thread ready");

                session_t.close();

                cdl.countDown();
            }).start();
        }
        cdl.await();

        List res = session.createNativeQuery("select sum(i.value) from Item i").getResultList();
        System.out.println("Objects sum : " + res.get(0));

        session.close();
    }
}
