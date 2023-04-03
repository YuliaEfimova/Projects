package game.controller;

import game.cells.Cell;
import game.controller.commands.Command;
import game.controller.commands.CommandGo;
import game.controller.commands.CommandSelect;
import game.controller.commands.CommandTimer;
import game.controller.state.Map;
import game.ui.UI;

import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    private final Map map;
    private final UI ui;

    public Controller(Map initialMap, UI ui) {
        this.map = initialMap;
        this.ui = ui;
    }

    public synchronized void timer() {
        Command cmd = new CommandTimer();
        map.sendCommandToAllCells(cmd);
        ui.updateState(map);
    }

    public void start() {
        ui.updateState(map);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timer();
            }
        }, 1000, 1000);
    }

    public void mousePressed(CellCoordinates cellCoordinates, boolean alt) {
//        System.out.printf("mousePressed([%d,%d],%s)\n", cellCoordinates.x, cellCoordinates.y, alt);
        Cell cell = map.getCell(cellCoordinates);
        if (map.getSelectedCellCoordinates() == null) {
//            System.out.printf("    getSelectedCellCoordinates() == null\n");
            cell.acceptCommand(new CommandSelect(map));
        } else if (cellCoordinates.equals(map.getSelectedCellCoordinates())) {
//            System.out.printf("    cellCoordinates.equals(map.getSelectedCellCoordinates())\n");
        } else if (isReachable(map.getSelectedCellCoordinates(), cellCoordinates)) {
//            System.out.printf("    go([%d,%d]->[%d,%d])\n",
//                    map.getSelectedCellCoordinates().x, map.getSelectedCellCoordinates().y,
//                    cellCoordinates.x, cellCoordinates.y);
            Command cmd = new CommandGo(cell, alt, map);
            map.getSelectedCell().acceptCommand(cmd);
        } else {
            cell.acceptCommand(new CommandSelect(map));
        }
        ui.updateState(map);
    }

    private boolean isReachable(CellCoordinates from, CellCoordinates to) {
        return Math.abs(to.x - from.x) + Math.abs(to.y - from.y) == 1;
    }
}
