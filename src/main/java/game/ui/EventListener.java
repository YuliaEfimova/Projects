package game.ui;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

import game.controller.Controller;

public class EventListener extends MouseInputAdapter {
    private final Controller controller;

    public EventListener(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                break;
            case MouseEvent.BUTTON3:
                break;
        }
    }
}
