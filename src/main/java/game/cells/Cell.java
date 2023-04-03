package game.cells;

import game.army.Army;
import game.controller.commands.Command;
import game.controller.state.Map;

public interface Cell {
    Army getArmy();
    void tick();
    void setArmy(Army army);

    CellsType getType();
    void acceptCommand(Command command);
    void select(Map map);
}
