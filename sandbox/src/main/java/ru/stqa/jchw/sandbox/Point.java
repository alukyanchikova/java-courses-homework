package ru.stqa.jchw.sandbox;

public class Point {

    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double calculateDistanceTo(Point neighbor) {
        return Math.sqrt(Math.pow((this.x - neighbor.x), 2) + Math.pow((this.y - neighbor.y), 2));
    }
}
