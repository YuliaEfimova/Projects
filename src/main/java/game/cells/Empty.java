package game.cells;

import game.controller.CellCoordinates;

public class Empty extends CellBase{
    public Empty(CellCoordinates cellCoordinates) {
        super(cellCoordinates);
    }

    @Override
    public void tick() {
        if (getArmy() != null) {
            setArmy(getArmy().tick());
        }
    }

    @Override
    public CellsType getType() {
        return CellsType.EMPTY;
    }


}
