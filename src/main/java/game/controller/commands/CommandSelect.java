package game.controller.commands;

import game.army.Army;
import game.cells.Cell;
import game.controller.state.Map;

public class CommandSelect implements Command{
    private final Map map;

    public CommandSelect(Map map) {
        this.map = map;
    }

    @Override
    public void executeOnCell(Cell cell) {
        cell.select(map);
    }

    @Override
    public void executeOnMap(Map map) {
        //Do nothing
    }

    @Override
    public void executeOnArmy(Army army) {
        //Do nothing
    }
}
