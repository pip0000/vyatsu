package com.vyatsu.task11;

import org.hibernate.Session;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainApp {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Scanner scanner;
        boolean exit_key = true;

        while (exit_key) {
            scanner = new Scanner(System.in);

            if (scanner.hasNext()) {
                List<String> sc = Arrays.stream(scanner.nextLine().split(" ")).filter(s -> s.length() > 0).toList();

                switch (sc.get(0)) {
                    case "/q":
                    case "/quit":
                        exit_key = false;
                        System.out.println("Выход");
                        break;

                    case "/showProductsByPerson":
                        if (sc.size() > 1) {
                            try {
                                List<CustomerProduct> productList = session.createQuery("select c.customerProduct " +
                                                "from Customer c where c.name = :name")
                                        .setParameter("name", sc.get(1)).getResultList();
                                System.out.println(sc.get(1) + " имеет продукты:");
                                for (CustomerProduct s : productList) {
                                    System.out.println(s.getProduct().getName());
                                }
                            } catch (Exception e) {
                                System.out.println("Read Error: Покупатель не найден");
                            }
                        } else {
                            System.out.println("Read Error: Неправильное использование /showProductsByPerson");
                        }
                        break;

                    case "/findPersonsByProductTitle":
                        if (sc.size() > 1) {
                            try {
                                List<CustomerProduct> productList = session.createQuery("select p.customerProduct from Product p where p.name = :name")
                                        .setParameter("name", sc.get(1)).getResultList();
                                System.out.println(sc.get(1) + " куплено покупателями:");
                                for (CustomerProduct c : productList) {
                                    System.out.println(c.getCustomer().getName());
                                }
                            } catch (Exception e) {
                                System.out.println("Read Error: Товар не найден");
                            }
                        } else {
                            System.out.println("Read Error: Неправильное использование /findPersonsByProductTitle");
                        }
                        break;

                    case "/removePerson":
                        if (sc.size() > 1) {

                            session.beginTransaction();
                            List<Integer> customer_products = session.createQuery("select cp.customerProductId from Customer c " +
                                            "join CustomerProduct cp on (c = cp.customer) where c.name = :name")
                                    .setParameter("name", sc.get(1)).getResultList();
                            for (int cp : customer_products) {
                                session.createQuery("delete from CustomerProduct where customerProductId = :id").setParameter("id", cp).executeUpdate();
                            }
                            session.createQuery("delete from Customer c where c.name = :name").setParameter("name", sc.get(1)).executeUpdate();
                            session.getTransaction().commit();
                            try {
                            } catch (Exception e) {
                                System.out.println("Read Error: Покупатель не найден");
                            }
                        } else {
                            System.out.println("Read Error: Неправильное использование /removePerson");
                        }
                        break;

                    case "/removeProduct":
                        if (sc.size() > 1) {
                            try {
                                session.beginTransaction();
                                List<Integer> customer_products = session.createQuery("" +
                                        "select cp.customerProductId from Product p join CustomerProduct cp on (p = cp.product) " +
                                        "where p.name = :name").setParameter("name", sc.get(1)).getResultList();
                                for (int cp : customer_products) {
                                    session.createQuery("delete from CustomerProduct where customerProductId = :id").setParameter("id", cp).executeUpdate();
                                }
                                session.createQuery("delete from Product p where p.name = :name").setParameter("name", sc.get(1)).executeUpdate();
                                session.getTransaction().commit();
                            } catch (Exception e) {
                                System.out.println("Read Error: Товар не найден");
                            }
                        } else {
                            System.out.println("Read Error: Неправильное использование /removeProduct");
                        }
                        break;

                    case "/buy":
                        if (sc.size() > 2) {
                            try {
                                session.beginTransaction();
                                List<Customer> c = session.createQuery("from Customer where name = :name")
                                        .setParameter("name", sc.get(1)).getResultList();
                                List<Product> p = session.createQuery("from Product where name = :name")
                                        .setParameter("name", sc.get(2)).getResultList();
                                CustomerProduct c_p = new CustomerProduct();
                                c_p.setCustomer(c.get(0));
                                c_p.setProduct(p.get(0));
                                c_p.setValue(p.get(0).getPrice());
                                session.save(c_p);
                                session.getTransaction().commit();
                            } catch (Exception e) {
                                System.out.println("Read Error: Товар или покупатель не найдены");
                            }

                        } else {
                            System.out.println("Read Error: Неправильное использование /buy");
                        }
                        break;

                    case "/buym":
                        if (sc.size() > 3) {
                            try {
                                session.beginTransaction();
                                List<Customer> c = session.createQuery("from Customer where name = :name")
                                        .setParameter("name", sc.get(1)).getResultList();
                                List<Product> p = session.createQuery("from Product where name = :name")
                                        .setParameter("name", sc.get(2)).getResultList();
                                for (int i = 0; i < Integer.parseInt(sc.get(3)); i++) {
                                    CustomerProduct c_p = new CustomerProduct();
                                    c_p.setCustomer(c.get(0));
                                    c_p.setProduct(p.get(0));
                                    c_p.setValue(p.get(0).getPrice());
                                    session.save(c_p);
                                }
                                session.getTransaction().commit();
                            } catch (Exception e) {
                                System.out.println("Read Error: Товар или покупатель не найдены");
                            }

                        } else {
                            System.out.println("Read Error: Неправильное использование /buym");
                        }
                        break;

                    case "/h":
                    case "/help":
                        System.out.println("""
                                /q /quit - выход из программы
                                /showProductsByPerson <person_name> - вывести товары, купленные person_name
                                /findPersonsByProductTitle <product_title> - вывести покупателей, купивших product_title
                                /removePerson <person_name> - удалить покупателя
                                /removeProduct <product_title> - удалить товар
                                /buy <person_name> <product_title> - покупатель покупает товар
                                /buym <person_name> <product_title> <count> - покупатель покупает товар кол-вом count
                                /h or /help - вывести комманды"""
                        );
                        break;

                    default:
                        System.out.println("Read Error: Неправильный ввод ");
                        for (String s : sc) {
                            System.out.println("<" + s + ">");
                        }
                        break;
                }
            }
            else {
                System.out.println("Read Error: Пустой ввод ");
            }
        }
        session.close();
        HibernateUtil.Shutdown();
    }
}