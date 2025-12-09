package com.rt;

/**
 * Représente une intersection entre un rayon et un objet de la scène.
 * <p>
 * Une intersection contient :
 * <ul>
 *     <li>le rayon ayant provoqué la collision</li>
 *     <li>le point exact d’impact</li>
 *     <li>la forme (Shape) percutée</li>
 * </ul>
 * Elle fournit également les méthodes nécessaires au calcul de l’éclairage :
 * Lambert, Phong, gestion des ombres (shadow rays), etc.
 * </p>
 */
public class Intersection {

    /** Rayon ayant intersecté l’objet. */
    private Ray ray;

    /** Point d’impact sur la surface. */
    private Point point;

    /** Objet de la scène ayant été intersecté. */
    private Shape shape;

    /**
     * Construit une nouvelle intersection.
     *
     * @param ray   le rayon ayant touché l’objet
     * @param point le point d’impact
     * @param shape l’objet intersecté
     */
    public Intersection(Ray ray, Point point, Shape shape) {
        this.ray = ray;
        this.point = point;
        this.shape = shape;
    }

    /** @return le rayon à l’origine de l’intersection */
    public Ray getRay() {
        return ray;
    }

    /** @return le point d’impact */
    public Point getPoint() {
        return point;
    }

    /** @return l’objet intersecté */
    public Shape getShape() {
        return shape;
    }

    /**
     * Calcule la contribution diffuse (Lambert) d’une lumière ponctuelle.
     *
     * @param light la lumière ponctuelle
     * @return la couleur diffuse générée par cette lumière
     */
    Color lambert(PointLight light) {
        Vector lightDir = (Vector) light.getPoint().soustraction(this.point).normalisation();
        Vector normal = (Vector) shape.getNormalAt(point).normalisation();
        double scalaire = Math.max(normal.produitScalaire(lightDir), 0.0);

        return (Color) light.getColor().produitSchur(shape.color).multiplication(scalaire);
    }

    /**
     * Calcule la contribution diffuse (Lambert) d’une lumière directionnelle.
     *
     * @param light lumière directionnelle
     * @return la couleur diffuse générée par cette lumière
     */
    Color lambert(VectorLight light) {
        // Convention : la lumière arrive en sens inverse du vecteur fourni
        Vector lightDir = (Vector) light.getVector().multiplication(-1).normalisation();
        Vector normal = (Vector) shape.getNormalAt(point).normalisation();
        double scalaire = Math.max(normal.produitScalaire(lightDir), 0.0);

        return (Color) light.getColor().produitSchur(shape.color).multiplication(scalaire);
    }

    /**
     * Détermine si un obstacle bloque la lumière entre l’intersection et la lumière.
     * <p>
     * Envoie un rayon d'ombre (shadow ray) vers la lumière.  
     * S’il rencontre un objet avant d’atteindre la lumière → ombre portée.
     * </p>
     *
     * @param sc    la scène dans laquelle chercher les obstacles
     * @param light la source lumineuse
     * @return {@code true} si un obstacle bloque la lumière
     */
    boolean hasObstacle(Scene sc, Light light) {

        Vector lightDir;
        double maxDistance;

        if (light.getClass() == PointLight.class) {
            PointLight pointLight = (PointLight) light;

            lightDir = (Vector) pointLight.getPoint().soustraction(this.point).normalisation();

            maxDistance = Math.sqrt(Math.pow(pointLight.getPoint().x - this.point.x, 2) + Math.pow(pointLight.getPoint().y - this.point.y, 2) + Math.pow(pointLight.getPoint().z - this.point.z, 2));

        } else {
            VectorLight vectorLight = (VectorLight) light;

            lightDir = (Vector) vectorLight.getVector().multiplication(-1).normalisation();

            maxDistance = Double.POSITIVE_INFINITY;
        }

        // Rayon légèrement décalé pour éviter l’auto-intersection
        Ray shadowRay = new Ray(
                (Point) this.point.addition(lightDir.multiplication(0.001)),
                lightDir
        );

        Intersection shadowIntersection = sc.nearIntersection(shadowRay);

        if (shadowIntersection != null) {
            double distanceToObstacle = Math.sqrt(Math.pow(shadowIntersection.getPoint().x - this.point.x, 2) + Math.pow(shadowIntersection.getPoint().y - this.point.y, 2) + Math.pow(shadowIntersection.getPoint().z - this.point.z, 2));

            if (distanceToObstacle < maxDistance) {
                return true;
            }
        }

        return false;
    }

    /**
     * Calcule la composante spéculaire selon le modèle de Phong.
     *
     * @param light   source lumineuse (ponctuelle ou directionnelle)
     * @param camera  caméra permettant de déterminer le vecteur de vision
     * @return couleur spéculaire générée par la lumière
     */
    Color phong(Light light, Camera camera) {
        Vector L;

        if (light instanceof PointLight pointLight) {
            L = (Vector) pointLight.getPoint()
                    .soustraction(this.point).normalisation();
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
