package game.ui;

import java.awt.*;

import javax.swing.*;

import game.army.Description;
import game.controller.CellCoordinates;
import game.controller.Controller;
import game.controller.state.Map;
import game.army.Army;
import game.army.Player;
import game.cells.*;

public class MainWindow extends JComponent {
    private final int CELLS_SIZE = 64;
    private Map map;
    private final Image swamp;
    private final Image city;
    private final Image mountain;
    private final Image general;
    private Controller controller;

    public MainWindow(Controller controller) {
        this.controller = controller;
        MouseListenerAdapter mouseListenerAdapter = new MouseListenerAdapter(this);
        addMouseListener(mouseListenerAdapter);
        swamp = new ImageIcon(ClassLoader.getSystemResource("swamp.png")).getImage();
        city = new ImageIcon(ClassLoader.getSystemResource("village.png")).getImage();
        mountain = new ImageIcon(ClassLoader.getSystemResource("peaks.png")).getImage();
        general = new ImageIcon(ClassLoader.getSystemResource("elven-castle.png")).getImage();
    }

    public void updateState(Map newMap) {
        this.map = newMap;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (map != null) {
            drawState(g, map);
        }
    }

    private void drawState(Graphics g, Map map) {
        for (int x = 0; x < map.getSizeX(); x += 1) {
            for (int y = 0; y < map.getSizeY(); y += 1) {
                CellCoordinates cellCoordinates = new CellCoordinates(x, y);
                Cell cell = map.getCell(cellCoordinates);
                drawCell(g, map.getCell(x, y), x, y, cellCoordinates.equals(map.getSelectedCellCoordinates()));
            }
        }
        g.setColor(Color.black);
    }

    private void drawCell(Graphics g, Cell cell, int x, int y, boolean isSelected) {
        Army army = cell.getArmy();
        if (army == null) {
            g.setColor(Color.lightGray);
        } else {
            Player player = army.getPlayer();
            if (player == Player.RED) {
                g.setColor(Color.red);
            } else {
                g.setColor(Color.blue);
            }
        }
        g.fillRect(x * CELLS_SIZE, y * CELLS_SIZE, CELLS_SIZE, CELLS_SIZE);
        switch (cell.getType()) {
            case CITY:
                g.drawImage(city, x * CELLS_SIZE, y * CELLS_SIZE, null);
                break;
            case MOUNTAIN:
                g.drawImage(mountain, x * CELLS_SIZE, y * CELLS_SIZE, null);
                break;
            case SWAMP:
                g.drawImage(swamp, x * CELLS_SIZE, y * CELLS_SIZE, null);
                break;
            case GENERAL:
                g.drawImage(general, x * CELLS_SIZE, y * CELLS_SIZE, null);
                break;
        }
        if (army != null) {
            Description description = army.description();
            if (description.getCountNormalInfantry() == 0) {
                g.setColor(Color.YELLOW);
                drawCenteredString(g, description.getCountVeteranInfantry() + "V", x * CELLS_SIZE + CELLS_SIZE/2, y * CELLS_SIZE + CELLS_SIZE/2);
            } else if (description.getCountVeteranInfantry() == 0) {
                g.setColor(Color.WHITE);
                drawCenteredString(g, String.valueOf(description.getCountNormalInfantry()), x * CELLS_SIZE + CELLS_SIZE/2, y * CELLS_SIZE + CELLS_SIZE/2);
            } else {
                g.setColor(Color.WHITE);
                drawCenteredString(g, String.valueOf(description.getCountNormalInfantry()), x * CELLS_SIZE + CELLS_SIZE/2, y * CELLS_SIZE + CELLS_SIZE/4);
                g.setColor(Color.YELLOW);
                drawCenteredString(g, "+" + description.getCountVeteranInfantry() + "V", x * CELLS_SIZE + CELLS_SIZE/2, y * CELLS_SIZE + CELLS_SIZE*3/4);
            }
        }
        if (isSelected) {
            g.setColor(Color.YELLOW);
            g.drawRect(x * CELLS_SIZE, y * CELLS_SIZE, CELLS_SIZE-1, CELLS_SIZE-1);
            g.drawRect(x * CELLS_SIZE+1, y * CELLS_SIZE+1, CELLS_SIZE-3, CELLS_SIZE-3);
        }
    }
    public void drawCenteredString(Graphics g, String text, int x, int y) {
        if (text.length()<=4) {
            g.setFont(g.getFont().deriveFont(20f));
        } else {
            g.setFont(g.getFont().deriveFont(16f));
        }
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        x -= metrics.stringWidth(text)/2;
        y -= metrics.getHeight() / 2 - metrics.getAscent();
        g.drawString(text, x, y);
    }

    public void mousePressed(int x, int y, boolean alt) {
        controller.mousePressed(new CellCoordinates(x/CELLS_SIZE, y/CELLS_SIZE), alt);
    }
}

