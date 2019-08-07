package ru.stqa.jft.sandbox;

public class Point {
    public int x1;
    public int y1;
    public int x2;
    public int y2;


    public Point(int x, int y) {
        this.x1 = x;
        this.y1 = y;
    }
    public Point(int x1, int x2, int y1, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    public double distance() {
//        d = \/(х2— х1)2 + (y2— y1)

        int a = this.x2 - this.x1;
        int b = this.y2 - this.y1;
        Double d = Math.sqrt(a * a + b * b);
        System.out.println("Расстояние между точками = " + d);
        return d;
    }

}
