package game.cells;

import game.army.Army;
import game.controller.CellCoordinates;
import game.controller.commands.Command;
import game.controller.state.Map;

public abstract class CellBase implements Cell {
    private Army army;
    private final CellCoordinates cellCoordinates;

    protected CellBase(CellCoordinates cellCoordinates) {
        this.cellCoordinates = cellCoordinates;
    }

    public CellCoordinates getCellCoordinates() {
        return cellCoordinates;
    }

    @Override
    public Army getArmy() {
        return army;
    }

    @Override
    public void setArmy(Army army) {
        this.army = army;
    }

    @Override
    public void acceptCommand(Command command) {
        command.executeOnCell(this);
    }

    @Override
    public void select(Map map) {
        if (army != null) {
            map.setSelectedCellCoordinates(getCellCoordinates());
        }
    }
}
