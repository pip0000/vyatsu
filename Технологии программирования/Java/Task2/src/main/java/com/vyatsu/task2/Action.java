package com.vyatsu.task2;

public interface Action {
    boolean run(Treadmill.Treadmills T);
    boolean jump(Wall.Walls w);
}
