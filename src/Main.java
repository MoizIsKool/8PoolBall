import Panels.MainScreen;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }

    public Main() {

        JFrame frame = new JFrame("Golf Game");
        MainScreen mainScreen = new MainScreen();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.setContentPane(mainScreen);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
