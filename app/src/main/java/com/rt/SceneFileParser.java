package com.rt;

import java.lang.reflect.Array;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap; 
import java.util.List;

public class SceneFileParser {
    ArrayList<String> lines;


    public SceneFileParser(Path filePath) throws Exception {
        ArrayList<String> allLines = new ArrayList<String>(Files.readAllLines(filePath));
        lines = new ArrayList<String>();
        for (String line : allLines) {
            line = line.trim();
            if (!line.isEmpty() && !line.startsWith("#")) {
                lines.add(line);
            }
        }
    }

    public int getWidth()throws Exception{
        if (lines.get(0).startsWith("size")){
            String[] tokens = lines.get(0).split(" ");
            return Integer.parseInt(tokens[1]);
        }
        throw new Exception("Size line not found");
    }

    public int getHeight()throws Exception{
        if (lines.get(0).startsWith("size")){
            String[] tokens = lines.get(0).split(" ");
            return Integer.parseInt(tokens[2]);
        }
        throw new Exception("Size line not found");
    }

    public String getOutput()throws Exception{
        if (lines.get(1).startsWith("output")){
            String[] tokens = lines.get(1).split(" ");
            return tokens[1];
        }
        throw new Exception("Output line not found");
    }

    public Camera getCamera() throws Exception{
        if (lines.get(2).startsWith("camera")){
            String[] tokens = lines.get(2).split(" ");
            Point lookFromPoint = new Point(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]));
            Point lookAtPoint = new Point(Double.parseDouble(tokens[4]), Double.parseDouble(tokens[5]), Double.parseDouble(tokens[6]));
            Vector up = new Vector(Double.parseDouble(tokens[7]), Double.parseDouble(tokens[8]), Double.parseDouble(tokens[9]));
            double fov = Double.parseDouble(tokens[10]);
            return new Camera(lookFromPoint, lookAtPoint, up, fov);
        }
        throw new Exception("Camera line not found");
    }

    public HashMap<String, ArrayList<Object>> getObjects() throws Exception{
        
        HashMap<String, ArrayList<Object>> objects = new HashMap<>();
        objects.put("lights", new ArrayList<>());
        objects.put("shapes", new ArrayList<>());
        objects.put("ambient", new ArrayList<>());
        Color diffuse = new Color();
        for (int i = 3; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] tokens = line.split(" ");
            switch (tokens[0]) {
                case "ambient":
                    if (objects.get("ambient").size() > 0) {
                        throw new Exception("Multiple ambient light definitions found");
                    }
                    Color ambient = new Color(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]));
                    
                    objects.get("ambient").add(ambient);
                    break;
                case "diffuse":
                    diffuse = new Color(Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]));
                    // diffuse.addition(ambiant);
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
                    objects.get("shapes").add(new Sphere(center, radius, diffuse));
                    break;
                // Add more cases for other shapes as needed
            }
        }
        return objects;
    }

    
}
