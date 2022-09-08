package com.vyatsu.task2;

public class Human implements Action{
    private Treadmill.Treadmills MaxR = Treadmill.Treadmills.NormalTreadmill;
    private Wall.Walls MaxJ = Wall.Walls.MiddleWall;
    private String name;

    public Human(String name) {
        this.name = name;
    }

    @Override
    public boolean run(Treadmill.Treadmills t) {
        if (t.count <= MaxR.count)
        {
            System.out.println("Human "+name+" successfully ran");
            return true;
        }
        else
        {
            System.out.println("Human "+name+" couldn't run");
            return false;
        }
    }

    @Override
    public boolean jump(Wall.Walls w) {
        if (w.count <= MaxJ.count)
        {
            System.out.println("Human "+name+" successfully jumped");
            return true;
        }
        else
        {
            System.out.println("Human "+name+" couldn't jump");
            return false;
        }
    }
}
