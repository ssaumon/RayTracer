package com.rt;

/**
 * Représente une caméra dans la scène 3D.
 * <p>
 * La caméra définit le point d'origine des rayons, la direction de vision, 
 * l'orientation verticale et le champ de vision (FOV) utilisé par le ray tracer.
 * </p>
 *
 * <p>Une caméra possède :
 * <ul>
 *   <li>Un point de vue {@code lookFrom} (origine des rayons).</li>
 *   <li>Un point cible {@code lookAt} (direction vers laquelle la caméra regarde).</li>
 *   <li>Un vecteur {@code up} définissant l'orientation verticale.</li>
 *   <li>Un champ de vision {@code fov} exprimé en degrés.</li>
 * </ul>
 * </p>
 */
public class Camera {

    /** Point de vue de la caméra, origine des rayons. */
    Point lookFrom;

    /** Point cible vers lequel la caméra regarde. */
    Point lookAt;

    /** Vecteur définissant l'orientation verticale de la caméra. */
    Vector up;

    /** Champ de vision (Field of View) de la caméra en degrés. */
    double fov;

    /**
     * Crée une caméra avec les paramètres spécifiés.
     *
     * @param lookFrom le point d'origine des rayons
     * @param lookAt le point cible vers lequel la caméra regarde
     * @param up le vecteur vertical définissant l'orientation de la caméra
     * @param fov le champ de vision en degrés
     */
    public Camera(Point lookFrom, Point lookAt, Vector up, double fov) {
        this.lookFrom = lookFrom;
        this.lookAt = lookAt;
        this.up = up;
        this.fov = fov;
    }

    /**
     * Retourne une représentation textuelle de la caméra.
     *
     * @return chaîne de caractères contenant lookFrom, lookAt, up et fov
     */
    @Override
    public String toString() {
        return lookFrom.toString() + "\n" + lookAt.toString() + "\n" + up.toString() + "\n" + fov;
    }

    /**
     * @return le point d'origine des rayons (lookFrom)
     */
    Point getLookFrom() {
        return lookFrom;
    }

    /**
     * @return le point cible vers lequel la caméra regarde (lookAt)
     */
    Point getLookAt() {
        return lookAt;
    }

    /**
     * @return le vecteur vertical de la caméra (up)
     */
    Vector up() {
        return up;
    }

    /**
     * @return le champ de vision de la caméra en degrés
     */
    double getFov() {
        return fov;
    }
}
