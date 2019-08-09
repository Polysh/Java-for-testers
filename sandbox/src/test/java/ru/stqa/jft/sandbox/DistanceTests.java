package ru.stqa.jft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.DecimalFormat;

public class DistanceTests {

    @Test
    public void testDistance(){
        Point p3 = new Point(3, 6, 3, 7);
        p3.distance();
        Assert.assertEquals( p3.distance(), 5);

    }

    @Test
    public void testDistanceDelta(){
        Point p3 = new Point(2, 6, 3, 7);
        p3.distance();
        DecimalFormat f = new DecimalFormat("##.00");
        Assert.assertEquals(Double.parseDouble(f.format(p3.distance()).replace(",", ".")), 5.67, 0.01, "Полученные значения не совпадают");

    }
}
