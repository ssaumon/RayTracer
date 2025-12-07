package com.rt;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, Ray Tracer!");
        Path path = Path.of("scenes/jalon5/tp51-diffuse.test");
        Scene sc = new Scene(new SceneFileParser(path));
        RayTracer rt = new RayTracer(sc);
        rt.render();
        System.out.println(sc.getCamera());
        System.out.println(sc.getAmbient());
        System.out.println(sc.getLights().size());
        
         path = Path.of("scenes/jalon5/tp51-specular.test");
         sc = new Scene(new SceneFileParser(path));
         rt = new RayTracer(sc);
        rt.render();
    }

}
