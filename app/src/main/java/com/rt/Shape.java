package com.rt;

public abstract class Shape {
Color color;

    public Shape(Color color) {
        this.color = color;
    }

    abstract Intersection intersection(Ray rayon);
    abstract Vector getNormalAt(Point p);
}
