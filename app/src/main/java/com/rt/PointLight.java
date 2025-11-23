package com.rt;

public class PointLight extends Light{
    Point point;
    public PointLight(Point point, Color color) {
        super(color);
        this.point = point;
    }
}
