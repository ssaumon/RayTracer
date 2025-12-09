package com.rt;

/**
 * Représente un rayon dans l'espace 3D.
 * <p>
 * Un rayon est défini par :
 * <ul>
 *     <li>un point d'origine ({@link #origin})</li>
 *     <li>une direction ({@link #direction})</li>
 * </ul>
 * Les rayons sont utilisés pour calculer les intersections avec les formes
 * et déterminer l'éclairage lors du rendu.
 * </p>
 */
public class Ray {

    /** Point d'origine du rayon */
    Point origin;

    /** Direction du rayon (doit être normalisée pour des calculs corrects) */
    Vector direction;

    /**
     * Construit un rayon avec un point d'origine et une direction.
     *
     * @param origin point d'origine du rayon
     * @param direction vecteur de direction
     */
    public Ray(Point origin, Vector direction) {
        this.origin = origin;
        this.direction = direction;
    }
}
