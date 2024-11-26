package Handlers;

import Pool.Ball;
import Pool.Hole;
import Pool.Table;

public class CollisionDetection {

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
                        offsetBalls(ball, otherBall, distance);
                        ballResolution(ball, otherBall);
                    }
                }
            }
        }
    }

    private void offsetBalls(Ball ball1, Ball ball2, double distance) {
        double overlap = Ball.diameter - distance;
        double dx = ((ball2.posX - ball1.posX) / distance)*overlap;
        double dy = ((ball2.posY - ball1.posY) / distance)*overlap;

        ball1.posX -= dx;
        ball1.posY -= dy;
        ball2.posX += dx;
        ball2.posY += dy;

    }

    private void ballResolution(Ball ball1, Ball ball2) {
        double numeratorX1 = (ball2.velX - ball1.velX) * (ball2.posX - ball1.posX);
        double numeratorSideX1 = (ball2.posX - ball1.posX);

        double numeratorX2 = (ball1.velX - ball2.velX) * (ball1.posX - ball2.posX);
        double numeratorSideX2 = (ball1.posX - ball2.posX);

        double denominatorX = Math.pow(ball2.posX - ball1.posX, 2);

        ball1.velX = ball1.velX + (numeratorX1/denominatorX) * numeratorSideX1;
        ball2.velX = ball2.velX + (numeratorX2/denominatorX) * numeratorSideX2;

        double numeratorY1 = (ball2.velY - ball1.velY) * (ball2.posY - ball1.posY);
        double numeratorSideY1 = (ball2.posY - ball1.posY);

        double numeratorY2 = (ball1.velY - ball2.velY) * (ball1.posY - ball2.posY);
        double numeratorSideY2 = (ball1.posY - ball2.posY);

        double denominatorY = Math.pow(ball2.posY - ball1.posY, 2);

        ball1.velY = ball1.velY + (numeratorY1/denominatorY) * numeratorSideY1;
        ball2.velY = ball2.velY + (numeratorY2/denominatorY) * numeratorSideY2;

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
