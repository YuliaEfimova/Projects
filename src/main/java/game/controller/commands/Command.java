package game.controller.commands;

import game.army.Army;
import game.cells.Cell;
import game.controller.state.Map;

public interface Command {
    void executeOnCell(Cell cell);
    void executeOnMap(Map map);
    void executeOnArmy(Army army);

}
