package com.rt;

public abstract class AbstractVec3 {
    double x,y,z;

    public AbstractVec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    abstract AbstractVec3 addition(AbstractVec3 other);
    abstract AbstractVec3 soustraction(AbstractVec3 other);
    abstract AbstractVec3 multiplication(double scalaire);
    abstract double produitScalaire(AbstractVec3 other);
    abstract AbstractVec3 produitVectoriel(AbstractVec3 other);
    abstract AbstractVec3 produitSchur(AbstractVec3 other);
    abstract double longueur();
    abstract AbstractVec3 normalisation();


}

