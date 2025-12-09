package com.rt;
import java.lang.Math;

/**
 * Représente une base orthonormale construite à partir des informations
 * de la caméra dans la scène.  
 * <p>
 * Cette base est utilisée pour projeter des coordonnées d’écran (pixels)
 * dans l’espace 3D afin de calculer les directions de rayons du Ray Tracer.
 * Elle est généralement définie comme :
 * <ul>
 *     <li><b>w</b> : direction opposée à la direction de visée (lookFrom → lookAt)</li>
 *     <li><b>u</b> : vecteur horizontal, orthogonal à <b>w</b> et défini par le produit vectoriel de "up" et <b>w</b></li>
 *     <li><b>v</b> : vecteur vertical, orthogonal aux deux autres</li>
 * </ul>
 * </p>
 */
public class Orthonormal {

    /** Axe horizontal de la base (équivalent à la droite de la caméra). */
    Vector u;

    /** Axe vertical de la base (équivalent au haut de la caméra). */
    Vector v;

    /** Axe avant/arrière de la base (direction opposée au regard de la caméra). */
    Vector w;

    /**
     * Construit une base orthonormale à partir de la scène.
     * <p>
     * Elle utilise les paramètres de la caméra :
     * <ul>
     *     <li>lookFrom : position de la caméra</li>
     *     <li>lookAt : point visé</li>
     *     <li>up : vecteur vertical de référence</li>
     * </ul>
     * </p>
     *
     * @param sc la scène contenant la caméra à utiliser
     */
    public Orthonormal(Scene sc) {
        Point lookFrom = sc.getCamera().getLookFrom();
        Point lookAt = sc.getCamera().getLookAt();
        Vector up = sc.getCamera().up();

        // w = direction arrière
        w = (Vector) lookFrom.soustraction(lookAt).normalisation();

        // u = direction horizontale (right)
        u = (Vector) up.produitVectoriel(w).normalisation();

        // v = direction verticale (up)
        v = (Vector) w.produitVectoriel(u);
    }
}
