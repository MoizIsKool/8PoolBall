package Handlers;

import Pool.Ball;
import Pool.Hole;
import Pool.Table;

public class CollisionHandler {

    public void checkWallCollision(Table table) {
        for (Ball ball : table.balls) {
            if(ball != null) {
                if (ball.posX < table.screenX) {
                    ball.velX = -ball.velX;
                    ball.posX = table.screenX;
                }
                if (ball.posX + Ball.diameter > table.screenX + table.width) {
                    ball.velX = -ball.velX;
                    ball.posX = table.screenX + table.width - Ball.diameter;
                }
                if (ball.posY < table.screenY) {
                    ball.velY = -ball.velY;
                    ball.posY = table.screenY;
                }
                if (ball.posY + Ball.diameter > table.screenY + table.height) {
                    ball.velY = -ball.velY;
                    ball.posY = table.screenY + table.height - Ball.diameter;
                }
            }
        }
    }

    public void checkBallBallCollision(Table table) {
        for (int i = 0; i < table.balls.length; i++) {
            for (int j = i + 1; j < table.balls.length; j++) {
                Ball ball = table.balls[i];
                Ball otherBall = table.balls[j];
                if(ball != otherBall && ball.isOnTable && otherBall.isOnTable) {
                    double distance = Math.sqrt(Math.pow(ball.posX - otherBall.posX, 2) + Math.pow(ball.posY - otherBall.posY, 2));
                    if(distance < Ball.diameter) {
                        ballResolution(ball, otherBall);
                    }
                }
            }
        }
    }

    public void ballResolution (Ball b1, Ball b2) {

        offset(b2, b1);

        double xDistance = (b1.posX + Ball.diameter/2) - (b2.posX + Ball.diameter/2);
        double yDistance = (b1.posY + Ball.diameter/2) - (b2.posY + Ball.diameter/2);
        double xVelDiff = b2.velX - b1.velX;
        double yVelDiff = b2.velY - b1.velY;

        double distanceSquared = xDistance * xDistance + yDistance * yDistance;
        double dotProduct = xDistance * xVelDiff + yDistance * yVelDiff;

        if (dotProduct > 0) {
            double colScale = dotProduct / distanceSquared;
            double xComponent = xDistance * colScale;
            double yComponent = yDistance * colScale;

            b1.velX += xComponent;
            b1.velY += yComponent;
            b2.velX -= xComponent;
            b2.velY -= yComponent;
        }
    }

    private void offset(Ball b1, Ball b2) {
        double overlap = Ball.diameter/2 + Ball.diameter/2 - distance(b1, b2);

        double theta = Math.atan2(((b1.posY + Ball.diameter/2) - (b2.posY + Ball.diameter/2)), ((b1.posX + Ball.diameter/2) - (b2.posX + Ball.diameter/2)));
        b2.posX -= (int) Math.round(overlap * Math.cos(theta));
        b2.posY -= (int) Math.round(overlap * Math.sin(theta));
    }

    private static double distance (Ball b1, Ball b2){
        return Math.sqrt(Math.pow((b2.posX+Ball.diameter/2) - (b1.posX + Ball.diameter/2), 2) + Math.pow((b2.posY+Ball.diameter/2) - (b1.posY + Ball.diameter/2), 2));
    }

    public void checkBallInHole(Hole[] holes, Ball[] balls) {
        for(Hole hole : holes) {
            for(Ball ball : balls) {
                if(hole.isBallInHole(ball)) {
                    ball.isOnTable = false;
                }
            }
        }
    }

}
