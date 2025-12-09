package com.rt;

/**
 * Représente une source lumineuse dans la scène.
 * <p>
 * Chaque lumière possède une couleur qui détermine son intensité et sa contribution
 * RGB lors de l'éclairage des objets. Les types spécifiques de lumière 
 * (par exemple {@link PointLight} ou {@link VectorLight}) héritent de cette classe
 * pour définir leur position ou leur direction.
 * </p>
 */
public class Light {

    /** La couleur de la lumière, représentant son intensité et ses composantes RGB. */
    Color color;

    /**
     * Crée une nouvelle lumière avec la couleur spécifiée.
     *
     * @param color la couleur (intensité) de la lumière
     */
    public Light(Color color) {
        this.color = color;
    }

    /**
     * Retourne la couleur de la lumière.
     *
     * @return la couleur de la lumière
     */
    Color getColor() {
        return this.color;
    }
}
