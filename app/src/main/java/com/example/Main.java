package com.example;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) {
        BufferedImage expectedImage = readInput(args[0]);
        BufferedImage actualImage = readInput(args[1]);

        if (expectedImage == null || actualImage == null){
            return;
        }
        
        int differences = ImageComparator.compareImages(expectedImage, actualImage);
        if (0 <= differences && differences < 1000){
            System.out.println("OK");
        }
        else {
            System.out.println("KO");
        }
        System.out.println("Les deux images diffèrent de " + differences + " pixels");
        
        writeDiff(expectedImage, actualImage);
    }

    private static BufferedImage readInput(String filename){
        try (var in = Files.newInputStream(Path.of(filename))) {
            return ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void writeDiff(BufferedImage expectedImage, BufferedImage actualImage){
        try (var out = Files.newOutputStream(Path.of("diff.png"))) {
            ImageIO.write(ImageComparator.generateDiffImage(expectedImage, actualImage), "png", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}