package com.rt;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class RayTracer {

    private int width;
    private int height;
    private Camera camera;
    private String output = "output.png";
    private Color ambient = new Color();
    private List<Light> lights = new ArrayList<>();
    private List<Shape> shapes = new ArrayList<>();
    private double fovr;
    private double pixelHeight;
    private double pixelWidth;
    private Orthonormal on;
    private Scene sc;


    public RayTracer(Scene sc) {
        camera = sc.getCamera();
        width = sc.getWidth();
        height = sc.getHeight();
        ambient = sc.getAmbient();
        lights = sc.getLights();
        shapes = sc.getShapes();
        output = sc.getOutput();
        fovr = (sc.getCamera().getFov()*Math.PI)/180;
        pixelHeight = Math.tan(fovr/2);
        pixelWidth = pixelHeight*width/height;
        on = new Orthonormal(sc);
        this.sc = sc;
    }

    Vector pixelDirection(int i,int j) throws Exception{
        double a = (pixelWidth*(i + 0.5 - width/2))/(width/2);
        double b = (pixelHeight*(j + 0.5 - height/2))/(height/2);
        Vector direction = (Vector) on.u.multiplication(a).addition(on.v.multiplication(b)).soustraction(on.w).normalisation();
        return direction;
    }

    Void render() throws Exception {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        System.out.println(sc.getAmbient());
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                Vector direction = pixelDirection(i, j);
                Ray ray = new Ray(camera.getLookFrom(), direction);
                Intersection intersectionPoint = sc.nearIntersection(ray);
                if (intersectionPoint != null) {
                    Color color = new Color();
                    for (Light light : lights){
                        if (light.getClass() == VectorLight.class){
                                VectorLight vectorLight = (VectorLight) light;
                                if (intersectionPoint.hasObstacle(sc, vectorLight)){
                                    continue;
                                }else{
                                    
                                color =(Color) color.addition(intersectionPoint.lambert(vectorLight));
                                }
                        }else if (light.getClass() == PointLight.class){
                            PointLight pointLight = (PointLight) light;
                            if (intersectionPoint.hasObstacle(sc, pointLight)){
                                continue;
                            }else{
                            color =(Color) color.addition(intersectionPoint.lambert(pointLight));
                            }
                        }
                        
                    }
                    image.setRGB(i, height - j - 1,color.toRGB());
                } else {
                    image.setRGB(i, height - j - 1, sc.getAmbient().toRGB());
                }
            }
        }
        try {
            File outputFile = new File(output);
            ImageIO.write(image, "png", outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; 
    }
    
}
