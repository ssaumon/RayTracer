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

    Boolean equals(Point other) {
        // double epsilon = 1e - 9;
        return this.x == other.x && this.y == other.y && this.z == other.z;
    }
}
