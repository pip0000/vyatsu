package com.vyatsu.task4;

import java.util.ArrayList;

public class Box<F extends Fruit> {
    ArrayList<F> fruits = new ArrayList<F>();

    public Box(F... f)
    {
        this.addFruit(f);
    }

    public Box() {
    }

    public float getWeight()
    {
        float weight = 0f;
        for (int i = 0; i < fruits.size(); i++)
        {
            weight += fruits.get(i).getWeight();
        }
        return weight;
    }

    public void addFruit(F... f)
    {
        for (int i = 0; i < f.length; i++)
        {
            fruits.add(f[i]);
        }
    }

    public boolean Compare(Box<? extends Fruit> another)
    {
       return Math.abs(this.getWeight() - another.getWeight()) <= 0.001f;
    }

    public void Transfer(Box<F> another)
    {
        if (another != this)
        {
            another.fruits.addAll(this.fruits);
            this.fruits.clear();
        }
    }
}
