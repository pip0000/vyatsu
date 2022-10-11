package com.vyatsu.task6;

import java.util.Arrays;

public class MainApp {
    static final int size = 50000000;
    static final int half = size/2;

    public static void main(String[] args) {
        TwoThreads();
        OneThread();
    }

    public static void OneThread()
    {
        float[] arr = new float[size];
        Arrays.fill(arr,1.0f);
        long time = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float)(arr[i]*Math.sin(0.2f + i/5)*Math.cos(0.2f + i/5)*Math.cos(0.4f + i/2));
        }
        System.out.println("Время: "+(System.currentTimeMillis()-time));
        System.out.println("Первая ячейка - "+arr[0]+" последняя ячейка - "+arr[size-1]);
    }

    public static void TwoThreads()
    {
        float[] arr = new float[size];
        float[] arr1 = new float[half];
        final float[] arr2 = new float[half];
        Arrays.fill(arr,1.0f);
        System.arraycopy(arr,0,arr1,0,half);
        System.arraycopy(arr,half,arr2,0,half);
        long time = System.currentTimeMillis();

        Thread t = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < half; i++) {
                    arr2[i] = (float)(arr2[i]*Math.sin(0.2f + (i+half)/5)*Math.cos(0.2f + (i+half)/5)*Math.cos(0.4f + (i+half)/2));
                }
            }
        });

        t.start();

        for (int i = 0; i < half; i++) {
            arr1[i] = (float)(arr1[i]*Math.sin(0.2f + i/5)*Math.cos(0.2f + i/5)*Math.cos(0.4f + i/2));
        }

        try {
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.arraycopy(arr1, 0, arr, 0, half);
        System.arraycopy(arr2, 0, arr, half, half);

        System.out.println("Время: "+(System.currentTimeMillis()-time));
        System.out.println("Первая ячейка - "+arr[0]+" последняя ячейка - "+arr[size-1]);
    }
}
