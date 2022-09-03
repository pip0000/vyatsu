package com.vyatsu.task1;

public class Tiger extends Animal{
    static int count;
    public Tiger(String name, int age, int maxr, int maxs) {
        super(name, age, maxr, maxs);
        setType("Tiger");
        count++;
    }
}
