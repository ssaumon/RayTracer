package com.rt;

public class Vector extends AbstractVec3 {
    public Vector(double x, double y, double z) {
        super(x, y, z);
    }

    @Override
    AbstractVec3 addition(AbstractVec3 other) {
        return new Vector(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    @Override
    AbstractVec3 soustraction(AbstractVec3 other) {
        return new Vector(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    @Override
    AbstractVec3 multiplication(double scalaire) {
        return new Vector(this.x * scalaire, this.y * scalaire, this.z * scalaire);
    }

    @Override
    double produitScalaire(AbstractVec3 other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    @Override
    AbstractVec3 produitVectoriel(AbstractVec3 other) {
        return new Vector(
            this.y * other.z - this.z * other.y,
            this.z * other.x - this.x * other.z,
            this.x * other.y - this.y * other.x
        );
    }

    @Override
    AbstractVec3 produitSchur(AbstractVec3 other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    double longueur() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    @Override
    AbstractVec3 normalisation() {
        double len = this.longueur();
        return new Vector(this.x / len, this.y / len, this.z / len);
    }

    Boolean equals(Vector other) {
        double epsilon = 1e-9;
        if(Math.abs(this.x - other.x) < epsilon && Math.abs(this.y - other.y) < epsilon && Math.abs(this.z - other.z) < epsilon) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return x + " " + y + " " + z;
    }

    double getX(){
        return x;
    }
    double getY(){
        return y;
    }
    double getZ(){
        return z;
    }

}
