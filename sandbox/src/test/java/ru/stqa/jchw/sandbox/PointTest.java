package ru.stqa.jchw.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PointTest {

    @Test
    public void shouldCorrectlyCalculateDistance() {
        Point a = new Point(1, 1);
        Point b = new Point(1, 2);
        Assert.assertEquals(a.calculateDistanceTo(b), 1.0);
    }

    @Test
    public void shouldReturnZeroWhenBothPointsAreEqual() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 0);
        Assert.assertEquals(a.calculateDistanceTo(b), 0.0);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void shouldFailWithExceptionWhenNullArgumentGiven() {
        Point a = new Point(1, -1);
        Point b = null;
        a.calculateDistanceTo(b);
    }
}