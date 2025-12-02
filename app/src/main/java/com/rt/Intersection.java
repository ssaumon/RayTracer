package com.rt;

public class Intersection {
    private Ray ray;
    private Point point;
    private Shape shape;

    public Intersection(Ray ray, Point point, Shape shape) {
        this.ray = ray;
        this.point = point;
        this.shape = shape;
    }

    public Ray getRay() {
        return ray;
    }
    public Point getPoint() {
        return point;
    }
    public Shape getShape() {
        return shape;
    }


    Color lambert(PointLight light){
        Vector lightDir = (Vector)(light.getPoint().soustraction(this.point).normalisation());
        Vector normal = shape.getNormalAt(point);
        double scalaire = Double.max(normal.produitScalaire(lightDir), (double) 0);
        return  (Color)(light.getColor().produitSchur(shape.color)).multiplication(scalaire);
    }

        Color lambert(VectorLight light){
        Vector lightDir = (Vector)light.getVector().normalisation();
        Vector normal = shape.getNormalAt(point);
        double scalaire = Double.max(normal.produitScalaire(lightDir), (double) 0);
        return  (Color)(light.getColor().produitSchur(shape.color)).multiplication(scalaire);
    }

    boolean hasObstacle(Scene sc, Light light){
        Point origin = this.point;
        Ray shadowRay;
        if (light.getClass() == PointLight.class){
            PointLight pointLight = (PointLight) light;
            Vector lightDir = (Vector)pointLight.getPoint().soustraction(origin);
            shadowRay = new Ray(origin,(Vector) lightDir.normalisation());
            Intersection intersection = sc.nearIntersection(shadowRay);
            if (intersection != null && intersection.getShape() != null) {
                // On ignore si l'intersection est exactement le point d'origine
                if (!intersection.getPoint().equals(origin)) {
                    // Vérifie aussi si elle est avant la lumière
                    double distToIntersection = origin.distance(intersection.getPoint());
                    if (distToIntersection < lightDir.longueur()) {
                        return true;
                    }
                }
            }
        } else { // VectorLight
            VectorLight vectorLight = (VectorLight) light;
            shadowRay = new Ray(origin, (Vector)vectorLight.getVector().normalisation());
            Intersection intersection = sc.nearIntersection(shadowRay);
            if (intersection != null && intersection.getShape() != null) {
                // Ignore si c'est le point de départ
                if (!intersection.getPoint().equals(origin)) {
                    return true;
                }
            }
        }
        return false;
    }


    
}

