package com.vyatsu.task2;

public class MainApp {
    public static void main(String[] args) {
        Obstacle[] ob = new Obstacle[]{
                new Wall(Wall.Walls.LowWall),
                new Treadmill(Treadmill.Treadmills.SlowTreadmill),
                new Treadmill(Treadmill.Treadmills.SuperTreadmill),
                new Wall(Wall.Walls.MiddleWall),
                new Wall(Wall.Walls.SuperWall),
                new Treadmill(Treadmill.Treadmills.FastTreadmill)
        };

        Action[] Cont = new Action[] {
                new Cat("Ginger"),
                new Human("John"),
                new Robot("R2D2")
        };

        for(Action a: Cont)
        {
            for(Obstacle o: ob)
            {
                if(!o.contest(a)) break;
            }
        }
    }
}
