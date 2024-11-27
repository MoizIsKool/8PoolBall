package Pool;

public class Hole {
    double posX, posY;
    double diameter;
    private double extraDistance = 15;

    Hole(int x, int y) {

        diameter = 60;

        this.posX = x - (diameter/2);
        this.posY = y - (diameter/2);
    }

    public boolean isBallInHole(Ball ball) {
        double distance = Math.sqrt(Math.pow((ball.posX+Ball.diameter/2) - (this.posX+this.diameter/2), 2) + Math.pow((ball.posY+Ball.diameter/2) - (this.posY+this.diameter/2), 2));
        return distance < (Ball.diameter / 2 + this.diameter / 2);
    }
}
