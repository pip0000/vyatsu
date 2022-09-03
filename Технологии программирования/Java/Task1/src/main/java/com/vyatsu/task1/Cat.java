package com.vyatsu.task1;

public class Cat extends Animal{
    static int count;
    public Cat(String name, int age, int maxr, int maxs) {
        super(name, age, maxr, maxs);
        setType("Cat");
        count++;
    }

    @Override
    public void swim(int dis) {
        System.out.println(getType()+" "+name+" can't swim");
    }

    public void info(){
        System.out.println("Cat"+name);
    }
}
