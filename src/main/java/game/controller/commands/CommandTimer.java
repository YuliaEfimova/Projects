package game.controller.commands;

import game.army.Army;
import game.cells.Cell;
import game.controller.state.Map;

public class CommandTimer implements Command{
    @Override
    public void executeOnCell(Cell cell) {
        cell.tick();
    }

    @Override
    public void executeOnMap(Map map) {
        map.incrementTimer();
    }

    @Override
    public void executeOnArmy(Army army) {
        //Do nothing
    }
}
