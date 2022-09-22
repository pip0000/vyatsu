package com.vyatsu.task4;

public class MainApp {
    public static void main(String[] args) {

        //1 и 2 задание

//        Arr<Integer> arr = new Arr<Integer>(1, 2, 3, 4);
//
//        arr.ShowArr();
//
//        arr.ToList();
//
//        arr.ShowList();
//
//        arr.Swap(0,1);
//
//        arr.ToList();
//
//        arr.ShowList();

        //3 задание

        Box<Apple> appleBox = new Box<Apple>(new Apple(),new Apple(), new Apple());
        Box<Apple> appleBox1 = new Box<Apple>(new Apple(),new Apple());
        Box<Orange> orangeBox = new Box<Orange>();

        orangeBox.addFruit(new Orange(), new Orange());

        System.out.println(appleBox.getWeight());
        System.out.println(appleBox1.getWeight());
        System.out.println(orangeBox.getWeight());

        System.out.println(appleBox.Compare(orangeBox));

        appleBox.Transfer(appleBox1);
        System.out.println(appleBox.getWeight());
        System.out.println(appleBox1.getWeight());

        System.out.println(appleBox.Compare(appleBox1));

    }
}
