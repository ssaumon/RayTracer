package com.rt;

/**
 * Représente un point dans l'espace 3D.
 * <p>
 * Un point est une position dans l'espace, et cette classe hérite de {@link AbstractVec3}.
 * Elle fournit des opérations utiles pour la géométrie 3D, comme l'addition d'un vecteur, 
 * la soustraction avec un autre point, et le calcul de distance.
 * </p>
 */
public class Point extends AbstractVec3 {

    /**
     * Crée un point 3D avec les coordonnées spécifiées.
     *
     * @param x coordonnée X
     * @param y coordonnée Y
     * @param z coordonnée Z
     */
    public Point(double x, double y, double z) {
        super(x, y, z);
    }

    /**
     * Additionne ce point avec un vecteur pour obtenir un nouveau point.
     *
     * @param other le vecteur à ajouter
     * @return un nouveau point résultant de l'addition
     */
    @Override
    AbstractVec3 addition(AbstractVec3 other) {
        Vector v = (Vector) other;
        return new Point(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    /**
     * Soustrait un autre point à ce point pour obtenir un vecteur.
     *
     * @param other le point à soustraire
     * @return un vecteur allant de l'autre point vers ce point
     */
    @Override
    AbstractVec3 soustraction(AbstractVec3 other) {
        Point p = (Point) other;
        return new Vector(this.x - p.x, this.y - p.y, this.z - p.z);
    }

    /**
     * Multiplie ce point par un scalaire.
     *
     * @param scalaire le facteur de multiplication
     * @return un nouveau point multiplié par le scalaire
     */
    @Override
    AbstractVec3 multiplication(double scalaire) {
        return new Point(this.x * scalaire, this.y * scalaire, this.z * scalaire);
    }

    /**
     * Non supporté pour la classe Point.
     */
    @Override
    double produitScalaire(AbstractVec3 other) {
        throw new UnsupportedOperationException("Non supporté pour Point");
    }

    /**
     * Non supporté pour la classe Point.
     */
    @Override
    AbstractVec3 produitVectoriel(AbstractVec3 other) {
        throw new UnsupportedOperationException("Non supporté pour Point");
    }

    /**
     * Non supporté pour la classe Point.
     */
    @Override
    AbstractVec3 produitSchur(AbstractVec3 other) {
        throw new UnsupportedOperationException("Non supporté pour Point");
    }

    /**
     * Non supporté pour la classe Point.
     */
    @Override
    double longueur() {
        throw new UnsupportedOperationException("Non supporté pour Point");
    }

    /**
     * Non supporté pour la classe Point.
     */
    @Override
    AbstractVec3 normalisation() {
        throw new UnsupportedOperationException("Non supporté pour Point");
    }

    /**
     * Calcule la distance euclidienne entre ce point et un autre point.
     *
     * @param other l'autre point
     * @return la distance entre les deux points
     */
    double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2) + Math.pow(this.z - other.z, 2));
    }

    /**
     * Vérifie si ce point est égal à un autre objet.
     *
     * @param obj l'objet à comparer
     * @return true si les coordonnées sont identiques (tolérance 1e-9), false sinon
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point other = (Point) obj;
        double epsilon = 1e-9;
        return Math.abs(this.x - other.x) < epsilon && Math.abs(this.y - other.y) < epsilon && Math.abs(this.z - other.z) < epsilon;
    }

    /**
     * Retourne une représentation textuelle du point.
     *
     * @return chaîne contenant les coordonnées X, Y et Z
     */
    @Override
    public String toString() {
        return x + " " + y + " " + z;
    }

    /** @return la coordonnée X du point */
    double getX() {
        return x;
    }

    /** @return la coordonnée Y du point */
    double getY() {
        return y;
    }

    /** @return la coordonnée Z du point */
    double getZ() {
        return z;
    }
}
