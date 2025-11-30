package com.rt;

public abstract class Shape {
Color color;

    public Shape(Color color) {
        this.color = color;
    }

    abstract Point intersection(Ray rayon);
}
