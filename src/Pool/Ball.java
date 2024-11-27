package Pool;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Ball {

    String colour;

    int animationCounter;
    int imageIndex;

    final double dragFactor = 0.99;

    public boolean isOnTable;

    public double posX;
    public double posY;
    public double velX;
    public double velY;
    public static double diameter;

    BufferedImage[] images;
    BufferedImage currentImage;

    static BufferedImage loadImage(String filename) {
        BufferedImage img = null;
        try{
            img = ImageIO.read(new File(filename));
        } catch (IOException e) {
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "An image failed to load: " + filename, "Error", JOptionPane.ERROR_MESSAGE);
        }
        //DEBUG
        //if (img == null) System.out.println("null");
        //else System.out.printf("w=%d, h=%d%n",img.getWidth(), img.getHeight());
        return img;
    }


    Ball(double x, double y, String colour) {
        this.isOnTable = true;
        this.colour = colour;
        diameter = 40;
        posX = x - (diameter/2);
        posY = y - (diameter/2);
        velX = 0;
        velY = 0;

        animationCounter = 0;
        images = new BufferedImage[4];

        if(colour.equals("RED")) {
            images[0] = loadImage("src/Assets/red/red1.png");
            images[1] = loadImage("src/Assets/red/red2.png");
            images[2] = loadImage("src/Assets/red/red3.png");
            images[3] = loadImage("src/Assets/red/red4.png");
        } else if(colour.equals("BLUE")) {
            images[0] = loadImage("src/Assets/blue/blue1.png");
            images[1] = loadImage("src/Assets/blue/blue2.png");
            images[2] = loadImage("src/Assets/blue/blue3.png");
            images[3] = loadImage("src/Assets/blue/blue4.png");
        }

        imageIndex = 0;
        currentImage = images[imageIndex];

    }

    public void updateCurrentImage() {
        currentImage = images[imageIndex];
    }

    public void update() {

        velX *= dragFactor;
        velY *= dragFactor;

        if (Math.abs(velX) < 0.1) {
            velX = 0;
        }
        if (Math.abs(velY) < 0.1) {
            velY = 0;
        }

        posX += velX;
        posY += velY;
    }



}
