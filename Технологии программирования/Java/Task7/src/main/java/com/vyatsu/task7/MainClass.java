package com.vyatsu.task7;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static final CountDownLatch cd = new CountDownLatch(CARS_COUNT);
    public static final CountDownLatch cd1 = new CountDownLatch(CARS_COUNT);
    public static final CyclicBarrier cb = new CyclicBarrier(CARS_COUNT);
    public static final Semaphore smp = new Semaphore(CARS_COUNT/2);

    public static void main (String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        try {
            cd.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        try {
            cd1.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
