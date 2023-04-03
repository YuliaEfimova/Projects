package game.cells;

import game.controller.CellCoordinates;

public class Mountain extends CellBase{
    public Mountain(CellCoordinates cellCoordinates) {
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
        return CellsType.MOUNTAIN;
    }
}
