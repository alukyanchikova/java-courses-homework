package ru.stqa.jchw.sandbox.lesson1task2.method;

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

    public static void main (String[] args) {
        Point a = new Point(1, 2);
        Point b = new Point(1, 4);
        double distance = a.calculateDistanceTo(b);
        System.out.println(distance);
    }

}
