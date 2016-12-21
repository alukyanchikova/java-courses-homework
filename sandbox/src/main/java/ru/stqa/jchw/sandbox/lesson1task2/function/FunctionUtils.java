package ru.stqa.jchw.sandbox.lesson1task2.function;

public class FunctionUtils {

    public static double calculateDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2));
    }

    public static void main(String[] args) {
        Point p1 = new Point(1, 6);
        Point p2 = new Point(1, 3);
        double distance = calculateDistance(p1, p2);
        System.out.println(distance);
    }
}
