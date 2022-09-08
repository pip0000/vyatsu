package com.vyatsu.task2;

public class Cat implements Action{
    private Treadmill.Treadmills MaxR = Treadmill.Treadmills.FastTreadmill;
    private Wall.Walls MaxJ = Wall.Walls.HighWall;
    private String name;

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public boolean run(Treadmill.Treadmills t) {
        if (t.count <= MaxR.count)
        {
            System.out.println("Cat "+name+" successfully ran");
            return true;
        }
        else
        {
            System.out.println("Cat "+name+" couldn't run");
            return false;
        }
    }

    @Override
    public boolean jump(Wall.Walls w) {
        if (w.count <= MaxJ.count)
        {
            System.out.println("Cat "+name+" successfully jumped");
            return true;
        }
        else
        {
            System.out.println("Cat "+name+" couldn't jump");
            return false;
        }
    }
}
