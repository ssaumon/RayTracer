package com.rt;
import java.lang.Math;

public class Orthonormal {
    Vector u;
    Vector v;
    Vector w;


    public Orthonormal(Scene sc) {
        Point lookFrom = sc.getCamera().getLookFrom();
        Point lookAt = sc.getCamera().getLookAt();
        Vector up = sc.getCamera().up();

        w = (Vector) lookFrom.soustraction(lookAt).normalisation();
        u = (Vector) up.produitVectoriel(w).normalisation();
        v = (Vector) w.produitVectoriel(u);
    }

    

    
}
