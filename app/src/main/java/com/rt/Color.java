package com.rt;

/**
 * Représente une couleur RGB normalisée, chaque composante étant comprise
 * entre 0 et 1.  
 * <p>
 * La classe {@code Color} hérite de {@link AbstractVec3} et utilise les
 * composantes du vecteur comme :
 * <ul>
 *     <li>x → rouge</li>
 *     <li>y → vert</li>
 *     <li>z → bleu</li>
 * </ul>
 * Les opérations vectorielles sont adaptées au domaine des couleurs :
 * limitation automatique à 1.0, produit de Schur pour l’éclairage, conversion
 * vers RGB 24 bits, etc.
 * </p>
 */
public class Color extends AbstractVec3 {

    /**
     * Construit une couleur à partir de trois composantes.
     *
     * @param r valeur rouge (0 à 1)
     * @param g valeur verte (0 à 1)
     * @param b valeur bleue (0 à 1)
     */
    public Color(double r, double g, double b) {
        super(r, g, b);
    }

    /**
     * Construit une couleur noire (0,0,0).
     */
    public Color() {
        super(0.0, 0.0, 0.0);
    }

    /**
     * Ajoute deux couleurs composante par composante.
     * <p>
     * Chaque composante est automatiquement bornée à 1.0.
     * </p>
     *
     * @param other autre couleur
     * @return nouvelle couleur correspondant à {@code this + other}
     */
    @Override
    AbstractVec3 addition(AbstractVec3 other) {
        double x1 = Math.min(this.x + other.x, 1.0);
        double y1 = Math.min(this.y + other.y, 1.0);
        double z1 = Math.min(this.z + other.z, 1.0);
        return new Color(x1, y1, z1);
    }

    /**
     * Non supporté pour le moment.
     *
     * @throws UnsupportedOperationException toujours levée
     */
    @Override
    AbstractVec3 soustraction(AbstractVec3 other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Multiplie chaque composante de la couleur par un scalaire.
     * <p>
     * Les valeurs sont bornées à 1.0.
     * </p>
     *
     * @param scalaire valeur à multiplier
     * @return nouvelle couleur résultante
     */
    @Override
    AbstractVec3 multiplication(double scalaire) {
        double x1 = Math.min(this.x * scalaire, 1.0);
        double y1 = Math.min(this.y * scalaire, 1.0);
        double z1 = Math.min(this.z * scalaire, 1.0);
        return new Color(x1, y1, z1);
    }

    /**
     * Produit scalaire non pertinent pour les couleurs.
     *
     * @throws UnsupportedOperationException toujours levée
     */
    @Override
    double produitScalaire(AbstractVec3 other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Produit vectoriel non pertinent pour les couleurs.
     *
     * @throws UnsupportedOperationException toujours levée
     */
    @Override
    AbstractVec3 produitVectoriel(AbstractVec3 other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Effectue un produit composante par composante (produit de Schur),
     * utile pour l'application d’éclairages (ex : Lambert, Phong).
     * <p>
     * Les composantes sont bornées à 1.0.
     * </p>
     *
     * @param other autre couleur
     * @return une nouvelle couleur
     */
    @Override
    AbstractVec3 produitSchur(AbstractVec3 other) {
        double x1 = Math.min(this.x * other.x, 1.0);
        double y1 = Math.min(this.y * other.y, 1.0);
        double z1 = Math.min(this.z * other.z, 1.0);
        return new Color(x1, y1, z1);
    }

    /**
     * Norme non pertinente pour les couleurs.
     *
     * @throws UnsupportedOperationException toujours levée
     */
    @Override
    double longueur() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Normalisation non pertinente pour les couleurs.
     *
     * @throws UnsupportedOperationException toujours levée
     */
    @Override
    AbstractVec3 normalisation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Compare deux couleurs avec une tolérance très faible.
     *
     * @param other autre couleur à comparer
     * @return vrai si les valeurs sont presque identiques (epsilon 1e-7)
     */
    Boolean equals(Color other) {
        double epsilon = 1e-7;
        return Math.abs(this.x - other.x) < epsilon && Math.abs(this.y - other.y) < epsilon && Math.abs(this.z - other.z) < epsilon;
    }

    /**
     * Convertit la couleur (composantes 0-1) en entier 24 bits RGB.
     *
     * @return une valeur entière codant la couleur (0xRRGGBB)
     */
    public int toRGB() {
        int red = (int) Math.round(x * 255);
        int green = (int) Math.round(y * 255);
        int blue = (int) Math.round(z * 255);

        return ((red & 0xff) << 16)
             | ((green & 0xff) << 8)
             | (blue & 0xff);
    }

    /**
     * Affiche la couleur sous forme de chaînes : "r g b".
     *
     * @return représentation textuelle de la couleur
     */
    @Override
    public String toString() {
        return x + " " + y + " " + z;
    }

}
