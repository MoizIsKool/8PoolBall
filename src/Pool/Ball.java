package Pool;

import java.util.Random;

public class Ball {

    String colour;


    final double dragFactor = 0.99;

    public boolean isOnTable;

    Random random = new Random();

    public double posX;
    public double posY;
    public double velX;
    public double velY;
    public static double diameter;

    Ball(double x, double y, String colour) {
        this.isOnTable = true;
        this.colour = colour;
        diameter = 40;
        posX = x - (diameter/2);
        posY = y - (diameter/2);
        velX = 0;
        velY = 0;
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
