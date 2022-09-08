package com.vyatsu.task2;

public class Wall extends Obstacle{
    public enum Walls {
        LowWall(1), MiddleWall(2), HighWall(3), SuperWall(4);

        Walls(int count) {
            this.count = count;
        }

        public int count;
        }
    public Walls w;

    public Wall(Walls w) {
        this.w = w;
    }

    @Override
    public boolean contest(Action a) {return a.jump(w);}
}
