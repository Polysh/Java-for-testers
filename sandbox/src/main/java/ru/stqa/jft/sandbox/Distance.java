package ru.stqa.jft.sandbox;

public class Distance {
    public static void main(String[] args) {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(6, 7);
        distance(p1, p2);

//        Расчет через вызов метода
        Point p3 = new Point(2, 4);
        Point p4 = new Point(6, 7);
        p3.distance(p4);
    }

    public static double distance(Point p1, Point p2) {
//        d = \/(х2— х1)2 + (y2— y1)2
        int a = p2.x - p1.x;
        int b = p2.y - p1.y;
        Double d = Math.sqrt(a * a + b * b);
        System.out.println("Расстояние между точками = " + d);
        return d;
    }
}
