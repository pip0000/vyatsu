package com.vyatsu.task4;

import java.util.ArrayList;

public class Arr<T> {
    private T[] arr;
    private ArrayList<T> arrList;

    public Arr(T... arr) {
        this.arr = arr;
    }

    public T Swap(int i1, int i2)
    {
        T t = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = t;
        return (T) arr;
    }

    public T[] getArr() {
        return arr;
    }

    public void ShowArr()
    {
        for (int i = 0; i < arr.length; i++)
        {
            System.out.print(arr[i]+" ");
        }
    }

    public void ShowList()
    {
        System.out.println(arrList);
    }

    public void ToList()
    {
        arrList.clear();
        for (int i = 0; i < arr.length; i++)
        {
            arrList.add(arr[i]);
        }
    }
}
