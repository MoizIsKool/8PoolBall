package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Pool.*;
import Handlers.*;

public class GamePanel extends JPanel implements ActionListener {

    final int FPS = 120;

    Timer timer;

    String player1Name;
    String player2Name;
    String player1Colour;
    String player2Colour;

    final int numBalls = 5;

    Table table;
    Cue cue = new Cue();

    CollisionHandler collisionHandler = new CollisionHandler();

    public GamePanel(String player1Name, String player2Name, String player1Colour, String player2Colour) {

        this.setPreferredSize(new Dimension(1200, 800));

        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.player1Colour = player1Colour;
        this.player2Colour = player2Colour;

        this.addMouseListener(cue.mouseHandler);
        this.addMouseMotionListener(cue.mouseHandler);

        table = new Table(100, 50, 1000, 563);

        timer = new Timer(1000/FPS, this);
    }

    public void startTimer() {
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        table.drawTable(g2d);
        table.drawPottedBalls(g2d, player1Colour, player2Colour);
        table.drawHoles(g2d);
        table.drawBalls(g2d);

        if(cue.isPullingBack) {
            drawLine(g2d);
        }

        drawInfo(g2d);
    }

    private void drawInfo(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.drawString("Player 1 (" + player1Colour + "): ", 50, 720);
        g2.drawString("Player 2 (" + player2Colour + "): ", 50, 760);
        g2.drawString(player1Name, 230, 720);
        g2.drawString(player2Name, 230, 760);

    }

    public void checkCollisions() {
        collisionHandler.checkWallCollision(table);
        collisionHandler.checkBallBallCollision(table);
        collisionHandler.checkBallInHole(table.holes, table.balls);
    }

    void resetBall() {
        if(!table.balls[10].isOnTable && !table.ballsMoving()) {
            table.resetWhite();
        }
    }

    void updateCue() {
        if(!table.ballsMoving()) {
            cue.determineShooting(table.balls[10]);
        }
        cue.shoot(table.balls[10]);
    }

    void drawLine(Graphics2D g2) {
        cue.drawLine(table.balls[10], g2);
    }

    void determineWin() {
        if(table.getPlayer1Potted(player1Colour) == 4) {
            JOptionPane.showMessageDialog(null, "Player 1 Wins!", "Winner", JOptionPane.INFORMATION_MESSAGE);
        }
        if(table.getPlayer2Potted(player2Colour) == 4) {
            JOptionPane.showMessageDialog(null, "Playwr 2 Wins!!", "Winner", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == timer) {
            determineWin();
            table.update();
            checkCollisions();
            updateCue();
            resetBall();
            repaint();
        }
    }
}
