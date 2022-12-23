package com.vyatsu.task9;

import com.vyatsu.task9.entity.Cat;
import com.vyatsu.task9.entity.Oleg;

import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException
    {
        PostgresConnection postgresConnection = PostgresConnection.connect("jdbc:postgresql://localhost:5432/postgres?user=postgres&password=2536");
        SqlRepository<Cat> sqlRepository = SqlRepository.init(postgresConnection, "com.vyatsu.task9.entity.Cat");
        Cat cat1 = new Cat("Сара", 5, 30, 5,Breeds.British);
        Cat cat2 = new Cat("German name", 10, 35, 6,Breeds.German);
        Cat cat3 = new Cat("Sokoke name", 7, 40, 7,Breeds.Sokoke);
        Cat cat4 = new Cat("Oriental name", 2, 45, 8,Breeds.Oriental);
        sqlRepository.insert(cat1);
        sqlRepository.insert(cat2);
        sqlRepository.insert(cat3);
        sqlRepository.insert(cat4);
        SqlRepository<Oleg> sqlRepository1 = SqlRepository.init(postgresConnection, "com.vyatsu.task9.entity.Oleg");
        Oleg oleg1 = new Oleg("Олег полицейский", 5, 30, 5,Job.ment);
        Oleg oleg2 = new Oleg("Олег врач", 10, 35, 6,Job.vrach);
        Oleg oleg3 = new Oleg("Олег программист", 7, 40, 7,Job.programmist);
        Oleg oleg4 = new Oleg("Олег пожарник", 2, 45, 8,Job.pozharnik);
        sqlRepository1.insert(oleg1);
        sqlRepository1.insert(oleg2);
        sqlRepository1.insert(oleg3);
        sqlRepository1.insert(oleg4);
    }
}