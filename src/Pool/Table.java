package Pool;

import java.awt.*;

public class Table {

    public int width;
    public int height;

    final int drawOffsetTable = 20;
    final int drawOffsetBall = 600;

    public int screenX;
    public int screenY;

    public Hole[] holes;
    public Ball[] balls;

    public Table(int screenX, int screenY, int width, int height) {
        this.screenX = screenX;
        this.screenY = screenY;
        this.width = width;
        this.height = height;
        holes = new Hole[6];
        addHoles();
        balls = new Ball[11];
        addBalls();
    }

    public void addBalls() {
        balls[0] = new Ball(screenX + drawOffsetBall, screenY + height/2, "BLUE");

        balls[1] = new Ball(screenX + drawOffsetBall + Ball.diameter, screenY + height/2 - Ball.diameter, "RED");
        balls[2] = new Ball(screenX + drawOffsetBall + Ball.diameter, screenY + height/2 + Ball.diameter, "RED");

        balls[3] = new Ball(screenX + drawOffsetBall + Ball.diameter*2, screenY + height/2, "RED");
        balls[4] = new Ball(screenX + drawOffsetBall + Ball.diameter*2, screenY + height/2 - Ball.diameter*2, "BLUE");
        balls[5] = new Ball(screenX + drawOffsetBall + Ball.diameter*2, screenY + height/2 + Ball.diameter*2, "BLUE");


        balls[6] = new Ball(screenX + drawOffsetBall + Ball.diameter*3, screenY + height/2 + Ball.diameter*3, "RED");

        balls[7] = new Ball(screenX + drawOffsetBall + Ball.diameter*3, screenY + height/2 - Ball.diameter, "BLUE");
        balls[8] = new Ball(screenX + drawOffsetBall + Ball.diameter*3, screenY + height/2 + Ball.diameter, "BLUE");

        balls[9] = new Ball(screenX + drawOffsetBall + Ball.diameter*3, screenY + height/2 - Ball.diameter*3, "RED");

        balls[10] = new Ball(screenX + drawOffsetBall - Ball.diameter*7, screenY + height/2, "WHITE");


    }

    public void resetWhite() {

        balls[10].velX = 0;
        balls[10].velY = 0;

        balls[10].posX = screenX + drawOffsetBall - Ball.diameter*7;
        balls[10].posY = screenY + height/2;
        balls[10].isOnTable = true;

    }

    public void addHoles() {
        holes[0] = new Hole(screenX, screenY);
        holes[1] = new Hole(screenX + width/2, screenY);
        holes[2] = new Hole(screenX + width, screenY);
        holes[3] = new Hole(screenX, screenY+height);
        holes[4] = new Hole(screenX + width/2, screenY+height);
        holes[5] = new Hole(screenX + width, screenY+height);
    }

    public void update() {
        for (Ball ball : balls) {
            if(ball != null) {
                ball.update();

            }
        }
    }

    public void drawTable(Graphics2D g2) {
        g2.setColor(new Color(196, 164, 132));
        g2.fillRoundRect((int)holes[0].posX - drawOffsetTable, (int)holes[0].posY - drawOffsetTable, (int)(width+holes[0].diameter + drawOffsetTable*2), (int)(height+holes[0].diameter + drawOffsetTable*2), 50, 50);
        g2.setColor(Color.GREEN);
        g2.fillRect(screenX, screenY, width, height);

    }

    public void drawHoles(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        for (Hole hole : holes) {
            g2.fillOval((int)hole.posX, (int)hole.posY, (int)hole.diameter, (int)hole.diameter);
        }
    }

    public void drawBalls(Graphics2D g2) {
        for (Ball ball : balls) {
            if(ball.isOnTable && ball.colour.equals("WHITE")) {
                g2.setColor(Color.WHITE);
                g2.fillOval((int)ball.posX, (int)ball.posY, (int)Ball.diameter, (int)Ball.diameter);
            } else if(ball.isOnTable) {
                g2.setColor(ball.colour.equals("RED") ? Color.RED : Color.BLUE);
                g2.fillOval((int)ball.posX, (int)ball.posY, (int)Ball.diameter, (int)Ball.diameter);
            }
        }
    }

    public int getPlayer1Potted(String player1Colour) {
        int player1Potted = 0;
        for(Ball ball : balls) {
            if(ball.colour.equals(player1Colour) && !ball.isOnTable) {}
            player1Potted++;
        }
        return player1Potted;
    }

    public int getPlayer2Potted(String player2Colour) {
        int player2Potted = 0;
        for(Ball ball : balls) {
            if(ball.colour.equals(player2Colour) && !ball.isOnTable) {}
            player2Potted++;
        }
        return player2Potted;
    }

    public void drawPottedBalls(Graphics2D g2, String player1Colour, String player2Colour) {
        int player1Potted = 0, player2Potted = 0;
        for(Ball ball : balls) {
            if(!ball.isOnTable && ball.colour.equals(player1Colour)) {player1Potted++;}
            if(!ball.isOnTable && ball.colour.equals(player2Colour)) {player2Potted++;}
        }

        if(player1Colour.equals("RED")) {
            g2.setColor(Color.RED);
        } else {
            g2.setColor(Color.BLUE);
        }

        int xPos1 = 60;
        for(int i = 0; i < player1Potted; i++) {
            g2.fillOval(300+xPos1, 720-40, (int) Ball.diameter, (int) Ball.diameter);
            xPos1 += 60;
        }

        if(player2Colour.equals("RED")) {
            g2.setColor(Color.RED);
        } else {
            g2.setColor(Color.BLUE);
        }

        int xPos2 = 60;
        for(int i = 0; i < player2Potted; i++) {
            g2.fillOval(300+xPos2, 760-20, (int) Ball.diameter, (int) Ball.diameter);
            xPos2 += 60;
        }

    }

    public boolean ballsMoving() {
        for(Ball ball : this.balls) {
            if(ball.isOnTable) {
                if(ball.velX != 0 || ball.velY != 0) {
                    return true;
                }
            }
        }
        return false;
    }

}
