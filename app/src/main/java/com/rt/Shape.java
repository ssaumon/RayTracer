package com.rt;

/**
 * Classe abstraite représentant une forme géométrique dans une scène 3D.
 * <p>
 * Une {@code Shape} possède des propriétés de rendu telles que :
 * <ul>
 *     <li>la couleur diffuse</li>
 *     <li>la couleur spéculaire</li>
 *     <li>la brillance (shininess) pour le calcul du Phong shading</li>
 * </ul>
 * Les classes dérivées (comme {@link Sphere}) doivent implémenter les méthodes
 * pour calculer l'intersection avec un rayon et obtenir la normale en un point.
 * </p>
 */
public abstract class Shape {

    /** Couleur diffuse de la forme */
    Color color;

    /** Brillance pour l'éclairage spéculaire */
    double shininess;

    /** Couleur spéculaire utilisée pour le calcul Phong */
    Color specularColor;

    /**
     * Construit une forme avec couleur diffuse, brillance et couleur spéculaire.
     *
     * @param color couleur diffuse
     * @param shininess brillance
     * @param specularColor couleur spéculaire
     */
    public Shape(Color color, double shininess, Color specularColor) {
        this.color = color;
        this.shininess = shininess;
        this.specularColor = specularColor;
    }

    /**
     * Construit une forme avec couleur diffuse et brillance, sans couleur spéculaire.
     *
     * @param color couleur diffuse
     * @param shininess brillance
     */
    public Shape(Color color, double shininess) {
        this.color = color;
        this.shininess = shininess;
        this.specularColor = new Color(0, 0, 0);
    }

    /**
     * Calcule l'intersection entre cette forme et un rayon donné.
     *
     * @param rayon le rayon à tester
     * @return un objet {@link Intersection} représentant le point d'intersection le plus proche, ou {@code null} s'il n'y a pas d'intersection
     */
    abstract Intersection intersection(Ray rayon);

    /**
     * Calcule la normale de la forme en un point donné.
     *
     * @param p le point sur la surface de la forme
     * @return un vecteur normalisé représentant la normale en ce point
     */
    abstract Vector getNormalAt(Point p);
}
