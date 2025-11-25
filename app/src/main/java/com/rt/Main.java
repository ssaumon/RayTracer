package com.rt;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, Ray Tracer!");
        Path path = Path.of("scenes/jalon2/test2.scene");
        Scene sc = new Scene(new SceneFileParser(path)); 
        System.out.println(sc.getCamera());
        System.out.println(sc.getAmbient());
        System.out.println(sc.getLights().size());   
    }

}
