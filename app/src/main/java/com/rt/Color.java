package com.rt;

public class Color extends AbstractVec3 {
    public Color(double r, double g, double b) {
        super(r, g, b);
    }

    public Color() {
        super(0.0, 0.0, 0.0);
    }

    @Override
    AbstractVec3 addition(AbstractVec3 other) {
         double x1 = Math.min(this.x + other.x, 1.0);
         double y1 = Math.min(this.y + other.y, 1.0);
         double z1 = Math.min(this.z + other.z, 1.0);
         return new Color(x1, y1, z1);
    }

    @Override
    AbstractVec3 soustraction(AbstractVec3 other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    AbstractVec3 multiplication(double scalaire) {
        double x1 = Math.min(this.x * scalaire, 1.0);
        double y1 = Math.min(this.y * scalaire, 1.0);
        double z1 = Math.min(this.z * scalaire, 1.0);
        return new Color(x1, y1, z1);
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
        double x1 = Math.min(this.x * other.x, 1.0);
        double y1 = Math.min(this.y * other.y, 1.0);
        double z1 = Math.min(this.z * other.z, 1.0);
        return new Color(x1, y1, z1);
    }

    @Override
    double longueur() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    AbstractVec3 normalisation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    Boolean equals(Color other) {
        return this.x == other.x && this.y == other.y && this.z == other.z;
    }

    public int toRGB(){
        int red = (int) Math.round(x  * 255);
        int green = (int) Math.round(y * 255);
        int blue = (int) Math.round(z * 255);
        return (
        ((red & 0xff) << 16)
        + ((green & 0xff) << 8)
        + (blue & 0xff));
    }

}
