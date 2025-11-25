package com.rt;

public class VectorLight extends Light{
    Vector vector;
    public VectorLight(Vector vector, Color color) {
        super(color);
        this.vector = vector;
    }
    Color getColor() {
        return this.color;
    }
    Vector getVector() {
        return this.vector;
    }

}
