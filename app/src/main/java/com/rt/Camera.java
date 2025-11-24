package com.rt;

public class Camera {
    Point lookFrom;
    Point lookAt;
    Vector up;
    double fov;

    public Camera(Point lookFrom, Point lookAt, Vector up, double fov) {
        this.lookFrom = lookFrom;
        this.lookAt = lookAt;
        this.up = up;
        this.fov = fov;
    }

    @Override
    public String toString() {
        return lookFrom.toString() + "\n" + lookAt.toString() + "\n" + up.toString() + "\n" + fov;
    }
}
