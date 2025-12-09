package com.rt;

/**
 * Représente une sphère dans l'espace 3D.
 * <p>
 * La sphère est définie par son centre, son rayon, sa couleur diffuse,
 * sa couleur spéculaire et sa brillance pour le calcul Phong.
 * Cette classe hérite de {@link Shape} et implémente les méthodes
 * pour calculer l'intersection avec un rayon et obtenir la normale en un point.
 * </p>
 */
public class Sphere extends Shape {

    /** Centre de la sphère */
    Point center;

    /** Rayon de la sphère */
    double radius;

    /**
     * Construit une sphère avec couleur diffuse et brillance.
     *
     * @param center centre de la sphère
     * @param radius rayon de la sphère
     * @param color couleur diffuse
     * @param shininess brillance pour le calcul spéculaire
     */
    public Sphere(Point center, double radius, Color color, double shininess) {
        super(color, shininess);
        this.center = center;
        this.radius = radius;
    }

    /**
     * Construit une sphère avec couleur diffuse, couleur spéculaire et brillance.
     *
     * @param center centre de la sphère
     * @param radius rayon de la sphère
     * @param color couleur diffuse
     * @param shininess brillance pour le calcul spéculaire
     * @param specularColor couleur spéculaire
     */
    public Sphere(Point center, double radius, Color color, double shininess, Color specularColor) {
        super(color, shininess, specularColor);
        this.center = center;
        this.radius = radius;
    }

    /**
     * Calcule l'intersection entre un rayon et cette sphère.
     *
     * @param rayon le rayon à tester
     * @return l'intersection la plus proche sous forme d'un objet {@link Intersection}, ou {@code null} si aucune intersection
     */
    @Override
    Intersection intersection(Ray rayon) {
        double a = rayon.direction.produitScalaire(rayon.direction);
        double b = rayon.direction.produitScalaire(rayon.origin.soustraction(center)) * 2;
        double c = (rayon.origin.soustraction(center)).produitScalaire(rayon.origin.soustraction(center)) - radius * radius;
        double delta = b * b - 4 * a * c;

        if (delta < 0) {
            return null;
        } else {
            double t1 = (-b - Math.sqrt(delta)) / (2 * a);
            double t2 = (-b + Math.sqrt(delta)) / (2 * a);
            double t;
            if (t1 > 0 && t2 > 0) {
                t = Math.min(t1, t2);
            } else if (t1 > 0) {
                t = t1;
            } else if (t2 > 0) {
                t = t2;
            } else {
                return null;
            }
            return new Intersection(
                    rayon,
                    new Point(rayon.origin.x + t * rayon.direction.x,
                              rayon.origin.y + t * rayon.direction.y,
                              rayon.origin.z + t * rayon.direction.z),
                    this
            );
        }
    }

    /**
     * Calcule la normale de la sphère en un point donné.
     *
     * @param p le point sur la surface de la sphère
     * @return un vecteur normalisé représentant la normale en ce point
     */
    @Override
    Vector getNormalAt(Point p) {
        return (Vector) (p.soustraction(center)).normalisation();
    }
}
