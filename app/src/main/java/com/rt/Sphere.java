package com.rt;

public class Sphere extends Shape{
    Point center;
    double radius;

    public Sphere(Point center, double radius, Color color) {
        super(color);
        this.center = center;
        this.radius = radius;
    }

}
