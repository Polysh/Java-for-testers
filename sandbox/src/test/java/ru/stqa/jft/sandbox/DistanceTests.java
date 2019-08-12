package ru.stqa.jft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.DecimalFormat;

public class DistanceTests {

    @Test
    public void testDistance() {
        Point p3 = new Point(2, 4);
        Point p4 = new Point(6, 7);
        p3.distance(p4);

        Assert.assertEquals(p3.distance(p4), 5);

    }

    @Test
    public void testDistanceDelta() {
        Point p3 = new Point(2, 3);
        Point p4 = new Point(6, 7);
        p3.distance(p4);
        DecimalFormat f = new DecimalFormat("##.00");
        Assert.assertEquals(Double.parseDouble(f.format(p3.distance(p4)).replace(",", ".")), 5.67, 0.01, "Полученные значения не совпадают");

    }
}
