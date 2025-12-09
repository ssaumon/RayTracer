package com.rt;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente une scène 3D à rendre par le ray tracer.
 * <p>
 * Une scène contient :
 * <ul>
 *     <li>la résolution de l'image (largeur/hauteur)</li>
 *     <li>une caméra</li>
 *     <li>l'éclairage ambiant</li>
 *     <li>une liste de lumières</li>
 *     <li>une liste de formes géométriques</li>
 *     <li>un nom de fichier de sortie</li>
 * </ul>
 * <p>
 * La scène est entièrement construite à partir d'un {@link SceneFileParser}, 
 * qui lit et interprète la configuration depuis un fichier de description de scène.
 */
public class Scene {

    /** Largeur de l'image de sortie en pixels. */
    private int width;

    /** Hauteur de l'image de sortie en pixels. */
    private int height;

    /** La caméra utilisée pour générer les rayons pour chaque pixel. */
    private Camera camera;

    /** Nom du fichier de sortie (image PNG). */
    private String output = "output.png";

    /** Couleur ambiante globale de la scène. */
    private Color ambient = new Color();

    /** Liste des lumières affectant l'illumination de la scène. */
    private List<Light> lights = new ArrayList<>();

    /** Liste des formes géométriques présentes dans la scène. */
    private List<Shape> shapes = new ArrayList<>();

    /**
     * Crée une scène en lisant tous les éléments produits par un {@link SceneFileParser}.
     *
     * @param parser le parser contenant tous les objets extraits de la scène.
     * @throws Exception si des éléments obligatoires sont manquants ou mal formatés.
     */
    Scene(SceneFileParser parser) throws Exception {
        this.width = parser.getWidth();
        this.height = parser.getHeight();
        this.output = (String) parser.getObjects().get("output").get(0);
        this.lights = (List<Light>) (Object) parser.getObjects().get("lights");
        this.shapes = (List<Shape>) (Object) parser.getObjects().get("shapes");
        this.ambient = (Color) (Object) parser.getObjects().get("ambient").get(0);
        this.camera = (Camera) parser.getObjects().get("camera").get(0);
    }

    /**
     * @return la largeur de l'image de sortie.
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return la hauteur de l'image de sortie.
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return le nom du fichier de sortie (PNG).
     */
    public String getOutput() {
        return output;
    }

    /**
     * @return la couleur ambiante utilisée pour l'illumination globale.
     */
    public Color getAmbient() {
        return ambient;
    }

    /**
     * @return la liste des lumières présentes dans la scène.
     */
    public List<Light> getLights() {
        return lights;
    }

    /**
     * @return la liste des formes géométriques présentes dans la scène.
     */
    public List<Shape> getShapes() {
        return shapes;
    }

    /**
     * @return la caméra depuis laquelle les rayons sont émis.
     */
    public Camera getCamera() {
        return camera;
    }

    /**
     * Calcule l'intersection la plus proche entre un rayon et toutes les formes de la scène.
     *
     * @param ray le rayon à tester contre toutes les formes.
     * @return l'intersection la plus proche {@link Intersection}, ou {@code null} 
     *         si aucune forme n'est intersectée.
     */
    Intersection nearIntersection(Ray ray) {
        Point nearestPoint = null;
        double minDistance = Double.MAX_VALUE;
        Shape nearestShape = null;
        double eps = 1e-7;

        for (Shape shape : shapes) {
            Intersection inter = shape.intersection(ray);
            if (inter != null) {
                Point p = inter.getPoint();
                double dist = ray.origin.distance(p);

                // Ignore les auto-intersections ou intersections trop proches
                if (dist > eps && dist < minDistance) {
                    minDistance = dist;
                    nearestPoint = p;
                    nearestShape = shape;
                }
            }
        }

        if (nearestPoint == null) {
            return null;
        }

        return new Intersection(ray, nearestPoint, nearestShape);
    }
}
