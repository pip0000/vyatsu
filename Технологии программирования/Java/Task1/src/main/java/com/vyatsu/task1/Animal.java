package com.vyatsu.task1;

public class Animal {
    String name;
    int age = 1;
    int maxr;
    int maxs;

    private String type;
    static int count;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Animal(String name, int age, int maxr, int maxs) {
        count++;
        this.name = name;
        this.age = age;
        this.maxr = maxr;
        this.maxs = maxs;
    }

    public void run(int dis) {
        if (dis < 0)
            System.out.println("Distance for " + name + " < 0>");
        else if (dis <= maxr)
            System.out.println(type + " " + name + " run " + dis + " m");
        else if (dis > maxr)
            System.out.println(type + " " + name + " can't run " + dis + " m");
    }

    public void swim(int dis) {
        if (dis < 0)
            System.out.println("Distance for " + name + " < 0");
        else if (dis <= maxs)
            System.out.println(type + " " + name + " swim " + dis + " m");
        else if (dis > maxs)
            System.out.println(type + " " + name + " can't swim " + dis + " m");
    }
}
