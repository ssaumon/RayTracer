package com.rt;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Parser pour les fichiers de description de scène utilisés par le ray tracer.
 * <p>
 * Ce parser lit un fichier texte décrivant :
 * <ul>
 *     <li>la taille de l'image</li>
 *     <li>la couleur ambiante</li>
 *     <li>les lumières (ponctuelles ou directionnelles)</li>
 *     <li>les formes géométriques (actuellement uniquement des sphères)</li>
 *     <li>les paramètres de la caméra</li>
 *     <li>le nom du fichier de sortie</li>
 * </ul>
 *
 * Il extrait ces objets et les stocke dans une structure (HashMap) qui sera utilisée
 * par le moteur de rendu.
 */
public class SceneFileParser {

    /** Toutes les lignes non vides et non commentées du fichier de scène. */
    ArrayList<String> lines;

    /**
     * Construit un parser et charge toutes les lignes pertinentes depuis le fichier donné.
     *
     * @param filePath chemin vers le fichier de description de scène
     * @throws Exception si le fichier ne peut pas être lu
     */
    public SceneFileParser(Path filePath) throws Exception {
        ArrayList<String> allLines = new ArrayList<>(Files.readAllLines(filePath));
        lines = new ArrayList<>();

        for (String line : allLines) {
            line = line.trim();
            if (!line.isEmpty() && !line.startsWith("#")) {
                lines.add(line);
            }
        }
    }

    /**
     * Extrait la largeur de l'image à partir de la première ligne de la description.
     *
     * @return la largeur de l'image rendue
     * @throws Exception si la ligne "size" est absente
     */
    public int getWidth() throws Exception {
        if (lines.get(0).startsWith("size")) {
            String[] tokens = lines.get(0).split(" ");
            return Integer.parseInt(tokens[1]);
        }
        throw new Exception("Ligne 'size' non trouvée");
    }

    /**
     * Extrait la hauteur de l'image à partir de la première ligne de la description.
     *
     * @return la hauteur de l'image rendue
     * @throws Exception si la ligne "size" est absente
     */
    public int getHeight() throws Exception {
        if (lines.get(0).startsWith("size")) {
            String[] tokens = lines.get(0).split(" ");
            return Integer.parseInt(tokens[2]);
        }
        throw new Exception("Ligne 'size' non trouvée");
    }

    /**
     * Analyse tous les objets de la scène après la ligne de taille.
     * <p>
     * Cette méthode extrait et organise les catégories suivantes :
     * <ul>
     *     <li><strong>lights</strong> : lumières ponctuelles ou directionnelles</li>
     *     <li><strong>shapes</strong> : objets sphériques</li>
     *     <li><strong>ambient</strong> : lumière ambiante globale</li>
     *     <li><strong>output</strong> : nom du fichier de sortie</li>
     *     <li><strong>camera</strong> : paramètres de la caméra</li>
     * </ul>
     *
     * La valeur de retour est une HashMap où chaque clé est associée à une liste
     * des objets analysés.
     *
     * @return une HashMap remplie décrivant l'ensemble de la scène
     * @throws Exception si des éléments obligatoires sont manquants ou si des doublons apparaissent
     */
    public HashMap<String, ArrayList<Object>> getObjects() throws Exception {
        HashMap<String, ArrayList<Object>> objects = new HashMap<>();
        objects.put("lights", new ArrayList<>());
        objects.put("shapes", new ArrayList<>());
        objects.put("ambient", new ArrayList<>());
        objects.put("output", new ArrayList<>());
        objects.put("camera", new ArrayList<>());
        objects.put("specular", new ArrayList<>());
        Color diffuse = new Color();
        Color specular = new Color();
        double shininess = 1.0;
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] tokens = line.split(" ");
            switch (tokens[0]) {
                case "ambient":
                    if (objects.get("ambient").size() > 0) {
                        throw new Exception("Définition multiple de la lumière ambiante trouvée");
                    }
                    Color ambient = new Color(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]));
                    objects.get("ambient").add(ambient);
                    break;

                case "diffuse":
                    diffuse = new Color(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]));
                    break;

                case "specular":
                    specular = new Color(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]));
                    break;

                case "point":
                    Point lightPoint = new Point(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]));
                    Color lightColor = new Color(Double.parseDouble(tokens[4]), Double.parseDouble(tokens[5]), Double.parseDouble(tokens[6]));
                    objects.get("lights").add(new PointLight(lightPoint, lightColor));
                    break;

                case "directional":
                    Vector lightVector = new Vector(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]));
                    Color vlightColor = new Color(Double.parseDouble(tokens[4]), Double.parseDouble(tokens[5]), Double.parseDouble(tokens[6]));
                    objects.get("lights").add(new VectorLight(lightVector, vlightColor));
                    break;

                case "sphere":
                    Point center = new Point(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]));
                    double radius = Double.parseDouble(tokens[4]);
                    objects.get("shapes").add(
                            new Sphere(center, radius, diffuse, shininess, specular)
                    );
                    break;

                case "output":
                    if (objects.get("output").size() > 0) {
                        throw new Exception("Définition multiple du fichier de sortie trouvée");
                    }
                    objects.get("output").add(tokens[1]);
                    break;

                case "camera":
                    if (objects.get("camera").size() > 0) {
                        throw new Exception("Définition multiple de la caméra trouvée");
                    }
                    Point lookFromPoint = new Point(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]));
                    Point lookAtPoint = new Point(Double.parseDouble(tokens[4]), Double.parseDouble(tokens[5]), Double.parseDouble(tokens[6]));
                    Vector up = new Vector(Double.parseDouble(tokens[7]), Double.parseDouble(tokens[8]), Double.parseDouble(tokens[9]));
                    double fov = Double.parseDouble(tokens[10]);
                    objects.get("camera").add(new Camera(lookFromPoint, lookAtPoint, up, fov));
                    break;

                case "shininess":
                    shininess = Double.parseDouble(tokens[1]);
                    break;
            }
        }

        // Valeur par défaut pour l'ambient
        if (objects.get("ambient").isEmpty()) {
            objects.get("ambient").add(new Color());
        }

        // Valeur par défaut pour le fichier de sortie
        if (objects.get("output").isEmpty()) {
            objects.get("output").add("output.png");
        }

        // La caméra est obligatoire
        if (objects.get("camera").isEmpty()) {
            throw new Exception("Aucune définition de caméra trouvée");
        }

        return objects;
    }
}
