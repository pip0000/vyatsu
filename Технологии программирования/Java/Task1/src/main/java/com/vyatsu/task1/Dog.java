package com.vyatsu.task1;

public class Dog extends Animal{
    static int count;
    public Dog(String name, int age, int maxr, int maxs) {
        super(name, age, maxr, maxs);
        setType("Dog");
        count++;
    }
}
