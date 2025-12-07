package com.rt;

public abstract class Shape {
Color color;
double shininess;
Color specularColor;

    public Shape(Color color, double shininess, Color specularColor) {
        this.color = color;
        this.shininess=shininess;
        this.specularColor = specularColor;
    }

    public Shape(Color color, double shininess) {
        this.color = color;
        this.shininess=shininess;
        this.specularColor = new Color(0,0,0);
    }

    abstract Intersection intersection(Ray rayon);
    abstract Vector getNormalAt(Point p);
}
