package com.vyatsu.task2;

public class Robot implements Action{
    private Treadmill.Treadmills MaxR = Treadmill.Treadmills.SuperTreadmill;
    private Wall.Walls MaxJ = Wall.Walls.LowWall;
    private String name;

    public Robot(String name) {
        this.name = name;
    }

    @Override
    public boolean run(Treadmill.Treadmills t) {
        if (t.count <= MaxR.count)
        {
            System.out.println("Robot "+name+" successfully ran");
            return true;
        }
        else
        {
            System.out.println("Robot "+name+" couldn't run");
            return false;
        }
    }

    @Override
    public boolean jump(Wall.Walls w) {
        if (w.count <= MaxJ.count)
        {
            System.out.println("Robot "+name+" successfully jumped");
            return true;
        }
        else
        {
            System.out.println("Robot "+name+" couldn't jump");
            return false;
        }
    }
}
