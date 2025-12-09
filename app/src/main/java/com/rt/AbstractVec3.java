package com.rt;

/**
 * Classe abstraite représentant un vecteur ou un point en 3 dimensions.
 * <p>
 * Cette classe sert de base pour les classes concrètes manipulant des objets
 * 3D tels que {@code Vector} ou {@code Point}.  
 * Elle définit les opérations vectorielles essentielles :
 * addition, soustraction, produit scalaire, produit vectoriel, etc.
 * </p>
 */
public abstract class AbstractVec3 {

    /** Coordonnée X du vecteur. */
    double x;

    /** Coordonnée Y du vecteur. */
    double y;

    /** Coordonnée Z du vecteur. */
    double z;

    /**
     * Construit un vecteur 3D avec les coordonnées spécifiées.
     *
     * @param x coordonnée X
     * @param y coordonnée Y
     * @param z coordonnée Z
     */
    public AbstractVec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Additionne ce vecteur avec un autre.
     *
     * @param other autre vecteur
     * @return un nouveau vecteur résultat de l’addition
     * @throws Exception si l’implémentation ne peut réaliser l’opération
     */
    abstract AbstractVec3 addition(AbstractVec3 other) throws Exception;

    /**
     * Soustrait un vecteur à ce vecteur.
     *
     * @param other vecteur à soustraire
     * @return le résultat de {@code this - other}
     */
    abstract AbstractVec3 soustraction(AbstractVec3 other);

    /**
     * Multiplie ce vecteur par un scalaire.
     *
     * @param scalaire valeur scalaire
     * @return un nouveau vecteur multiplié par {@code scalaire}
     */
    abstract AbstractVec3 multiplication(double scalaire);

    /**
     * Calcule le produit scalaire entre ce vecteur et un autre.
     *
     * @param other autre vecteur
     * @return le produit scalaire des deux vecteurs
     */
    abstract double produitScalaire(AbstractVec3 other);

    /**
     * Calcule le produit vectoriel entre ce vecteur et un autre.
     *
     * @param other autre vecteur
     * @return vecteur orthogonal résultant du produit vectoriel
     */
    abstract AbstractVec3 produitVectoriel(AbstractVec3 other);

    /**
     * Produit de Schur (multiplication composante par composante).
     *
     * @param other autre vecteur
     * @return un vecteur où chaque composante résulte de {@code this[i] * other[i]}
     */
    abstract AbstractVec3 produitSchur(AbstractVec3 other);

    /**
     * Calcule la longueur (norme Euclidienne) du vecteur.
     *
     * @return la norme du vecteur
     */
    abstract double longueur();

    /**
     * Retourne une version normalisée du vecteur.
     *
     * @return un nouveau vecteur de même direction mais de norme 1
     */
    abstract AbstractVec3 normalisation();
}
