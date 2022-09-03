package com.vyatsu.task1;

public class MainApp {
    public static void main(String[] args) {
        System.out.println("Begin");

        int[] count = new int[3];

        Animal[] anim = {
                new Cat("Ginger", 3, 200,0),
                new Dog("Bob", 6, 500, 10),
                new Tiger("Alan", 4, 1000, 50),
                new Cat("Snowflake", 5, 150,0),
                new Dog("Lucky",2,300,20)
        };

        for (Animal a:anim) {
            a.run(30);
            a.swim(10);
        }

        for (int i = 0; i < anim.length; i++) {
            if (anim[i].getType() == "Cat")
                count[0]++;
            else if (anim[i].getType() == "Dog")
                count[1]++;
            else if (anim[i].getType() == "Tiger")
                count[2]++;
        }

        System.out.println("All " + Animal.count);
        System.out.println("Cat " + Cat.count);
        System.out.println("Dog " + Dog.count);
        System.out.println("Tiger " + Tiger.count);

        System.out.println("End");
    }
}
