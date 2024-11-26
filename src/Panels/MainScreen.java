package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JPanel implements ActionListener {

    GamePanel gamePanel;
    IntroScreen introScreen = new IntroScreen(this);

    public MainScreen() {
        this.setPreferredSize(new Dimension(1200, 800));
        this.add(introScreen);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("START GAME")) {
            if(introScreen.player1Name.getText().equals("") || introScreen.player2Name.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter player names");
                return;
            } else if (introScreen.player1Name.getText().length() > 10 || introScreen.player2Name.getText().length() > 10) {
                JOptionPane.showMessageDialog(null, "The names cannot be longer than 10 characters!");
                return;
            }
            introScreen.getPlayerInfo();
            gamePanel = new GamePanel(introScreen.player1, introScreen.player2, introScreen.player1Colour, introScreen.player2Colour);
            this.remove(introScreen);
            this.add(gamePanel, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            gamePanel.startTimer();
        }
    }
}
