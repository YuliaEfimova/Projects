package game.ui;

import java.awt.*;

import javax.swing.*;

import game.controller.Controller;
import game.controller.state.Map;

public class GraphicalUI implements UI {

    private MainWindow mainWindow;

    @Override
    public void init(Controller controller) {
        mainWindow = new MainWindow(controller);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("The Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainWindow);
        frame.setSize(new Dimension(1280, 800));
        frame.setVisible(true);
        frame.setResizable(false);
    }

    @Override
    public void updateState(Map newMap) {
        if (mainWindow != null) {
            mainWindow.updateState(newMap);
        }
    }
}
