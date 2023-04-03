package game;

import game.controller.Controller;
import game.controller.state.Map;
import game.ui.UI;
import game.ui.GraphicalUI;

public class Main {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");

        int sizeX = 20;
        int sizeY = 12;
        Map initialMap = Map.create(sizeX, sizeY);
        UI ui = new GraphicalUI();
        Controller controller = new Controller(initialMap, ui);
        ui.init(controller);
        controller.start();
    }
}
