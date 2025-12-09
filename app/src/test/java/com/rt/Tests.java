package com.rt;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class Tests {

    private static final double EPSILON = 1e-9;

    @Test
    void testAddition() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(4, 5, 6);

        Vector result = (Vector) v1.addition(v2);

        assertEquals(new Vector(5, 7, 9), result);
    }

    @Test
    void testSoustraction() {
        Vector v1 = new Vector(4, 5, 6);
        Vector v2 = new Vector(1, 2, 3);

        Vector result = (Vector) v1.soustraction(v2);

        assertEquals(new Vector(3, 3, 3), result);
    }

    @Test
    void testMultiplicationScalaire() {
        Vector v = new Vector(1, -2, 3);
        Vector result = (Vector) v.multiplication(2);

        assertEquals(new Vector(2, -4, 6), result);
    }

    @Test
    void testProduitScalaire() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(4, -5, 6);

        double result = v1.produitScalaire(v2);

        assertEquals(12, result, EPSILON);
    }

    @Test
    void testProduitVectoriel() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(4, 5, 6);

        Vector result = (Vector) v1.produitVectoriel(v2);

        assertEquals(new Vector(-3, 6, -3), result);
    }

    @Test
    void testLongueur() {
        Vector v = new Vector(3, 4, 0);
        assertEquals(5, v.longueur(), EPSILON);
    }

    @Test
    void testNormalisation() {
        Vector v = new Vector(0, 3, 4);
        Vector n = (Vector) v.normalisation();

        assertEquals(0, n.getX(), EPSILON);
        assertEquals(0.6, n.getY(), EPSILON);
        assertEquals(0.8, n.getZ(), EPSILON);
    }


    @Test
    void testEquals() {
        Vector v1 = new Vector(1.000000001, 2, 3);
        Vector v2 = new Vector(1.000000002, 2, 3);

        assertTrue(v1.equals(v2));
    }

    @Test
    void testToString() {
        Vector v = new Vector(1, 2, 3);
        assertEquals("1.0 2.0 3.0", v.toString());
    }
}


