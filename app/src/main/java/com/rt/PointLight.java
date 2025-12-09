package com.rt;

/**
 * Représente une source lumineuse ponctuelle dans la scène.
 * <p>
 * Une {@link PointLight} émet de la lumière depuis une position spécifique dans l'espace,
 * contrairement à {@link VectorLight} qui a une direction fixe mais pas de position définie.
 * </p>
 *
 * <p>Cette lumière possède :
 * <ul>
 *   <li>Un point dans l'espace représentant sa position.</li>
 *   <li>Une couleur héritée de la classe {@link Light}.</li>
 * </ul>
 * </p>
 */
public class PointLight extends Light {

    /** La position de la lumière dans l'espace 3D. */
    Point point;

    /**
     * Crée une nouvelle lumière ponctuelle avec la position et la couleur données.
     *
     * @param point la position de la lumière
     * @param color la couleur (intensité) de la lumière
     */
    public PointLight(Point point, Color color) {
        super(color);
        this.point = point;
    }

    /**
     * Retourne la couleur de la lumière.
     *
     * @return la couleur de la lumière
     */
    @Override
    Color getColor() {
        return this.color;
    }

    /**
     * Retourne la position de la lumière.
     *
     * @return le point représentant la position de la lumière
     */
    Point getPoint() {
        return this.point;
    }
}
