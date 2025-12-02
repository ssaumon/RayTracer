package com.rt;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private int width;
    private int height;
    private Camera camera;
    private String output = "output.png";
    private Color ambient = new Color();
    private List<Light> lights = new ArrayList<>();
    private List<Shape> shapes = new ArrayList<>();
    
    Scene(SceneFileParser parser) throws Exception {
        this.width = parser.getWidth();
        this.height = parser.getHeight();
        this.output = (String)parser.getObjects().get("output").get(0);
        lights = (List<Light>)(Object)parser.getObjects().get("lights");
        shapes = (List<Shape>)(Object)parser.getObjects().get("shapes");
        ambient = (Color)(Object)parser.getObjects().get("ambient").get(0);
        this.camera = (Camera)parser.getObjects().get("camera").get(0);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getOutput() {
        return output;
    }

    public Color getAmbient() {
        return ambient;
    }

    public List<Light> getLights() {
        return lights;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public Camera getCamera() {
        return camera;
    }

    Intersection nearIntersection(Ray ray) {
        Point nearestPoint = null;
        double minDistance = Double.MAX_VALUE;
        Shape nearestShape = null;
        double eps = 1e-7;

        for (Shape shape : shapes) {
                Intersection inter = shape.intersection(ray);
                if (inter != null) {
                    Point p = inter.getPoint();
                    double dist = ray.origin.distance(p);

                    if (dist > eps && dist < minDistance) {
                        minDistance = dist;
                        nearestPoint = p;
                        nearestShape = shape;
                    }
                }
            }
        if (nearestPoint == null) {
            return null;
        }
        return new Intersection(ray, nearestPoint, nearestShape);
    }
}