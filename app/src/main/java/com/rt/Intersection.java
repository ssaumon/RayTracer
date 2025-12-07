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
        Vector normal = (Vector) shape.getNormalAt(point).normalisation();
        double scalaire = Math.max(normal.produitScalaire(lightDir), 0.0);
        return (Color)(light.getColor().produitSchur(shape.color)).multiplication(scalaire);
    }

    Color lambert(VectorLight light){
        // ATTENTION : Vérifier si il faut inverser selon convention
        Vector lightDir = (Vector)light.getVector().multiplication(-1).normalisation();
        Vector normal = (Vector) shape.getNormalAt(point).normalisation();
        double scalaire = Math.max(normal.produitScalaire(lightDir), 0.0);
        return (Color)(light.getColor().produitSchur(shape.color)).multiplication(scalaire);
    }

    boolean hasObstacle(Scene sc, Light light){
        Vector lightDir;
        double maxDistance;
        if (light.getClass() == PointLight.class){
            PointLight pointLight = (PointLight) light;
            lightDir = (Vector)(pointLight.getPoint().soustraction(this.point).normalisation());
            maxDistance = Math.sqrt(Math.pow(pointLight.getPoint().x - this.point.x,2) + Math.pow(pointLight.getPoint().y - this.point.y,2) + Math.pow(pointLight.getPoint().z - this.point.z,2));
        } else {
            VectorLight vectorLight = (VectorLight) light;
            lightDir = (Vector)vectorLight.getVector().multiplication(-1).normalisation();
            maxDistance = Double.POSITIVE_INFINITY;
        }
        Ray shadowRay = new Ray((Point) this.point.addition(lightDir.multiplication(0.001)), lightDir);
        Intersection shadowIntersection = sc.nearIntersection(shadowRay);
        if (shadowIntersection != null){
            double distanceToObstacle = Math.sqrt(Math.pow(shadowIntersection.getPoint().x - this.point.x,2) + Math.pow(shadowIntersection.getPoint().y - this.point.y,2) + Math.pow(shadowIntersection.getPoint().z - this.point.z,2));
            if (distanceToObstacle < maxDistance){
                return true;
            }
        }
        return false;
    }

Color phong(Light light, Camera camera){
    Vector L;
    if (light instanceof PointLight pointLight) {
        L = (Vector) pointLight.getPoint().soustraction(this.point).normalisation();
    } else {
        VectorLight vectorLight = (VectorLight) light;
        L = (Vector) vectorLight.getVector().normalisation();
    }
    Vector N = (Vector) shape.getNormalAt(point).normalisation();
    Vector V = (Vector) camera.getLookFrom().soustraction(this.point).normalisation();
    Vector H = (Vector) L.addition(V).normalisation();
    double cosNH = Math.max(N.produitScalaire(H), 0.0);
    double specularFactor = Math.pow(cosNH, shape.shininess);
    return (Color) light.getColor().produitSchur(shape.specularColor).multiplication(specularFactor);
}
}