package com.rt;

/**
 * Représente un vecteur 3D dans l'espace.
 * <p>
 * Cette classe hérite de {@link AbstractVec3} et fournit des opérations 
 * vectorielles usuelles telles que l'addition, la soustraction, le produit 
 * scalaire, le produit vectoriel, la normalisation et la réflexion.
 * </p>
 */
public class Vector extends AbstractVec3 {

    /**
     * Crée un vecteur 3D avec les coordonnées spécifiées.
     *
     * @param x composante X
     * @param y composante Y
     * @param z composante Z
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
    }

    /**
     * Additionne ce vecteur avec un autre vecteur.
     *
     * @param other le vecteur à additionner
     * @return un nouveau vecteur résultant de l'addition
     */
    @Override
    AbstractVec3 addition(AbstractVec3 other) {
        return new Vector(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    /**
     * Soustrait un autre vecteur à ce vecteur.
     *
     * @param other le vecteur à soustraire
     * @return un nouveau vecteur résultant de la soustraction
     */
    @Override
    AbstractVec3 soustraction(AbstractVec3 other) {
        return new Vector(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    /**
     * Multiplie ce vecteur par un scalaire.
     *
     * @param scalaire le facteur de multiplication
     * @return un nouveau vecteur résultant
     */
    @Override
    AbstractVec3 multiplication(double scalaire) {
        return new Vector(this.x * scalaire, this.y * scalaire, this.z * scalaire);
    }

    /**
     * Calcule le produit scalaire avec un autre vecteur.
     *
     * @param other le vecteur avec lequel calculer le produit scalaire
     * @return la valeur du produit scalaire
     */
    @Override
    double produitScalaire(AbstractVec3 other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    /**
     * Calcule le produit vectoriel avec un autre vecteur.
     *
     * @param other le vecteur avec lequel calculer le produit vectoriel
     * @return un nouveau vecteur résultant du produit vectoriel
     */
    @Override
    AbstractVec3 produitVectoriel(AbstractVec3 other) {
        return new Vector(this.y * other.z - this.z * other.y, this.z * other.x - this.x * other.z, this.x * other.y - this.y * other.x);
    }

    /**
     * Non implémenté pour la classe Vector.
     */
    @Override
    AbstractVec3 produitSchur(AbstractVec3 other) {
        throw new UnsupportedOperationException("Non supporté pour Vector");
    }

    /**
     * Calcule la longueur (norme) du vecteur.
     *
     * @return la longueur du vecteur
     */
    @Override
    double longueur() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    /**
     * Normalise le vecteur pour obtenir un vecteur unité.
     *
     * @return un nouveau vecteur normalisé
     */
    @Override
    AbstractVec3 normalisation() {
        double len = this.longueur();
        return new Vector(this.x / len, this.y / len, this.z / len);
    }

    /**
     * Compare ce vecteur à un autre objet pour vérifier l'égalité.
     *
     * @param obj l'objet à comparer
     * @return true si les deux vecteurs sont égaux (tolérance 1e-9), false sinon
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vector other = (Vector) obj;
        double epsilon = 1e-9;
        return Math.abs(this.x - other.x) < epsilon && Math.abs(this.y - other.y) < epsilon && Math.abs(this.z - other.z) < epsilon;
    }

    /**
     * Retourne une représentation textuelle du vecteur.
     *
     * @return chaîne contenant les composantes X, Y et Z
     */
    @Override
    public String toString() {
        return x + " " + y + " " + z;
    }

    /**
     * @return la composante X du vecteur
     */
    double getX() {
        return x;
    }

    /**
     * @return la composante Y du vecteur
     */
    double getY() {
        return y;
    }

    /**
     * @return la composante Z du vecteur
     */
    double getZ() {
        return z;
    }

    Vector reflect(Vector normal) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
