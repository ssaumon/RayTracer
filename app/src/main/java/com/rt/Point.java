package com.rt;

public class Point extends AbstractVec3 {
    public Point(double x, double y, double z) {
        super(x, y, z);
    }

    @Override
    AbstractVec3 addition(AbstractVec3 other) {
        Vector v = (Vector) other; 
        return new Point(this.x + v.x, this.y + v.y, this.z + v.z);
    }

    @Override
    AbstractVec3 soustraction(AbstractVec3 other) {
        Point p = (Point) other;
        return new Vector(this.x - p.x, this.y - p.y, this.z - p.z);
    }

    @Override
    AbstractVec3 multiplication(double scalaire) {
        return new Point(this.x * scalaire, this.y * scalaire, this.z * scalaire);
    }

    @Override
    double produitScalaire(AbstractVec3 other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    AbstractVec3 produitVectoriel(AbstractVec3 other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    AbstractVec3 produitSchur(AbstractVec3 other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    double longueur() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    AbstractVec3 normalisation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2) + Math.pow(this.z - other.z, 2));
    }

    Boolean equals(Point other) {
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
