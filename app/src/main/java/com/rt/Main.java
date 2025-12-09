package com.rt;

import java.nio.file.Path;

/**
 * Point d'entrée principal du moteur de Ray Tracing.
 * <p>
 * Cette classe initialise la scène à partir d’un fichier,
 * crée un {@link RayTracer} et lance le rendu.
 * </p>
 */
public class Main {

    /**
     * Méthode principale du programme.
     *
     * @param args arguments de ligne de commande (non utilisés)
     * @throws Exception si une erreur survient lors du chargement de la scène
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Lancement du Ray Tracer!");

        // Chemin vers le fichier de description de la scène
        Path path = Path.of(args[0]);

        // Chargement et parsing de la scène
        Scene sc = new Scene(new SceneFileParser(path));

        // Création du RayTracer
        RayTracer rt = new RayTracer(sc);

        // Lancement du rendu
        rt.render();

        System.out.println("Fin du Ray Tracer !");
    }
}
