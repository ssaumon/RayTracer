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
        this.output = parser.getOutput();
        lights = (List<Light>)(Object)parser.getObjects().get("lights");
        shapes = (List<Shape>)(Object)parser.getObjects().get("shapes");
        ambient = (Color)(Object)parser.getObjects().get("ambient").get(0);
        this.camera = parser.getCamera();
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

    Point nearIntersection(Ray ray) {
        Point nearestPoint = null;
        double minDistance = Double.MAX_VALUE;

        for (Shape shape : shapes) {
            Point intersectionPoint = shape.intersection(ray);
            if (intersectionPoint != null) {
                double distance = ray.origin.distance(intersectionPoint);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestPoint = intersectionPoint;
                }
            }
        }

        return nearestPoint;
    }
}