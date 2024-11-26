package Pool;

import Handlers.MouseHandler;

import java.awt.*;

public class Cue {
    boolean isShooting = false;
    public boolean isPullingBack = false;
    public MouseHandler mouseHandler = new MouseHandler();

    double velocityDownscaleFactor = 10;

    public void determineShooting(Ball whiteBall) {
        double distance = Math.sqrt(Math.pow((whiteBall.posX + Ball.diameter/2) - mouseHandler.x, 2) + Math.pow((whiteBall.posY+Ball.diameter/2) - mouseHandler.y, 2));
        if(distance < Ball.diameter/2 && !isShooting) {
            if(mouseHandler.pressed) {
                isPullingBack = true;
            }
        }
    }

    public void shoot(Ball whiteBall) {
        if(isPullingBack) {
            if(mouseHandler.released) {
                double xDistance = (whiteBall.posX + Ball.diameter / 2) - mouseHandler.x;
                double yDistance = (whiteBall.posY + Ball.diameter / 2) - mouseHandler.y;

                whiteBall.velX = xDistance/velocityDownscaleFactor;
                whiteBall.velY = yDistance/velocityDownscaleFactor;

                isShooting = false;
                isPullingBack = false;
            }
        }
    }

    public void drawLine(Ball whiteBall, Graphics2D g2) {
        g2.setStroke(new BasicStroke(5));
        g2.setColor(Color.ORANGE);
        g2.drawLine((int)whiteBall.posX+(int)Ball.diameter/2, (int)whiteBall.posY+(int)Ball.diameter/2, mouseHandler.x, mouseHandler.y);
    }

}
