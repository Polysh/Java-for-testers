package ru.stqa.jft.sandbox;

public class Point {
    public int x;
    public int y;


    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point p) {
//        d = \/(х2— х1)2 + (y2— y1)

        int a = this.x - p.x;
        int b = this.y - p.y;
        Double d = Math.sqrt(a * a + b * b);
        System.out.println("Расстояние между точками = " + d);
        return d;
    }

}
