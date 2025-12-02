package com.rt;


public class Sphere extends Shape{
    Point center;
    double radius;

    public Sphere(Point center, double radius, Color color) {
        super(color);
        this.center = center;
        this.radius = radius;
    }

    @Override
    Intersection intersection(Ray rayon){
        double a = rayon.direction.produitScalaire(rayon.direction);
        double b = rayon.direction.produitScalaire(rayon.origin.soustraction(center))*2;
        double c = (rayon.origin.soustraction(center)).produitScalaire(rayon.origin.soustraction(center)) - radius*radius;
        double delta = b*b - 4*a*c;

        if (delta < 0){
            return null;
        }
        else{
            double t1 = (-b - Math.sqrt(delta)) / (2*a);
            double t2 = (-b + Math.sqrt(delta)) / (2*a);
            double t;
            if (t1 > 0 && t2 > 0){
                t = Math.min(t1, t2);
            }
            else if (t1 > 0){
                t = t1;
            }
            else if (t2 > 0){
                t = t2;
            }
            else{
                return null;
            }
            return new Intersection(rayon, new Point(rayon.origin.x + t*rayon.direction.x, rayon.origin.y + t*rayon.direction.y, rayon.origin.z + t*rayon.direction.z), this);
        }
    }

    @Override
    Vector getNormalAt(Point p){
        return (Vector)(p.soustraction(center)).normalisation();
    }

}
