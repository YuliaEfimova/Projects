package game.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseListenerAdapter implements MouseListener {
    private final MainWindow mainWindow;

    public MouseListenerAdapter(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                mainWindow.mousePressed(e.getX(), e.getY(), e.isAltDown());
                break;
            case MouseEvent.BUTTON3:
                mainWindow.mousePressed(e.getX(), e.getY(), true);
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
