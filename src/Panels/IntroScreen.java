package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntroScreen extends JPanel implements ActionListener{

    String player1;
    String player2;
    String player1Colour;
    String player2Colour;

    GridBagConstraints gbc = new GridBagConstraints();
    GridBagLayout gbLayout = new GridBagLayout();

    JLabel mainLabel = new JLabel("8 POOL BALL");
    JLabel player1Label = new JLabel("Player 1 Information:");
    JLabel player2Label = new JLabel("Player 2 Information:");

    JTextField player1Name = new JTextField(20);
    JTextField player2Name = new JTextField(20);

    JCheckBox player1Red = new JCheckBox("RED", true);
    JCheckBox player1Blue = new JCheckBox("BLUE", false);
    JCheckBox player2Red = new JCheckBox("RED", false);
    JCheckBox player2Blue = new JCheckBox("BLUE", true);

    JButton startButton = new JButton("START GAME");

    IntroScreen(ActionListener listener) {
        this.setPreferredSize(new Dimension(1200, 700));
        setItemsPreferredSize();
        addItems();
        setFocusableFalse();
        setActionListeners(listener);

        this.setLayout(gbLayout);

    }

    private void setItemsPreferredSize() {
        mainLabel.setFont(new Font("Arial", Font.BOLD, 60));
        player1Label.setFont(new Font("Arial", Font.BOLD, 20));
        player2Label.setFont(new Font("Arial", Font.BOLD, 20));
        player1Name.setFont(new Font("Arial", Font.PLAIN, 20));
        player2Name.setFont(new Font("Arial", Font.PLAIN, 20));
        player1Red.setFont(new Font("Arial", Font.PLAIN, 20));
        player1Blue.setFont(new Font("Arial", Font.PLAIN, 20));
        player2Red.setFont(new Font("Arial", Font.PLAIN, 20));
        player2Blue.setFont(new Font("Arial", Font.PLAIN, 20));
        startButton.setFont(new Font("Arial", Font.BOLD, 40));

    }

    private void addItems() {

        gbc.weighty = 10;

        this.add(mainLabel);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=2;
        gbLayout.setConstraints(mainLabel, gbc);

        gbc.gridwidth = 1;
        gbc.weighty = 3;

        this.add(player1Label);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbLayout.setConstraints(player1Label, gbc);

        this.add(player2Label);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbLayout.setConstraints(player2Label, gbc);

        gbc.weightx = 10;

        this.add(player1Name);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbLayout.setConstraints(player1Name, gbc);

        this.add(player2Name);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbLayout.setConstraints(player2Name, gbc);

        gbc.weightx = 0;

        this.add(player1Red);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbLayout.setConstraints(player1Red, gbc);

        this.add(player1Blue);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbLayout.setConstraints(player1Blue, gbc);

        this.add(player2Red);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbLayout.setConstraints(player2Red, gbc);

        this.add(player2Blue);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbLayout.setConstraints(player2Blue, gbc);

        gbc.weighty = 10;

        this.add(startButton);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbLayout.setConstraints(startButton, gbc);
    }

    private void setFocusableFalse() {
        player1Red.setFocusable(false);
        player1Blue.setFocusable(false);
        player2Red.setFocusable(false);
        player2Blue.setFocusable(false);
        startButton.setFocusable(false);
    }

    private void setActionListeners(ActionListener listener) {
        player1Red.addActionListener(this);
        player1Blue.addActionListener(this);
        player2Red.addActionListener(this);
        player2Blue.addActionListener(this);
        startButton.addActionListener(listener);
    }

    public void getPlayerInfo() {
        player1 = player1Name.getText();
        player2 = player2Name.getText();
        player1Colour = player1Red.isSelected() ? "RED" : "BLUE";
        player2Colour = player2Red.isSelected() ? "RED" : "BLUE";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == player1Red) {
            if(player1Red.isSelected()) {
                player1Blue.setSelected(false);
                player2Red.setSelected(false);
                player2Blue.setSelected(true);
            } else {
                player1Blue.setSelected(true);
                player2Red.setSelected(true);
                player2Blue.setSelected(false);
            }
        }
        if (e.getSource() == player1Blue) {
            if(player2Blue.isSelected()) {
                player1Red.setSelected(false);
                player2Blue.setSelected(false);
                player2Red.setSelected(true);
            } else {
                player1Red.setSelected(true);
                player2Blue.setSelected(true);
                player2Red.setSelected(false);
            }
        }
        if (e.getSource() == player2Red) {
            if(player2Red.isSelected()) {
                player2Blue.setSelected(false);
                player1Red.setSelected(false);
                player1Blue.setSelected(true);
            } else {
                player2Blue.setSelected(true);
                player1Red.setSelected(true);
                player1Blue.setSelected(false);
            }
        }
        if (e.getSource() == player2Blue) {
            if(player1Blue.isSelected()) {
                player2Red.setSelected(false);
                player1Blue.setSelected(false);
                player1Red.setSelected(true);
            } else {
                player2Red.setSelected(true);
                player1Blue.setSelected(true);
                player1Red.setSelected(false);
            }
        }

    }
}
