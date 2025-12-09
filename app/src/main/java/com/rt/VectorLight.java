package com.rt;

/**
 * Représente une source lumineuse directionnelle dans la scène.
 * <p>
 * Une {@link VectorLight} émet de la lumière dans une direction spécifique, définie
 * par un {@link Vector}, plutôt que depuis une position donnée. Ce type de lumière
 * est couramment utilisé pour simuler la lumière du soleil ou toute source lumineuse
 * située à l'infini.
 * </p>
 *
 * <p>Cette lumière possède :
 * <ul>
 *   <li>Un vecteur de direction indiquant où la lumière est dirigée.</li>
 *   <li>Une couleur héritée de la classe {@link Light}.</li>
 * </ul>
 * </p>
 */
public class VectorLight extends Light {

    /** La direction de la lumière, représentée par un vecteur normalisé. */
    Vector vector;

    /**
     * Crée une nouvelle lumière directionnelle avec la direction et la couleur données.
     *
     * @param vector la direction de la lumière ; doit idéalement être normalisée
     * @param color  la couleur (intensité) de la lumière
     */
    public VectorLight(Vector vector, Color color) {
        super(color);
        this.vector = vector;
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
     * Retourne le vecteur de direction de la lumière.
     *
     * @return le vecteur de direction
     */
    Vector getVector() {
        return this.vector;
    }
}
