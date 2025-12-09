package com.rt;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * Classe principale responsable du rendu Ray Tracing.
 * <p>
 * Le {@code RayTracer} calcule la couleur de chaque pixel en lançant des rayons
 * depuis la caméra, détecte les intersections avec les objets de la scène,
 * applique l'éclairage (ambiant, Lambert, Phong), puis génère une image finale.
 * </p>
 */
public class RayTracer {

    /** Largeur de l’image de sortie en pixels. */
    private int width;
    /** Hauteur de l’image de sortie en pixels. */
    private int height;
    /** Caméra utilisée pour générer les rayons. */
    private Camera camera;
    /** Nom du fichier de sortie. */
    private String output = "output.png";
    /** Couleur ambiante globale de la scène. */
    private Color ambient = new Color();
    /** Liste des sources lumineuses de la scène. */
    private List<Light> lights = new ArrayList<>();
    /** Liste des objets (formes) de la scène. */
    private List<Shape> shapes = new ArrayList<>();
    /** Champ de vision en radians. */
    private double fovr;
    /** Hauteur d’un pixel dans l’espace normalisé. */
    private double pixelHeight;
    /** Largeur d’un pixel dans l’espace normalisé. */
    private double pixelWidth;
    /** Base orthonormale liée à la caméra. */
    private Orthonormal on;
    /** Scène complète contenant caméra, objets et lumières. */
    private Scene sc;

    /**
     * Construit un RayTracer à partir d'une scène.
     *
     * @param sc scène à rendre
     */
    public RayTracer(Scene sc) {
        camera = sc.getCamera();
        width = sc.getWidth();
        height = sc.getHeight();
        ambient = sc.getAmbient();
        lights = sc.getLights();
        shapes = sc.getShapes();
        output = sc.getOutput();
        fovr = (sc.getCamera().getFov() * Math.PI) / 180;
        pixelHeight = Math.tan(fovr / 2);
        pixelWidth = pixelHeight * width / height;
        on = new Orthonormal(sc);
        this.sc = sc;
    }

    /**
     * Calcule la direction d’un rayon partant de la caméra vers un pixel donné.
     *
     * @param i coordonnée x du pixel
     * @param j coordonnée y du pixel
     * @return un vecteur normalisé représentant la direction du rayon
     * @throws Exception en cas d’erreur de manipulation vectorielle
     */
    Vector pixelDirection(int i, int j) throws Exception {
        double a = (pixelWidth * (i + 0.5 - width / 2)) / (width / 2);
        double b = (pixelHeight * (j + 0.5 - height / 2)) / (height / 2);
        Vector direction = (Vector) on.u.multiplication(a).addition(on.v.multiplication(b)).soustraction(on.w).normalisation();
        return direction;
    }

    /**
     * Lance le processus de rendu complet.
     * <p>
     * Pour chaque pixel :
     * <ul>
     *     <li>Un rayon est généré.</li>
     *     <li>La première intersection avec un objet est calculée.</li>
     *     <li>L’éclairage ambiant, Lambert et Phong est appliqué.</li>
     *     <li>Le pixel reçoit sa couleur finale.</li>
     * </ul>
     * L’image finale est ensuite sauvegardée au format PNG.
     * </p>
     *
     * @return {@code null} (méthode utilisée comme procédure)
     * @throws Exception en cas d'erreur de calcul ou de fichier
     */
    Void render() throws Exception {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        System.out.println(sc.getAmbient());

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {

                Vector direction = pixelDirection(i, j);
                Ray ray = new Ray(camera.getLookFrom(), direction);
                Intersection intersectionPoint = sc.nearIntersection(ray);

                if (intersectionPoint != null) {

                    Color color = (Color) sc.getAmbient().produitSchur(intersectionPoint.getShape().color);

                    // Application des différentes lumières
                    for (Light light : lights) {

                        if (light.getClass() == VectorLight.class) {
                            VectorLight vectorLight = (VectorLight) light;

                            if (intersectionPoint.hasObstacle(sc, vectorLight)) {
                                continue;
                            }

                            color = (Color) color.addition(intersectionPoint.lambert(vectorLight));
                            color = (Color) color.addition(intersectionPoint.phong(vectorLight, camera));

                        } else if (light.getClass() == PointLight.class) {
                            PointLight pointLight = (PointLight) light;

                            if (intersectionPoint.hasObstacle(sc, pointLight)) {
                                continue;
                            }

                            color = (Color) color.addition(intersectionPoint.lambert(pointLight));
                            color = (Color) color.addition(intersectionPoint.phong(pointLight, camera));
                        }
                    }

                    image.setRGB(i, height - j - 1, color.toRGB());

                } else {
                    // Pixel sans intersection : couleur ambiante
                    image.setRGB(i, height - j - 1, sc.getAmbient().toRGB());
                }
            }
        }

        // Sauvegarde de l'image
        try {
            File outputFile = new File(output);
            ImageIO.write(image, "png", outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
