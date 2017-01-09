package ru.stqa.jchw.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EquationTest {

    @Test
    public void testNoSolutions (){
        Equation e = new Equation(1, 1, 1);
        Assert.assertEquals(e.rootNumber(), 0);
    }

    @Test
    public void testOneSolution (){
        Equation e = new Equation(1, 2, 1);
        Assert.assertEquals(e.rootNumber(), 1);
    }

    @Test
    public void testTwoSolutions (){
        Equation e = new Equation(1, 5, 6);
        Assert.assertEquals(e.rootNumber(), 2);
    }

    @Test
    public void testLenear (){
        Equation e = new Equation(0, 1, 1);
        Assert.assertEquals(e.rootNumber(), 1);
    }

    @Test
    public void testConstant () {
        Equation e = new Equation(0, 0, 1);
        Assert.assertEquals(e.rootNumber(), 0);
    }

    @Test
    public void testZero() {
        Equation e = new Equation(0, 0, 0);
        Assert.assertEquals(e.rootNumber(), -1);
    }

}
