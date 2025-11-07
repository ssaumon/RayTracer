package com.example;

import java.awt.image.BufferedImage;

public class ImageComparator {
    public static int compareImages(BufferedImage expectedImage, BufferedImage actualImage) {
        if (areSameSize(expectedImage, actualImage)) {
            int differences = processNumberDifferences(expectedImage, actualImage);
            return differences;
        }
        return -1;
    }

    public static BufferedImage generateDiffImage(BufferedImage expectedImage, BufferedImage actualImage) {
        BufferedImage diff = new BufferedImage(expectedImage.getWidth(), expectedImage.getHeight(),
                expectedImage.getType());

        for (int y = 0; y < expectedImage.getHeight(); y++) {
            for (int x = 0; x < expectedImage.getWidth(); x++) {
                if (expectedImage.getRGB(x, y) != actualImage.getRGB(x, y)) {
                    diff.setRGB(x, y, expectedImage.getRGB(x, y) - actualImage.getRGB(x, y));
                }
            }
        }
        return diff;
    }

    private static Boolean areSameSize(BufferedImage expectedImage, BufferedImage actualImage) {
        return (expectedImage.getWidth() == actualImage.getWidth()
                && expectedImage.getHeight() == actualImage.getHeight());
    }

    private static int processNumberDifferences(BufferedImage expectedImage, BufferedImage actualImage) {
        int nbDifferences = 0;
        for (int y = 0; y < expectedImage.getHeight(); y++) {
            for (int x = 0; x < expectedImage.getWidth(); x++) {
                if (expectedImage.getRGB(x, y) != actualImage.getRGB(x, y)) {
                    nbDifferences++;
                }
            }
        }
        return nbDifferences;
    }
}
